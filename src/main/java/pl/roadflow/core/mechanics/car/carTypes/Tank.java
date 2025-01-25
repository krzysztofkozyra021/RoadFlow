package pl.roadflow.core.mechanics.car.carTypes;

import pl.roadflow.core.mechanics.car.Car;
import pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Tank extends Car {
    private static CarParameters getTankParams() {
        return new CarParameters(
                0.15f,  // accelerationFactor - heavy vehicle acceleration
                0.25f,  // minSpeedToTurn - requires momentum to turn
                0.01f,  // driftFactor - minimal track sliding
                30f,   // maxSpeed - powerful but slow
                0.1f,  // grip - maximum terrain grip
                3f     // turnFactor - heavy, deliberate turning
        );
    }

    public Tank(String color) {
        super(getTankParams(),
                new ImageIcon("src/main/resources/assets/cars/car9_" + color + ".png"));
    }
}
