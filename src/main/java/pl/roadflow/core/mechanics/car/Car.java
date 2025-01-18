package src.main.java.pl.roadflow.core.mechanics.car;

import src.main.java.pl.roadflow.core.mechanics.car.controller.CarInputHandler;
import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.utils.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;


public class Car {
    private CarInputHandler carInputHandler;
    private float x, y;
    private int width = 36;
    private int height = 12;
    private Color color = Color.RED;
    private static final float VELOCITY_SCALE = 5.0f;

    public Car(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.carInputHandler = new CarInputHandler();
    }

    public void update() {
        // Update controller
        carInputHandler.update();

        // Get controller
        TopDownCarController controller = carInputHandler.topDownCarController;

        controller.ApplySteering();
        controller.ApplyEngineForce();

        // Get entry values
        Vector2 inputVector = carInputHandler.getInputVector();
        float angleInRadians = (float)Math.toRadians(controller.rotationAngle + 180);

        // Calculate new position
        x += Math.cos(angleInRadians) * inputVector.y * VELOCITY_SCALE;
        y += Math.sin(angleInRadians) * inputVector.y * VELOCITY_SCALE;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();

        int drawX = (int)x;
        int drawY = (int)y;

        g2d.translate(drawX + width/2, drawY + height/2);
        g2d.rotate(Math.toRadians(carInputHandler.topDownCarController.rotationAngle + 180));
        g2d.translate(-(drawX + width/2), -(drawY + height/2));


        // Car body
        g2d.setColor(color);
        g2d.fillRect(drawX, drawY, width, height);

        g2d.setTransform(old);
    }

    // Methods to handle key events
    public void handleKeyPressed(int keyCode) {
        carInputHandler.handleKeyPressed(keyCode);
    }

    public void handleKeyReleased(int keyCode) {
        carInputHandler.handleKeyReleased(keyCode);
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
