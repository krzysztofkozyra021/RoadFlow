package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Lowrider extends Car {
    private static final ImageIcon carModel = new ImageIcon("src/main/java/pl/roadflow/assets/cars/car16_armygreen.png");
    private static CarParameters getLowriderParams() {
        return new CarParameters(
                0.6f,     // accelerationFactor (0.1 - 1.0) - laid-back acceleration
                1.1f,     // minSpeedToTurn (0 - 2) - needs momentum to turn
                0.8f,     // driftFactor (0.5f-0.9f) - smooth sliding capability
                45f,      // maxSpeed (1 - 100) - cruising speed
                0.03f,    // grip/friction (0.01 - 0.1) - low grip for style
                6f        // turnFactor (1 - 15) - relaxed turning style
        );
    }

    public Lowrider() {
        super(getLowriderParams(),carModel);
    }

    public Lowrider(CarParameters carParameters) {
        super(carParameters,carModel);
    }
}
