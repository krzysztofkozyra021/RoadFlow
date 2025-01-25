package pl.roadflow.core.mechanics.stats;

public class CarParameters {
    private float accelerationFactor;   // accelerationFactor (0.1 - 1.0)
    private float minSpeedToTurn;        // minSpeedToTurn (0 - 2)
    private float driftFactor;          // driftFactor (0.5f-0.9f) - 0.5 = less drift , 0.9 = more drift
    private float maxSpeed;          // maxSpeed (1 - 100)
    private float grip;                 // grip/friction (0.01 - 0.1)
    private float turnFactor;          // Turn Factor (1 - 15) - 1 = very hard to turn, 15 = very easy to turn

    public CarParameters(float accelerationFactor, float minSpeedToTurn, float driftFactor, float maxSpeed, float grip, float turnFactor) {
        this.accelerationFactor = accelerationFactor;
        this.minSpeedToTurn = minSpeedToTurn;
        this.driftFactor = driftFactor;
        this.maxSpeed = maxSpeed;
        this.grip = grip;
        this.turnFactor = turnFactor;
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

    public float getGrip() {
        return grip;
    }

    public float getTurnFactor() {
        return turnFactor;
    }
}
