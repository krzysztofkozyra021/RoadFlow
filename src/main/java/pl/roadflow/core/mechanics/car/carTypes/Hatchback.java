package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Hatchback extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car3_armygreen.png");
    private static CarParameters getDefaultSportCarParams() {
        return new CarParameters(
                0.7f,
                0.5f,
                0.96f,
                3f,
                0.96f
        );
    }

    public Hatchback() {
        super(getDefaultSportCarParams(),carModel);
    }

    public Hatchback(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
