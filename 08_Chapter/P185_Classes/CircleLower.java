public class CircleLower {
    double rad;
    final double PI;

    public CircleUpper(double r) {
        rad  = r;
        PI = 3.14;
    }

    public double getPerimeter() {
        return (rad * 2) * PI;
    }
}