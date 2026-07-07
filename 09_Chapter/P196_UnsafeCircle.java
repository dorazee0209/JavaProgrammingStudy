class Circle {
    double rad;
    fianl double PI = 3.14;

    public Circle(double rad) {
        this.rad = rad;
    }

    public void setRad(double rad) {
        if(rad < 0) {
            rad = 0;
            return;
        }
        this.rad = r;
    }

    public double getArea() {
        return getPower(rad) * PI;
    }

    protected double getPower(double rad) {
        return rad * rad;
    }
}

public class P196_UnsafeCircle {
    static void main(String[] args) {
        Circle c = new Circle(1.5);
        System.out.println(c.getArea());
    }
}