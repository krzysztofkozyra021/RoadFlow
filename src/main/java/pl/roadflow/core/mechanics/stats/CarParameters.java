package src.main.java.pl.roadflow.core.mechanics.stats;

public class CarParameters {
    private float accelerationFactor;   // Base acceleration force
    private float minSpeedToTurn;     // Minimum speed required for turning
    private float driftFactor;         // How much lateral velocity is preserved (higher = more drift)
    private float maxSpeed;          // Maximum vehicle speed
    private float drag;               // Grip resistance (less = car stops faster)

    public CarParameters(float accelerationFactor, float minSpeedToTurn, float driftFactor, float maxSpeed, float drag) {
        this.accelerationFactor = accelerationFactor;
        this.minSpeedToTurn = minSpeedToTurn;
        this.driftFactor = driftFactor;
        this.maxSpeed = maxSpeed;
        this.drag = drag;
    }

    public float getAccelerationFactor() {
        return accelerationFactor;
    }

    public float getMinSpeedToTurn() {
        return minSpeedToTurn;
    }

    public float getDriftFactor() {
        return driftFactor;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getDrag() {
        return drag;
    }
}
