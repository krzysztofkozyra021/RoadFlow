package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class MiniVan extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car1_armygreen.png");
    private static CarParameters getDefaultSportCarParams() {
        return new CarParameters(
                0.6f,
                0.3f,
                0.90f,
                10f,
                0.93f
        );
    }

    public MiniVan() {
        super(getDefaultSportCarParams(),carModel);
    }

    public MiniVan(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
