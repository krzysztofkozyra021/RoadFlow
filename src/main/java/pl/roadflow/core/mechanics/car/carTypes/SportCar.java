package pl.roadflow.core.mechanics.car.carTypes;

import pl.roadflow.core.mechanics.car.Car;
import pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class SportCar extends Car {
    private static CarParameters getSportCarParams() {
        return new CarParameters(
                0.8f,  // accelerationFactor - fast acceleration, responsive
                0.5f,  // minSpeedToTurn - agile turning at low speeds
                0.7f,  // driftFactor - controlled drift for sporty feel
                80f,   // maxSpeed - high top speed for performance
                0.06f, // grip - good road grip while maintaining sportiness
                8f     // turnFactor - sharp, precise turning
        );
    }

    public SportCar(String color) {
        super(getSportCarParams(),
                new ImageIcon("src/main/resources/assets/cars/car12_" + color + ".png"));
    }
}