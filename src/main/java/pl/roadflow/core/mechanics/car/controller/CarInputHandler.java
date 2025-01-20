package src.main.java.pl.roadflow.core.mechanics.car.controller;

import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;
import src.main.java.pl.roadflow.utils.Vector2;

import java.awt.event.KeyEvent;

public class CarInputHandler {
    public TopDownCarController topDownCarController;
    private Vector2 inputVector;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public CarInputHandler(CarParameters carParameters) {
        topDownCarController = new TopDownCarController(carParameters);
        inputVector = new Vector2();
    }

    public TopDownCarController getTopDownCarController() {
        return topDownCarController;
    }

    public Vector2 getInputVector() {
        return inputVector;
    }

    public void update() {
        // We reset the input vector
        inputVector = new Vector2(0, 0);

        // We set the input vector based on the keys pressed
        // Vertical (y)
        if (upPressed) inputVector.y = 1.0f;
        if (downPressed) inputVector.y = -1.0f;

        // Horizontal (x)
        if (leftPressed) inputVector.x = -1.0f;
        if (rightPressed) inputVector.x = 1.0f;

        topDownCarController.setInputVector(inputVector);
    }

    public void handleKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
        }
    }

    public void handleKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }
    }
}
