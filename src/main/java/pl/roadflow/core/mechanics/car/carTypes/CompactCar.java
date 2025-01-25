package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class CompactCar extends Car {
    private static CarParameters getCompactCarParams() {
        return new CarParameters(
                0.6f,     // accelerationFactor (0.1 - 1.0) - modest city car acceleration
                0.8f,     // minSpeedToTurn (0 - 2) - excellent low-speed maneuverability
                0.5f,     // driftFactor (0.5f-0.9f) - minimal drift, stable cornering
                45f,      // maxSpeed (1 - 100) - typical city car speed
                0.07f,    // grip/friction (0.01 - 0.1) - good everyday grip
                12f       // turnFactor (1 - 15) - very easy to maneuver
        );
    }


    public CompactCar(String color) {
        super(getCompactCarParams(),
                new ImageIcon("src/main/java/pl/roadflow/assets/cars/car7_" + color + ".png"));
    }
}
