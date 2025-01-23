package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class RaceCar extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car2_armygreen.png");
    private static CarParameters getRaceCarParams() {
        return new CarParameters(
                0.9f,  // accelerationFactor - best in class acceleration
                0.4f,  // minSpeedToTurn - most agile turning capability
                0.65f, // driftFactor - professional drift handling
                100f,  // maxSpeed - maximum speed possible
                0.05f, // grip - balanced for high-speed control
                9f     // turnFactor - precise racing turns
        );
    }

    public RaceCar() {
        super(getRaceCarParams(),carModel);
    }

    public RaceCar(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
