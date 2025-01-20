package src.main.java.pl.roadflow.core.mechanics.car.controller.impl;

import src.main.java.pl.roadflow.core.mechanics.car.controller.IVehicleInput;
import src.main.java.pl.roadflow.core.mechanics.car.controller.IVehiclePhysics;
import src.main.java.pl.roadflow.core.mechanics.car.physics.DriftPhysics;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;
import src.main.java.pl.roadflow.utils.Vector2;

public class TopDownCarController implements IVehiclePhysics, IVehicleInput {

    private DriftPhysics driftPhysics;
    CarParameters carParameters;
    // Vehicle dynamics constants
    public float turnFactor = 10.0f;            // Base turning rate
    public float currentSpeed = 0.0f;          // Current vehicle speed
    private float lateralDrag = 1.02f;          // Lateral movement resistance (More = More drift)


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

    @Override
    public void applyEngineForce() {

        float maxSpeed = carParameters.getMaxSpeed();
        float accelerationFactor = carParameters.getAccelerationFactor();
        float drag = carParameters.getDrag();
        Vector2 engineForceVector = getEngineForceVector(maxSpeed, accelerationFactor);
        velocity = velocity.add(engineForceVector);

        driftPhysics.applyDriftPhysics();

        if (velocity.magnitude() > maxSpeed) {
            velocity = velocity.normalize().multiply(maxSpeed);
        }

        if (Math.abs(accelerationInput) < 0.1f) {
            velocity = velocity.multiply(drag);
        }

        currentSpeed = velocity.magnitude();
    }

    private Vector2 getEngineForceVector(float maxSpeed, float accelerationFactor) {

        float angleInRadians = (float)Math.toRadians(rotationAngle);
        Vector2 forwardDir = new Vector2(
                (float)Math.cos(angleInRadians),  // We use cos for x because front of car is facing right
                (float)Math.sin(angleInRadians)   // and sin for y
        );

        float currentForwardSpeed = Vector2.dot(velocity, forwardDir);
        float accelerationRoom = maxSpeed - Math.abs(currentForwardSpeed);
        float actualAcceleration = accelerationInput * (accelerationFactor / 100) * accelerationRoom;

        Vector2 engineForceVector = forwardDir.multiply(actualAcceleration);
        return engineForceVector;
    }

    @Override
    public void applySteering() {
        if (currentSpeed < carParameters.getMinSpeedToTurn()) return;

        // Calculate speed-based steering sensitivity
        float speedFactor = currentSpeed / carParameters.getMaxSpeed();
        speedFactor = Math.max(0.2f, Math.min(speedFactor, 1.0f));

        // Apply steering with drift compensation
        float steeringAmount = steeringInput * turnFactor * speedFactor;

        // Enhance steering during drift
        if (Math.abs(Vector2.dot(velocity.normalize(), getForwardVector())) < 0.8f) {
            steeringAmount *= 1.3f; // Increase steering sensitivity during drift
        }

        // Update rotation angle
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

    // Input handling
    public void setInputVector(Vector2 inputVector) {
        this.inputVector = inputVector;
        accelerationInput = inputVector.y;
        steeringInput = inputVector.x;
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
}
