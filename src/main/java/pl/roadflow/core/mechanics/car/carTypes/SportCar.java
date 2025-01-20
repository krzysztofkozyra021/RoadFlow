package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

public class SportCar extends Car {
    private static CarParameters getDefaultSportCarParams() {
        return new CarParameters(
                0.7f,
                0.5f,
                0.96f,
                20f,
                0.99f
        );
    }

    public SportCar(int startX, int startY) {
        super(startX, startY, getDefaultSportCarParams());
    }

    public SportCar(int startX, int startY, CarParameters carParameters) {
        super(startX, startY, carParameters);
    }
}