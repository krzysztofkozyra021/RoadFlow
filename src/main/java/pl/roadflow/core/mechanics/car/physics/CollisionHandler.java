package pl.roadflow.core.mechanics.car.physics;

import pl.roadflow.core.mechanics.car.Car;
import pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import pl.roadflow.ui.screens.GameScreen;
import pl.roadflow.utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class CollisionHandler {
    public int stuckCounter = 0;
    private final Car car;

    public CollisionHandler(Car car) {
        this.car = car;
    }

    public Vector2 handleCollisions(float x, float y, float previousX, float previousY,
                                    TopDownCarController controller) {
        ArrayList<Rectangle> obstacles = GameScreen.getMapObstacles();
        Vector2 position = new Vector2(x, y);
        for (Rectangle obstacle : obstacles) {
            if (isCollidingWithObstacle(obstacle, x, y, car.getCarModelIcon(), controller.getRotationAngle())) {
                position = calculateCollisionResponse(previousX, previousY, x, y, obstacle, controller);
                stuckCounter++;
                return position;
            }
        }

        if (isCollidingWithFrame(x, y, car.getCarModelIcon(), controller.getRotationAngle())) {
            position = handleFrameCollision(x, y, controller);
            stuckCounter++;
            return position;
        }



        stuckCounter = 0;
        return position;
    }

    private Vector2 calculateCollisionResponse(float previousX, float previousY, float x, float y,
                                               Rectangle obstacle, TopDownCarController controller) {
        // Calculate push vector
        float centerX = obstacle.x + obstacle.width / 2.0f;
        float centerY = obstacle.y + obstacle.height / 2.0f;

        float pushX = x - centerX;
        float pushY = y - centerY;

        float length = (float) Math.sqrt(pushX * pushX + pushY * pushY);
        if (length > 0) {
            pushX /= length;
            pushY /= length;

            x += pushX * 2.0f; // Push by const distance
            y += pushY * 2.0f;
        } else {
            x = previousX;
            y = previousY;
        }

        controller.setVelocity(controller.getVelocity().multiply(0.5f)); // Slow down after collision
        return new Vector2(x, y);
    }



    public boolean isCollidingWithObstacle(Rectangle obstacle, float x, float y, ImageIcon carModel, float rotationAngle) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x + carModel.getIconWidth() / 2.0, y + carModel.getIconHeight() / 2.0);
        transform.rotate(Math.toRadians(rotationAngle));

        Rectangle carBounds = new Rectangle(-carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2,
                carModel.getIconWidth(), carModel.getIconHeight());
        Shape rotatedHitbox = transform.createTransformedShape(carBounds);

        return rotatedHitbox.intersects(obstacle);
    }

    private boolean isCollidingWithFrame(float x, float y, ImageIcon carModel, float rotationAngle) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x + carModel.getIconWidth() / 2.0, y + carModel.getIconHeight() / 2.0);
        transform.rotate(Math.toRadians(rotationAngle));

        Rectangle carBounds = new Rectangle(-carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2,
                carModel.getIconWidth(), carModel.getIconHeight());
        Shape rotatedHitbox = transform.createTransformedShape(carBounds);
        Rectangle bounds = rotatedHitbox.getBounds();

        Rectangle frameBounds = GameScreen.getFrameBounds();
        return bounds.x < 0 ||
                bounds.y < 0 ||
                bounds.x + bounds.width > frameBounds.width ||
                bounds.y + bounds.height > frameBounds.height;
    }

    private Vector2 handleFrameCollision(float x, float y,
                                         TopDownCarController controller) {
        Rectangle frameBounds = GameScreen.getFrameBounds();

        // New position, make sure that car stays in frame
        float newX = Math.max(0, Math.min(x, frameBounds.width ));
        float newY = Math.max(0, Math.min(y, frameBounds.height));

        // If position changed, there's collision
        if (newX != x || newY != y) {
            controller.setVelocity(controller.getVelocity().multiply(0.5f));
        }

        return new Vector2(newX, newY);
    }


}