package src.main.java.pl.roadflow.core.mechanics.car;

import src.main.java.pl.roadflow.core.mechanics.car.controller.CarInputHandler;
import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.core.mechanics.car.physics.CollisionHandler;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;
import src.main.java.pl.roadflow.utils.Vector2;
import src.main.java.pl.roadflow.utils.consts.GameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Car {
    private CarInputHandler carInputHandler;
    private CollisionHandler collisionHandler;
    private float x, y;
    private static final float VELOCITY_SCALE = 1.0f;
    private ImageIcon carModel = new ImageIcon(GameConsts.CAR_FILE_PATH);
    private int currentPositionTile;
    private Rectangle hitbox;

    public Car(int startX, int startY, CarParameters carParameters) {
        this.collisionHandler = new CollisionHandler(this);
        this.x = startX - carModel.getIconWidth() / 2;
        this.y = startY - carModel.getIconHeight() / 2;
        this.carInputHandler = new CarInputHandler(carParameters);
        this.hitbox = new Rectangle((int) x, (int) y, carModel.getIconWidth(), carModel.getIconHeight());
    }


    public ImageIcon getCarModel() {
        return carModel;
    }

    public void update() {
        float previousX = x;
        float previousY = y;

        carInputHandler.update();
        TopDownCarController controller = carInputHandler.getTopDownCarController();
        controller.applySteering();
        controller.applyEngineForce();

        Vector2 velocity = controller.getVelocity();
        float stepX = velocity.x * VELOCITY_SCALE / 5; // We divide step into 5 mini steps
        float stepY = velocity.y * VELOCITY_SCALE / 5;

        for (int i = 0; i < 5; i++) {
            // Update step position
            x += stepX;
            y += stepY;

            // Update hitbox
            hitbox.setBounds((int) x, (int) y, carModel.getIconWidth(), carModel.getIconHeight());

            // Handle collision
            Vector2 collision = collisionHandler.handleCollisions(
                     x, y, previousX, previousY, controller
            );

            x = collision.x;
            y = collision.y;

        }
    }

    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();

        // Draw car
        g2d.translate(x + carModel.getIconWidth() / 2, y + carModel.getIconHeight() / 2);
        g2d.rotate(Math.toRadians(90 + carInputHandler.topDownCarController.rotationAngle));
        carModel.paintIcon(null, g2d, -carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2);

        // Draw debug visuals
        if (GameConsts.DEBUG_MODE == 1) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Rectangle(-carModel.getIconWidth() / 2, -carModel.getIconHeight() / 2,
                    carModel.getIconWidth(), carModel.getIconHeight()));
        }

        g2d.setTransform(old);
    }


    public void handleKeyPressed(int keyCode) {
        carInputHandler.handleKeyPressed(keyCode);
    }

    public void handleKeyReleased(int keyCode) {
        carInputHandler.handleKeyReleased(keyCode);
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public int setCurrentPositionTile(int tile) {
        return currentPositionTile = tile;
    }

    public boolean isOnRoadOrGroundTile(char tile) {
        return tile == 'D' || tile == 'P' || tile == 'S' || tile == 'G' ||
                tile == '1' || tile == '2' || tile == '3' || tile == '4' ||
                tile == 'R';
    }
}