package src.main.java.pl.roadflow.core.mechanics.car;

import src.main.java.pl.roadflow.core.mechanics.car.controller.CarInputHandler;
import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.utils.Vector2;
import src.main.java.pl.roadflow.utils.consts.GameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;


public class Car {
    private CarInputHandler carInputHandler;
    private float x, y;
    private int width = 36;
    private int height = 12;
    private Color color = Color.RED;
    private static final float VELOCITY_SCALE = 5.0f;
    private ImageIcon carModel = new ImageIcon(GameConsts.CAR_FILE_PATH);

    public Car(int startX, int startY) {
        this.x = startX - carModel.getIconWidth()/2;
        this.y = startY - carModel.getIconHeight()/2;
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
        float angleInRadians = (float)Math.toRadians(controller.rotationAngle);

        // Calculate new position
        x += Math.cos(angleInRadians) * inputVector.y * VELOCITY_SCALE;
        y += Math.sin(angleInRadians) * inputVector.y * VELOCITY_SCALE;
    }

    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();

        // Move to center of car image asset
        g2d.translate(x + carModel.getIconWidth()/2, y + carModel.getIconHeight()/2);

        // Obrót wokół środka
        g2d.rotate(Math.toRadians(90 + carInputHandler.topDownCarController.rotationAngle));

        // Drawing with half offset of icon size
        carModel.paintIcon(null, g2d, -carModel.getIconWidth()/2, -carModel.getIconHeight()/2);

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
