package src.main.java.pl.roadflow.core.mechanics.car.controller.impl;

import src.main.java.pl.roadflow.core.mechanics.car.controller.ITopDownCarController;
import src.main.java.pl.roadflow.utils.Vector2;

public class TopDownCarController implements ITopDownCarController {
    public float accelerationFactor = 30.0f;
    public float turnFactor = 3.5f;

    public float accelerationInput = 0.0f;
    public float steeringInput = 0.0f;
    public float rotationAngle = 0.0f;

    @Override
    public void ApplyEngineForce() {

        // We calculate up vector based on the rotation angle
        float angleInRadians = (float)Math.toRadians(rotationAngle + 180);
        Vector2 up = new Vector2(
                (float)Math.sin(angleInRadians),
                -(float)Math.cos(angleInRadians)
        );

        Vector2 engineForceVector = up.multiply(accelerationInput * accelerationFactor);
    }

    @Override
    public void ApplySteering() {
        rotationAngle += steeringInput * turnFactor;
    }

    public void setInputVector(Vector2 inputVector) {
        accelerationInput = inputVector.y;
        steeringInput = inputVector.x;
    }

}
