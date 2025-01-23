package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Tank extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car9_armygreen.png");
    private static CarParameters getTankParams() {
        return new CarParameters(
                0.15f,  // accelerationFactor - heavy vehicle acceleration
                0.25f,  // minSpeedToTurn - requires momentum to turn
                0.01f,  // driftFactor - minimal track sliding
                30f,   // maxSpeed - powerful but slow
                0.1f,  // grip - maximum terrain grip
                3f     // turnFactor - heavy, deliberate turning
        );
    }

    public Tank() {
        super(getTankParams(),carModel);
    }

    public Tank(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
