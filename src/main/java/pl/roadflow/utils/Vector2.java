package pl.roadflow.utils;

public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this(0f, 0f);
    }

    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public Vector2 normalize() {
        float mag = magnitude();
        if (mag == 0) {
            return new Vector2(0, 0);
        }
        return new Vector2(x / mag, y / mag);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 multiply(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }
    public Vector2 divide(float scalar) {
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public static float dot(Vector2 a, Vector2 b) {
        return a.x * b.x + a.y * b.y;
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);

    }
}