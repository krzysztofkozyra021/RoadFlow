package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class FamilySedan extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car4_armygreen.png");
    private static CarParameters getFamilySedanParams() {
        return new CarParameters(
                0.5f,     // accelerationFactor (0.1 - 1.0) - comfortable, smooth acceleration
                1.2f,     // minSpeedToTurn (0 - 2) - stable turning characteristics
                0.5f,     // driftFactor (0.5f-0.9f) - minimal drift for safety
                50f,      // maxSpeed (1 - 100) - balanced highway speed
                0.06f,    // grip/friction (0.01 - 0.1) - reliable road holding
                8f        // turnFactor (1 - 15) - predictable turning behavior
        );
    }

    public FamilySedan() {
        super(getFamilySedanParams(),carModel);
    }

    public FamilySedan(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
