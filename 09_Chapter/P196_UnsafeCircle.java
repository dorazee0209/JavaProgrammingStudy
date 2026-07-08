class Circle {
    double rad;
    final double PI = 3.14;

    public Circle(double rad) {
        this.rad = rad;
    }

    public void setRad(double rad) {
        if(rad < 0) {
            rad = 0;
            return;
        }
        this.rad = rad;
    }

    public double getArea() {
        return getPower(rad) * PI;
    }

    private double getPower(double rad) {
        return rad * rad;
    }
}

public class P196_UnsafeCircle {
    public static void main(String[] args) {
        Circle c = new Circle(1.5);
        System.out.println(c.getArea());

        c.setRad(2.5);
        System.out.println(c.getArea());

        c.setRad(-3.3);
        System.out.println(c.getArea());

        c.rad = -4.5; // inappropriate access!!
        System.out.println(c.getArea());
    }
}