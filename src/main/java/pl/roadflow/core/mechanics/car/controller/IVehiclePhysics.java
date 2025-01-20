package src.main.java.pl.roadflow.core.mechanics.car.controller;

import src.main.java.pl.roadflow.utils.Vector2;

public interface IVehiclePhysics {
    void applyEngineForce();
    void applySteering();
    Vector2 getVelocity();
    float getCurrentSpeed();
}