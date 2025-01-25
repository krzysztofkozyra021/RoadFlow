package pl.roadflow.core.mechanics.car.controller.impl;

import pl.roadflow.core.mechanics.car.controller.IVehicleInput;
import pl.roadflow.core.mechanics.car.controller.IVehiclePhysics;
import pl.roadflow.core.mechanics.car.physics.DriftPhysics;
import pl.roadflow.core.mechanics.stats.CarParameters;
import pl.roadflow.utils.Vector2;

public class TopDownCarController implements IVehiclePhysics, IVehicleInput {

    private DriftPhysics driftPhysics;
    CarParameters carParameters;
    // Vehicle dynamics constants
    public float currentSpeed = 0.0f;          // Current vehicle speed
    private float lateralDrag = 1.02f;          // Lateral movement resistance (More = More drift)
    private boolean isOnRoad = true;


    // Input state
    public float accelerationInput = 0.0f;     // Forward/backward input (-1 to 1)
    public float steeringInput = 0.0f;         // Left/right input (-1 to 1)
    public float rotationAngle = 0.0f;         // Current vehicle rotation in degrees
    private Vector2 velocity = new Vector2(0, 0); // Current velocity vector
    Vector2 inputVector;                       // Raw input vector

    public TopDownCarController(CarParameters carParameters) {
        driftPhysics = new DriftPhysics(this);
        this.carParameters = carParameters;
    }

    public float getLateralDrag(){
        return lateralDrag;
    }

    public float getAccelerationInput() {
        return accelerationInput;
    }

    public CarParameters getCarParameters() {
        return carParameters;
    }

    public void setIsOnRoad(boolean isOnRoad) {
        this.isOnRoad = isOnRoad;
    }

    @Override
    public void applyEngineForce() {
        float maxSpeed = carParameters.getMaxSpeed();
        float accelerationFactor = carParameters.getAccelerationFactor();
        float grip = carParameters.getGrip();
        float deltaTime = 1.0f/60.0f; // Assuming 60 FPS

        if (!isOnRoad) {
            grip *= 1.5f;
            maxSpeed *= 0.7f;
        }

        // Calculate how far we are from max speed (as a percentage)
        float currentSpeedRatio = velocity.magnitude() / maxSpeed;

        // Progressive acceleration curve that gets weaker as we approach max speed
        float accelerationMultiplier = (1.0f - (currentSpeedRatio));

        // Base force is stronger at low speeds, weaker at high speeds
        float baseForce = maxSpeed * 0.004f; // 5% of max speed as base force

        // Scale the force by accelerationFactor (0.1 - 1.0)
        float scaledForce = baseForce * accelerationFactor;

        // Apply acceleration curve
        Vector2 engineForceVector = getEngineForceVector(maxSpeed, scaledForce * accelerationMultiplier);
        velocity = velocity.add(engineForceVector);

        // Apply drift physics
        driftPhysics.applyDriftPhysics();

        // Speed limiting
        if (velocity.magnitude() > maxSpeed) {
            velocity = velocity.normalize().multiply(maxSpeed);
        }

        // Braking and natural deceleration
        float naturalDrag = 0.3f; // Natural air resistance
        float brakingForce;

        if (Math.abs(accelerationInput) < 0.1f) {
            // When not accelerating, apply stronger braking
            brakingForce = (grip * 1.2f + naturalDrag) * deltaTime;
        } else {
            // Natural deceleration during acceleration
            brakingForce = (grip * 0.3f + naturalDrag * (currentSpeedRatio)) * deltaTime;
        }


        velocity = velocity.multiply(1.0f - brakingForce);

        if (!isOnRoad) {
            velocity = velocity.multiply(0.99f);
        }

        currentSpeed = velocity.magnitude();
    }

    private Vector2 getEngineForceVector(float maxSpeed, float accelerationFactor) {
        float angleInRadians = (float)Math.toRadians(rotationAngle);
        Vector2 forwardDir = new Vector2(
                (float)Math.cos(angleInRadians),
                (float)Math.sin(angleInRadians)
        );

        if (!isOnRoad) {
            accelerationFactor *= 0.75f;
        }

        float currentForwardSpeed = Vector2.dot(velocity, forwardDir);
        float speedRatio = Math.abs(currentForwardSpeed) / maxSpeed;
        float baseAcceleration = 0.4f;
        float accelerationMultiplier = (1.0f - speedRatio) * (1.0f - speedRatio);
        float speedScale = maxSpeed / 100f;

        float scaledAcceleration = baseAcceleration * accelerationFactor * speedScale;
        float actualAcceleration = accelerationInput * scaledAcceleration * accelerationMultiplier;



        return forwardDir.multiply(actualAcceleration);
    }

    @Override
    public void applySteering() {
        if (currentSpeed < carParameters.getMinSpeedToTurn()) return;

        float speedFactor = currentSpeed / carParameters.getMaxSpeed();
        speedFactor = Math.max(0.2f, Math.min(speedFactor, 1.0f));

        float steeringAmount = steeringInput * carParameters.getTurnFactor() * speedFactor;

        Vector2 forwardDir = getForwardVector();
        float driftAngle = Math.abs(Vector2.dot(velocity.normalize(), forwardDir));
        if (driftAngle < 0.8f) {
            steeringAmount *= 1.3f;
        }

        rotationAngle += steeringAmount;
        normalizeAngle();
    }

    private Vector2 getForwardVector() {
        float angleInRadians = (float)Math.toRadians(rotationAngle);
        return new Vector2(
                (float)Math.cos(angleInRadians),
                (float)Math.sin(angleInRadians)
        );
    }


    private void normalizeAngle() {
        rotationAngle = rotationAngle % 360;
        if (rotationAngle < 0) rotationAngle += 360;
    }

    public void setInputVector(Vector2 inputVector) {
        this.inputVector = inputVector;
        steeringInput = inputVector.x;
        accelerationInput = inputVector.y;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public float getRotationAngle() {
        return rotationAngle;
    }


    public void setVelocity(Vector2 vector2) {
        velocity = vector2;
    }

    public void stopCar() {
        accelerationInput = 0;
        steeringInput = 0;
        currentSpeed = 0;

        // Reset velocity
        velocity = new Vector2(0, 0);
    }

    public boolean isOnRoad() {
        return isOnRoad;
    }
}
