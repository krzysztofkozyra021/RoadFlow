package src.main.java.pl.roadflow.core.mechanics.car.physics;

import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;
import src.main.java.pl.roadflow.utils.Vector2;

public class DriftPhysics {
    private final TopDownCarController topDownCarController;
    private static final float GRIP_STRENGTH = 0.5f; // Controls how fast orthagonal speed is killed
    public DriftPhysics(TopDownCarController topDownCarController) {
        this.topDownCarController = topDownCarController;
    }

    public void applyDriftPhysics() {
        float angleInRadians = (float) Math.toRadians(topDownCarController.getRotationAngle());
        Vector2 forward = new Vector2(
                (float) Math.cos(angleInRadians),
                (float) Math.sin(angleInRadians)
        );

        Vector2 right = new Vector2(
                -(float) Math.sin(angleInRadians),
                (float) Math.cos(angleInRadians)
        );

        Vector2 velocity = topDownCarController.getVelocity();
        float forwardSpeed = Vector2.dot(velocity, forward);
        float lateralSpeed = Vector2.dot(velocity, right);

        float grip = GRIP_STRENGTH + (1.0f - topDownCarController.getCarParameters().getDriftFactor());

        if (!topDownCarController.isOnRoad()) {
            grip *= 2.0f;
        }

        float deltaTime = 1.0f/60.0f;

        float reductionRate = Math.abs(lateralSpeed) * grip * deltaTime;
        float newLateralSpeed = lateralSpeed;

        if (Math.abs(lateralSpeed) > 0.1f) {
            if (lateralSpeed > 0) {
                newLateralSpeed = Math.max(0, lateralSpeed - reductionRate);
            } else {
                newLateralSpeed = Math.min(0, lateralSpeed + reductionRate);
            }
        }

        Vector2 newVelocity = forward.multiply(forwardSpeed).add(right.multiply(newLateralSpeed));
        topDownCarController.setVelocity(newVelocity);
    }
}