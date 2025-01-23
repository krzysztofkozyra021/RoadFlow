package src.main.java.pl.roadflow.core.mechanics.car.controller;

import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;
import src.main.java.pl.roadflow.utils.Vector2;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class CarInputHandler {

    private final Map<Integer, Boolean> keyStates = new HashMap<>();
    public TopDownCarController topDownCarController;
    private Vector2 inputVector;

    public CarInputHandler(CarParameters carParameters) {
        topDownCarController = new TopDownCarController(carParameters);
        inputVector = new Vector2();
        for (VehicleKeysControl control : VehicleKeysControl.values()) {
            keyStates.put(control.getKeyCode(), false);
        }
    }

    public TopDownCarController getTopDownCarController() {
        return topDownCarController;
    }

    public void update() {
        float steeringInput = 0;
        float accelerationInput = 0;

        for (Map.Entry<Integer, Boolean> entry : keyStates.entrySet()) {
            if (entry.getValue()) {
                VehicleKeysControl control = VehicleKeysControl.getByKeyCode(entry.getKey());
                if (control != null) {
                    Vector2 input = control.getInputVector();
                    if (input.x != 0) {
                        steeringInput += input.x;
                    }
                    if (input.y != 0) {
                        accelerationInput += input.y;
                    }
                }
            }
        }

        steeringInput = Math.max(-1, Math.min(1, steeringInput));
        accelerationInput = Math.max(-1, Math.min(1, accelerationInput));

        Vector2 finalInput = new Vector2(steeringInput, accelerationInput);
        topDownCarController.setInputVector(finalInput);
    }

    public void handleKeyPressed(int keyCode) {
        if (keyStates.containsKey(keyCode)) {
            keyStates.put(keyCode, true); // mark key as pressed
        }
    }

    public void handleKeyReleased(int keyCode) {
        if (keyStates.containsKey(keyCode)) {
            keyStates.put(keyCode, false); // mark key as released
        }
    }




}
