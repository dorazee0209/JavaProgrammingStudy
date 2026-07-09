class SimpleCalculator {
    static final double PI = 3.1415;

    double add(double n1, double n2) {
        return n1 + n2;
    }
    double min(double n1, double n2) {
        return n1 - n2;
    }
    double calCircleArea(double r) {
        return r * r * PI;
    }
    double calCirclePeri(double r) {
        return 2 * r * PI;
    }
}

public class P226_UseCalculator {
    public static void main(String[] args) {
        SimpleCalculator sc = new SimpleCalculator();
        System.out.println("3 + 4 = " + sc.add(3, 4));
        System.out.println("Area of r = 2.2: " + sc.calCircleArea(2.2));

        System.out.println("15 - 7 = " + sc.min(15, 7));
        System.out.println("Peri of r = 5.0: " + sc.calCirclePeri(5.0));
    }
}

