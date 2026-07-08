class Circle {
    private double rad;
    final double PI = 3.14;

    public Circle(double r) {
        rad = r;
    }

    public void setRad(double r) {
        if(r < 0) {
            rad = 0;
            return;
        }
        rad = r;
    }

    public double getRad() {
        return rad;
    }

    public double getArea() {
        return getPower(rad) * PI;
    }

    private double getPower(double rad) {
        return rad * rad;
    }
}

public class P198_InfoHideCircle {
    public static void main(String[] args) {
        Circle c = new Circle(1.5);
        printResult(c);

        c.setRad(3.4);
        printResult(c);
    }

    public static void printResult(Circle c) {
        System.out.println("Rad = " + c.getRad());
        System.out.println("Area = " + c.getArea());
    }
}