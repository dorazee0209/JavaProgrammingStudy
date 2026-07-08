class Circle {
    static final double PI = 3.1415; // <- Point!
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }
    void showPerimeter() {
        System.out.println("Perimeter: " + ((radius*2)*PI));
    }
    void showArea() {
        System.out.println("Area: " +  returnPower(radius) * PI);
    }
    private static double returnPower(double radius) {
        return radius * radius;
    }
}

public class P223_CircleConstPI {
    public static void main(String[] args) {
        Circle c = new Circle(1.2);
        c.showPerimeter();
        c.showArea();
    }
}