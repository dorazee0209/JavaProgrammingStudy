/*
 * [문제 06-2] 값을 반환하는 메소드의 정의 - 문제 1
 *
 * 인자로 원의 반지름 정보를 전달하면, 원의 넓이를 계산해서 반환하는 메소드와
 * 원의 둘레를 계산해서 반환하는 메소드를 각각 정의하고,
 * 이 둘을 호출하는 main 메소드를 정의하자.
 */

public class Answer01 {
    public static void main(String[] args) {
        int r = 3;
        System.out.println("S = " + S(r));
        System.out.println("D = " + D(r));
    }

    public static double S(int num) {
        return Sq(num) * 3.14;
    }

    public static double D(int num) {
        return 2 * num * 3.14;
    }

    public static int Sq(int num) {
        return num*num;
    }
}
