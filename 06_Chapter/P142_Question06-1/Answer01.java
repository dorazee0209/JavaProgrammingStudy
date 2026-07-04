/*
 * [문제 06-1] 메소드의 정의 - 문제 1
 *
 * 정수 둘을 인자로 전달받아서 두 수의 사칙연산 결과를 출력하는 메소드와
 * 이 메소드를 호출하는 main 메소드를 정의해 보자.
 * 단 나눗셈은 몫과 나머지를 각각 출력해야 한다.
 */

public class Answer01 {
    public static void main(String[] args) {
        int x = 10, y = 5;
        calculus(x, y);
    }

    static void calculus(int x, int y) {
        System.out.println(x + " + " + y + " = " + (x + y));
        System.out.println(x + " - " + y + " = " + (x - y));
        System.out.println(x + " * " + y + " = " + (x * y));
        System.out.println(x + " / " + y + " = " + (x / y) + " ... " + (x % y));
    }
}
