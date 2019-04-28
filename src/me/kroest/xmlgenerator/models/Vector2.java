package me.kroest.xmlgenerator.models;

public class Vector2 {
    public double x;
    public double z;

    public Vector2(double x, double z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public String toString() {
        return x + "," + z;
    }
}
