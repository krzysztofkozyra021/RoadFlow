package src.main.java.pl.roadflow.core.mechanics.car.controller.impl;

import src.main.java.pl.roadflow.core.mechanics.car.controller.ITopDownCarController;
import src.main.java.pl.roadflow.utils.Vector2;

public class TopDownCarController implements ITopDownCarController {
    public float accelerationFactor = 30.0f;
    public float turnFactor = 3.5f;
    public float currentSpeed = 0.0f;
    private float minSpeedToTurn = 2.0f;

    public float accelerationInput = 0.0f;
    public float steeringInput = 0.0f;
    public float rotationAngle = 0.0f;
    private Vector2 velocity = new Vector2(0, 0);
    Vector2 inputVector;

    @Override
    public void ApplyEngineForce() {
        float angleInRadians = (float)Math.toRadians(rotationAngle + 180);
        Vector2 up = new Vector2(
                (float)Math.sin(angleInRadians),
                -(float)Math.cos(angleInRadians)
        );

        Vector2 engineForceVector = up.multiply(accelerationInput * accelerationFactor);

        // apply engine force
        velocity = velocity.add(engineForceVector);
        currentSpeed = Math.abs(accelerationInput * accelerationFactor); // abs because we don't care about direction
    }

    @Override
    public void ApplySteering() {
        if (currentSpeed < minSpeedToTurn) {
            return;
        }

        // calculate rotation factor based on speed
        float speedFactor = Math.min((currentSpeed - minSpeedToTurn) / 10.0f, 1.0f);
        speedFactor = Math.max(0.0f, speedFactor);

        // apply rotation
        rotationAngle += steeringInput * turnFactor * speedFactor;
    }

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
}
