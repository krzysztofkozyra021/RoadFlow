package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Tank extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car9_armygreen.png");
    private static CarParameters getDefaultSportCarParams() {
        return new CarParameters(
                0.7f,
                0.5f,
                0.96f,
                3f,
                0.96f
        );
    }

    public Tank() {
        super(getDefaultSportCarParams(),carModel);
    }

    public Tank(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
