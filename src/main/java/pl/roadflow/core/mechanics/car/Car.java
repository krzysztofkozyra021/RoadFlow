package src.main.java.pl.roadflow.core.mechanics.car;

import src.main.java.pl.roadflow.core.mechanics.car.controller.CarInputHandler;
import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.utils.Vector2;
import src.main.java.pl.roadflow.utils.consts.GameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.ArrayList;

public class Car {
    private CarInputHandler carInputHandler;
    private float x, y;
    private static final float VELOCITY_SCALE = 1.0f;
    private ImageIcon carModel = new ImageIcon(GameConsts.CAR_FILE_PATH);
    private char currentPositionTile = ' ';
    private Rectangle hitbox;
    private List<Rectangle> obstacles;
    private static final float BOUNCE_FACTOR = 0.5f;
    private static final int TILE_SIZE = 32;
    private float lastSafeX;
    private float lastSafeY;
    private int stuckCounter = 0;
    private static final int STUCK_THRESHOLD = 90;

    public Car(int startX, int startY) {
        this.x = startX - carModel.getIconWidth() / 2;
        this.y = startY - carModel.getIconHeight() / 2;
        this.lastSafeX = this.x;
        this.lastSafeY = this.y;
        this.carInputHandler = new CarInputHandler();
        this.hitbox = new Rectangle((int) x, (int) y, carModel.getIconWidth(), carModel.getIconHeight());
        this.obstacles = new ArrayList<>();
    }

    private boolean isCompletelyOnRoad(char currentTile) {
        int leftTile = (int)((x) / TILE_SIZE);
        int rightTile = (int)((x + carModel.getIconWidth()) / TILE_SIZE);
        int topTile = (int)((y) / TILE_SIZE);
        int bottomTile = (int)((y + carModel.getIconHeight()) / TILE_SIZE);

        boolean foundValidTile = false;
        for (int tileX = leftTile; tileX <= rightTile; tileX++) {
            for (int tileY = topTile; tileY <= bottomTile; tileY++) {
                if (isOnRoadOrGroundTile(currentTile)) {
                    foundValidTile = true;
                }
            }
        }
        return foundValidTile;
    }

    public void update() {
        float previousX = x;
        float previousY = y;

        carInputHandler.update();

        TopDownCarController controller = carInputHandler.topDownCarController;
        controller.applySteering();
        controller.applyEngineForce();

        Vector2 velocity = controller.getVelocity();

        if (isCompletelyOnRoad(currentPositionTile)) {
            // Try moving
            x += velocity.x * VELOCITY_SCALE;
            y += velocity.y * VELOCITY_SCALE;

            // Check for collisions
            boolean collision = false;
            for (Rectangle obstacle : obstacles) {
                if (isCollidingWithObstacle(obstacle)) {
                    collision = true;

                    // Calculate push direction
                    float centerX = obstacle.x + obstacle.width / 2;
                    float centerY = obstacle.y + obstacle.height / 2;
                    float dirX = x - centerX;
                    float dirY = y - centerY;

                    // Normalize direction
                    float length = (float) Math.sqrt(dirX * dirX + dirY * dirY);
                    if (length > 0) {
                        dirX /= length;
                        dirY /= length;

                        // Push away from obstacle
                        x = previousX + dirX * BOUNCE_FACTOR;
                        y = previousY + dirY * BOUNCE_FACTOR;
                    } else {
                        x = previousX;
                        y = previousY;
                    }

                    // Reduce velocity
                    controller.setVelocity(controller.getVelocity().multiply(0.5f));
                    stuckCounter++;
                    break;
                }
            }

            if (!collision) {
                controller.setVelocity(velocity);
                lastSafeX = x;
                lastSafeY = y;
                stuckCounter = 0;
            }

            if (stuckCounter > STUCK_THRESHOLD) {
                x = lastSafeX;
                y = lastSafeY;
                controller.stopCar();
                stuckCounter = 0;
            }
        } else {
            x = previousX;
            y = previousY;
            controller.stopCar();

            stuckCounter++;
            if (stuckCounter > STUCK_THRESHOLD) {
                x = lastSafeX;
                y = lastSafeY;
                controller.stopCar();
                stuckCounter = 0;
            }
        }
    }

    public boolean isCollidingWithObstacle(Rectangle obstacle) {
        AffineTransform transform = new AffineTransform();
        transform.translate(x + carModel.getIconWidth() / 2, y + carModel.getIconHeight() / 2);
        transform.rotate(Math.toRadians(carInputHandler.topDownCarController.rotationAngle));

        Shape rotatedHitbox = transform.createTransformedShape(
                new Rectangle(-carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2,
                        carModel.getIconWidth(), carModel.getIconHeight())
        );

        return rotatedHitbox.intersects(obstacle);
    }

    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();

        // Draw car
        g2d.translate(x + carModel.getIconWidth() / 2, y + carModel.getIconHeight() / 2);
        g2d.rotate(Math.toRadians(carInputHandler.topDownCarController.rotationAngle));
        carModel.paintIcon(null, g2d, -carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2);

        // Draw debug visuals
        if (GameConsts.DEBUG_MODE == 1) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Rectangle(-carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2,
                    carModel.getIconWidth(), carModel.getIconHeight()));

            // Draw obstacles
            g2d.setTransform(old);
            g2d.setColor(Color.BLUE);
            for (Rectangle obstacle : obstacles) {
                g2d.draw(obstacle);
            }
        }

        g2d.setTransform(old);
    }

    public void addObstacle(Rectangle obstacle) {
        obstacles.add(obstacle);
    }

    public void clearObstacles() {
        obstacles.clear();
    }

    public void handleKeyPressed(int keyCode) {
        carInputHandler.handleKeyPressed(keyCode);
    }

    public void handleKeyReleased(int keyCode) {
        carInputHandler.handleKeyReleased(keyCode);
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public char setCurrentPositionTile(char tile) {
        return currentPositionTile = tile;
    }

    public boolean isOnRoadOrGroundTile(char tile) {
        return tile == 'D' || tile == 'P' || tile == 'S' || tile == 'G' ||
                tile == '1' || tile == '2' || tile == '3' || tile == '4' ||
                tile == 'R';
    }
}