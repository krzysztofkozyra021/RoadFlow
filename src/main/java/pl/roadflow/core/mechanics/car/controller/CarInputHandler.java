package src.main.java.pl.roadflow.core.mechanics.car.controller;

import src.main.java.pl.roadflow.core.mechanics.car.controller.impl.TopDownCarController;

public class CarInputHandler {

    public TopDownCarController topDownCarController;

    public CarInputHandler() {
        topDownCarController = new TopDownCarController();
    }
}
