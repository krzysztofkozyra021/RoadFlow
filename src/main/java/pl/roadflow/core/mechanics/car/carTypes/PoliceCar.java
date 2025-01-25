package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class PoliceCar extends Car {
    private static CarParameters getPoliceCarParams() {
        return new CarParameters(
                0.9f,     // accelerationFactor (0.1 - 1.0) - powerful pursuit acceleration
                1.3f,     // minSpeedToTurn (0 - 2) - trained handling capability
                0.7f,     // driftFactor (0.5f-0.9f) - controlled performance sliding
                75f,      // maxSpeed (1 - 100) - high pursuit speed
                0.06f,    // grip/friction (0.01 - 0.1) - professional handling grip
                9f        // turnFactor (1 - 15) - responsive pursuit handling
        );
    }

    public PoliceCar(String color) {
        super(getPoliceCarParams(),
                new ImageIcon("src/main/java/pl/roadflow/assets/cars/car6_" + color + ".png"));
    }
}
