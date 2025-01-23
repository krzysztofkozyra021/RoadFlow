package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Taxi extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car8_armygreen.png");
    private static CarParameters getTruckParams() {
        return new CarParameters(
                0.50f,     // accelerationFactor (0.1 - 1.0) - reliable urban acceleration
                0.9f,     // minSpeedToTurn (0 - 2) - city-smart turning
                0.6f,     // driftFactor (0.5f-0.9f) - controlled urban cornering
                65f,      // maxSpeed (1 - 100) - good city-highway speed
                0.06f,    // grip/friction (0.01 - 0.1) - dependable road grip
                8f        // turnFactor (1 - 15) - experienced driver handling
        );
    }

    public Taxi() {
        super(getTruckParams(),carModel);
    }

    public Taxi(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
