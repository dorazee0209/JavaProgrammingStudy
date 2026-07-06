package com.w.smart;

public class Circle {
    double rad;
    final double PI;

    public Circle(double r) {
        rad  = r;
        PI = 3.14;
    }

    public double getArea() {
        return getPower2(rad) * PI;
    }

    double getPower2(double rad) {
        return rad * rad;
    }
}