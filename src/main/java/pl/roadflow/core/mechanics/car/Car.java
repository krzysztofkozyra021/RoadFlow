package src.main.java.pl.roadflow.core.mechanics.car;

import src.main.java.pl.roadflow.core.mechanics.car.controller.CarInputHandler;


public class Car {
    CarInputHandler carInputHandler;

    public Car() {
        carInputHandler = new CarInputHandler();
    }
}
