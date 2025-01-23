package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class MiniVan extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car1_armygreen.png");
    private static CarParameters getMiniVanParams() {
        return new CarParameters(
                0.4f,     // accelerationFactor (0.1 - 1.0) - practical, unhurried acceleration
                1.3f,     // minSpeedToTurn (0 - 2) - careful turning required
                0.5f,     // driftFactor (0.5f-0.9f) - minimal drift for family safety
                40f,      // maxSpeed (1 - 100) - family-oriented top speed
                0.08f,    // grip/friction (0.01 - 0.1) - safe, reliable grip
                5f        // turnFactor (1 - 15) - steady, predictable turns
        );
    }

    public MiniVan() {
        super(getMiniVanParams(),carModel);
    }

    public MiniVan(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
