package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Suv extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car10_armygreen.png");
    private static CarParameters getSuvParams() {
        return new CarParameters(
                0.6f,     // accelerationFactor (0.1 - 1.0) - confident SUV acceleration
                1.1f,     // minSpeedToTurn (0 - 2) - higher stance turning
                0.5f,     // driftFactor (0.5f-0.9f) - stability-focused cornering
                55f,      // maxSpeed (1 - 100) - practical SUV speed
                0.08f,    // grip/friction (0.01 - 0.1) - all-terrain grip
                6f        // turnFactor (1 - 15) - controlled body roll
        );
    }

    public Suv() {
        super(getSuvParams(),carModel);
    }

    public Suv(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
