package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Hatchback extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car3_armygreen.png");
    private static CarParameters getHatchbackParams() {
        return new CarParameters(
                0.7f,     // accelerationFactor (0.1 - 1.0) - peppy performance
                1.0f,     // minSpeedToTurn (0 - 2) - balanced turning speed
                0.6f,     // driftFactor (0.5f-0.9f) - slight sportiness in corners
                55f,      // maxSpeed (1 - 100) - good all-round speed
                0.06f,    // grip/friction (0.01 - 0.1) - competent grip
                11f       // turnFactor (1 - 15) - nimble handling

        );
    }

    public Hatchback() {
        super(getHatchbackParams(),carModel);
    }

    public Hatchback(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
