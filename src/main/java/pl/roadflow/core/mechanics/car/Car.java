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
    private final CarInputHandler carInputHandler;
    private final CollisionHandler collisionHandler;
    private float x, y;
    private static final float VELOCITY_SCALE = 1.0f;
    private final ImageIcon carModelIcon;
    private int currentPositionTile;
    private final Rectangle hitbox;
    private final CarParameters carParameters;

    public Car(CarParameters carParameters, ImageIcon carModelIcon) {
        this.carParameters = carParameters;
        this.carModelIcon = carModelIcon;
        this.collisionHandler = new CollisionHandler(this);
        this.carInputHandler = new CarInputHandler(carParameters);
        this.hitbox = new Rectangle((int) x, (int) y, carModelIcon.getIconWidth(), carModelIcon.getIconHeight());
    }


    public ImageIcon getCarModelIcon() {
        return carModelIcon;
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
            hitbox.setBounds((int) x, (int) y, carModelIcon.getIconWidth(), carModelIcon.getIconHeight());

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
        g2d.translate(x + (double) carModelIcon.getIconWidth() / 2, y + (double) carModelIcon.getIconHeight() / 2);
        g2d.rotate(Math.toRadians(90 + carInputHandler.topDownCarController.rotationAngle));
        carModelIcon.paintIcon(null, g2d, -carModelIcon.getIconWidth() / 2, -carModelIcon.getIconHeight() / 2);

        // Draw debug visuals
        if (GameConsts.DEBUG_MODE == 1) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Rectangle(-carModelIcon.getIconWidth() / 2, -carModelIcon.getIconHeight() / 2,
                    carModelIcon.getIconWidth(), carModelIcon.getIconHeight()));
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

    public void setCurrentPositionTile(int tile) {
        currentPositionTile = tile;
    }

    public boolean isOnRoadOrGroundTile(char tile) {
        return tile == 'D' || tile == 'P' || tile == 'S' || tile == 'G' ||
                tile == '1' || tile == '2' || tile == '3' || tile == '4' ||
                tile == 'R';
    }
    
    public float getCarMaxSpeed(){
        return carParameters.getMaxSpeed();
    }

    public float getCarGrip(){
        return carParameters.getDrag();
    }

    public float getCarAcceleration(){
        return carParameters.getAccelerationFactor();
    }
    
    
}