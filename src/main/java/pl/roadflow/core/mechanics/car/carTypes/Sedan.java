package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class Sedan extends Car {
    private static CarParameters getSedanParams() {
        return new CarParameters(
                0.75f,    // accelerationFactor (0.1 - 1.0) - refined, smooth power delivery
                1.3f,     // minSpeedToTurn (0 - 2) - balanced luxury handling
                0.6f,     // driftFactor (0.5f-0.9f) - controlled cornering behavior
                70f,      // maxSpeed (1 - 100) - executive class speed
                0.07f,    // grip/friction (0.01 - 0.1) - premium tire grip
                9f        // turnFactor (1 - 15) - comfortable yet precise steering
        );
    }

    public Sedan(String color) {
        super(getSedanParams(),
                new ImageIcon("src/main/java/pl/roadflow/assets/cars/car5_" + color + ".png"));
    }
}
