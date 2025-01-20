package src.main.java.pl.roadflow.core.mechanics.car.physics;

import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;
import src.main.java.pl.roadflow.utils.Vector2;

public class DriftPhysics {

    private final TopDownCarController topDownCarController;

    public DriftPhysics(TopDownCarController topDownCarController) {
        this.topDownCarController = topDownCarController;
    }

    public void applyDriftPhysics() {
        float angleInRadians = (float) Math.toRadians(topDownCarController.getRotationAngle());
        Vector2 forward = new Vector2(
                (float) Math.cos(angleInRadians),
                (float) Math.sin(angleInRadians)
        );

        // Vector right is perpendicular to forward (rotated 90 degrees clockwise)
        Vector2 right = new Vector2(
                -(float) Math.sin(angleInRadians),
                (float) Math.cos(angleInRadians)
        );

        float forwardSpeed = Vector2.dot(topDownCarController.getVelocity(), forward);
        float lateralSpeed = Vector2.dot(topDownCarController.getVelocity(), right);

        float driftedLateralSpeed = lateralSpeed * topDownCarController.getCarParameters().getDriftFactor();

        if (Math.abs(topDownCarController.getAccelerationInput()) < 0.1f && Math.abs(forwardSpeed) > 0.5f) {
            driftedLateralSpeed *= topDownCarController.getLateralDrag();
        }

        topDownCarController.setVelocity(forward.multiply(forwardSpeed).add(right.multiply(driftedLateralSpeed)));
    }
}