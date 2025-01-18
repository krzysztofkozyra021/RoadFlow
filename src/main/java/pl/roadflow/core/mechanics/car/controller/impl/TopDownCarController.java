package src.main.java.pl.roadflow.core.mechanics.car.controller.impl;

import src.main.java.pl.roadflow.core.mechanics.car.controller.IVehicleInput;
import src.main.java.pl.roadflow.core.mechanics.car.controller.IVehiclePhysics;
import src.main.java.pl.roadflow.utils.Vector2;

public class TopDownCarController implements IVehiclePhysics, IVehicleInput {
    // Vehicle dynamics constants
    public float accelerationFactor = 0.005f;    // Base acceleration force
    public float turnFactor = 10.0f;            // Base turning rate
    public float currentSpeed = 0.0f;          // Current vehicle speed
    private float minSpeedToTurn = 0.5f;       // Minimum speed required for turning
    private float driftFactor = 0.95f;         // How much lateral velocity is preserved (lower = more drift)
    private float maxSpeed = 20f;             // Maximum vehicle speed
    private float drag = 0.98f;                // Air resistance (higher = less drag)
    private float lateralDrag = 0.9f;          // Lateral movement resistance


    // Input state
    public float accelerationInput = 0.0f;     // Forward/backward input (-1 to 1)
    public float steeringInput = 0.0f;         // Left/right input (-1 to 1)
    public float rotationAngle = 0.0f;         // Current vehicle rotation in degrees
    private Vector2 velocity = new Vector2(0, 0); // Current velocity vector
    Vector2 inputVector;                       // Raw input vector

    @Override
    public void applyEngineForce() {
        float angleInRadians = (float)Math.toRadians(rotationAngle);
        Vector2 forwardDir = new Vector2(
                (float)Math.cos(angleInRadians),  // We use cos for x because front of car is facing right
                (float)Math.sin(angleInRadians)   // and sin for y
        );

        float currentForwardSpeed = Vector2.dot(velocity, forwardDir);
        float accelerationRoom = maxSpeed - Math.abs(currentForwardSpeed);
        float actualAcceleration = accelerationInput * accelerationFactor * accelerationRoom;

        Vector2 engineForceVector = forwardDir.multiply(actualAcceleration);
        velocity = velocity.add(engineForceVector);

        applyDriftPhysics();

        if (velocity.magnitude() > maxSpeed) {
            velocity = velocity.normalize().multiply(maxSpeed);
        }

        if (Math.abs(accelerationInput) < 0.1f) {
            velocity = velocity.multiply(drag);
        }

        currentSpeed = velocity.magnitude();
    }

    private void applyDriftPhysics() {
        float angleInRadians = (float)Math.toRadians(rotationAngle);
        Vector2 forward = new Vector2(
                (float)Math.cos(angleInRadians),
                (float)Math.sin(angleInRadians)
        );

        // Vector right is perpendicular to forward (rotated 90 degrees clockwise)
        Vector2 right = new Vector2(
                -(float)Math.sin(angleInRadians),
                (float)Math.cos(angleInRadians)
        );

        float forwardSpeed = Vector2.dot(velocity, forward);
        float lateralSpeed = Vector2.dot(velocity, right);

        float driftedLateralSpeed = lateralSpeed * driftFactor;

        if (Math.abs(accelerationInput) < 0.1f && Math.abs(forwardSpeed) > 0.5f) {
            driftedLateralSpeed *= lateralDrag;
        }

        velocity = forward.multiply(forwardSpeed).add(right.multiply(driftedLateralSpeed));
    }

    @Override
    public void applySteering() {
        if (currentSpeed < minSpeedToTurn) return;

        // Calculate speed-based steering sensitivity
        float speedFactor = currentSpeed / maxSpeed;
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
