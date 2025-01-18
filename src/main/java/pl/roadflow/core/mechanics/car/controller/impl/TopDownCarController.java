package src.main.java.pl.roadflow.core.mechanics.car.controller.impl;

import src.main.java.pl.roadflow.core.mechanics.car.controller.ITopDownCarController;

public class TopDownCarController implements ITopDownCarController {
    public float accelerationFactor = 30.0f;
    public float turnFactor = 3.5f;

    public float accelerationInput = 0.0f;
    public float steeringInput = 0.0f;
    public float rotationAngle = 0.0f;

    @Override
    public void ApplyEngineForce() {

    }

    @Override
    public void ApplySteering() {
        rotationAngle -= steeringInput * turnFactor;

    }
}
