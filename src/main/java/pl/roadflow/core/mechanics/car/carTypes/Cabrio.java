package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Cabrio extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car15_armygreen.png");
    private static CarParameters getCabrioParams() {
        return new CarParameters(
                0.8f,     // accelerationFactor (0.1 - 1.0) - sporty acceleration
                1.2f,     // minSpeedToTurn (0 - 2) - agile turning at low speeds
                0.7f,     // driftFactor (0.5f-0.9f) - balanced drift capability
                60f,      // maxSpeed (1 - 100) - good top speed for a light car
                0.05f,    // grip/friction (0.01 - 0.1) - moderate road grip
                10f       // turnFactor (1 - 15) - responsive handling
        );
    }

    public Cabrio() {
        super(getCabrioParams(),carModel);
    }

    public Cabrio(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
