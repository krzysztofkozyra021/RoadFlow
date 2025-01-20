package src.main.java.pl.roadflow.core.mechanics.car.controller;

import src.main.java.pl.roadflow.utils.Vector2;

public interface IVehicleInput {
    void setInputVector(Vector2 inputVector);
    float getRotationAngle();
}
