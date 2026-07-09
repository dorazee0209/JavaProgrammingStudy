//P226_UseCalculator.java에서 개선된 점:
//- 불필요하게 인스턴스 생성 과정 생략

class SimpleCalculator {
    static final double PI = 3.1415;

    static double add(double n1, double n2) {
        return n1 + n2;
    }
    static double min(double n1, double n2) {
        return n1 - n2;
    }
    static double calCircleArea(double r) {
        return r * r * PI;
    }
    static double calCirclePeri(double r) {
        return 2 * r * PI;
    }
}

public class P227_UseCalculatorCMVer {
    public static void main(String[] args) {
        System.out.println("3 + 4 = " + SimpleCalculator.add(3, 4));
        System.out.println("Area of r = 2.2: " + SimpleCalculator.calCircleArea(2.2));
        System.out.println("15 - 7 = " + SimpleCalculator.min(15, 7));
        System.out.println("Peri of r = 5.0: " + SimpleCalculator.calCirclePeri(5.0));
    }
}