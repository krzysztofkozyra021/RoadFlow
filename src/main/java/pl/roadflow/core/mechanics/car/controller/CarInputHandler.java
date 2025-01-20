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
        // We reset input vector
        inputVector = new Vector2(0, 0);

        // We add vector from active keys
        for (Map.Entry<Integer, Boolean> entry : keyStates.entrySet()) {
            if (entry.getValue()) { // If key is pressed
                VehicleKeysControl control = VehicleKeysControl.getByKeyCode(entry.getKey());
                if (control != null) {
                    inputVector = inputVector.add(control.getInputVector());
                }
            }
        }

        // Pass active vector to controller
        topDownCarController.setInputVector(inputVector);
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
