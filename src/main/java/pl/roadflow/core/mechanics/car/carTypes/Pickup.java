package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Pickup extends Car {
    private static CarParameters getPickupParams() {
        return new CarParameters(
                0.65f,    // accelerationFactor (0.1 - 1.0) - strong utility acceleration
                1.3f,     // minSpeedToTurn (0 - 2) - higher clearance turning
                0.55f,    // driftFactor (0.5f-0.9f) - work-oriented stability
                60f,      // maxSpeed (1 - 100) - capable hauling speed
                0.08f,    // grip/friction (0.01 - 0.1) - rugged terrain grip
                5f        // turnFactor (1 - 15) - utility-focused handling
        );
    }

    public Pickup(String color) {
        super(getPickupParams(),
                new ImageIcon("src/main/java/pl/roadflow/assets/cars/car11_" + color + ".png"));
    }
}
