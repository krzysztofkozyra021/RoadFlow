package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Truck extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car13_armygreen.png");
    private static CarParameters getDefaultSportCarParams() {
        return new CarParameters(
                0.3f,     // accelerationFactor (0.1 - 1.0) - heavy load acceleration
                0.3f,     // minSpeedToTurn (0 - 2) - wide turning radius
                0.3f,     // driftFactor (0.5f-0.9f) - stability-focused
                40f,      // maxSpeed (1 - 100) - safety-limited speed
                0.09f,    // grip/friction (0.01 - 0.1) - heavy vehicle grip
                4f        // turnFactor (1 - 15) - careful, wide turns
        );
    }

    public Truck() {
        super(getDefaultSportCarParams(),carModel);
    }

    public Truck(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
