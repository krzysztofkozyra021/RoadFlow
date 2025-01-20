package src.main.java.pl.roadflow.core.mechanics.car.controller;

import src.main.java.pl.roadflow.utils.Vector2;

import java.awt.event.KeyEvent;

public enum VehicleKeysControl {
    // Arrows
    ACCELERATION(KeyEvent.VK_UP, new Vector2(0, 1)),
    BRAKE(KeyEvent.VK_DOWN, new Vector2(0, -1)),
    LEFT(KeyEvent.VK_LEFT, new Vector2(-1, 0)),
    RIGHT(KeyEvent.VK_RIGHT, new Vector2(1, 0)),

    // WASD
    W_ACCELERATION(KeyEvent.VK_W, new Vector2(0, 1)),
    S_BRAKE(KeyEvent.VK_S, new Vector2(0, -1)),
    A_LEFT(KeyEvent.VK_A, new Vector2(-1, 0)),
    D_RIGHT(KeyEvent.VK_D, new Vector2(1, 0));

    private final int keyCode;
    private final Vector2 inputVector;

    VehicleKeysControl(int keyCode, Vector2 inputVector) {
        this.keyCode = keyCode;
        this.inputVector = inputVector;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public Vector2 getInputVector() {
        return inputVector;
    }

    public static VehicleKeysControl getByKeyCode(int keyCode) {
        for (VehicleKeysControl control : values()) {
            if (control.keyCode == keyCode) {
                return control;
            }
        }
        return null;
    }
}

