public class CircleUpper {
    double rad;
    final double PI;

    public CircleUpper(double r) {
        rad  = r;
        PI = 3.14;
    }

    public double getArea() {
        return getPower2(rad) * PI;
    }

    double getPower2(int rad) {
        return rad * rad;
    }
}

