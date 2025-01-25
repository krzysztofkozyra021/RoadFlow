package pl.roadflow.core.mechanics.car.carTypes;

import pl.roadflow.core.mechanics.car.Car;
import pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Truck extends Car {
    private static CarParameters getTruckParams() {
        return new CarParameters(
                0.3f,     // accelerationFactor (0.1 - 1.0) - heavy load acceleration
                0.3f,     // minSpeedToTurn (0 - 2) - wide turning radius
                0.3f,     // driftFactor (0.5f-0.9f) - stability-focused
                40f,      // maxSpeed (1 - 100) - safety-limited speed
                0.09f,    // grip/friction (0.01 - 0.1) - heavy vehicle grip
                4f        // turnFactor (1 - 15) - careful, wide turns
        );
    }

    public Truck(String color) {
        super(getTruckParams(),
                new ImageIcon("src/main/resources/assets/cars/car13_" + color + ".png"));
    }
}
