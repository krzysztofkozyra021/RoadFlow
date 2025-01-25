package src.main.java.pl.roadflow.core.mechanics.car.carTypes;

import src.main.java.pl.roadflow.core.mechanics.car.Car;
import src.main.java.pl.roadflow.core.mechanics.stats.CarParameters;

import javax.swing.*;

public class MuscleCar extends Car {
    public static CarParameters getMuscleCarParams() {
        return new CarParameters(
                0.65f, // accelerationFactor - strong but slightly less than race car
                0.6f,  // minSpeedToTurn - better than standard car but not race-level
                0.7f,  // driftFactor - good drift capability for power slides
                85f,   // maxSpeed - high but not race car level
                0.06f, // grip - slightly loose for muscle car feel
                7.5f   // turnFactor - responsive but not as sharp as sports cars
        );
    }

    public MuscleCar(String color) {
        super(getMuscleCarParams(),
                new ImageIcon("src/main/java/pl/roadflow/assets/cars/car14_" + color + ".png"));
    }
}
