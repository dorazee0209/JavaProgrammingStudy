/*
 * [문제 06-1] 메소드의 정의 - 문제 2
 *
 * 정수 둘을 인자로 전달받아서, 두 수의 '차의 절댓값'을 계산하여 출력하는 메소드와
 * 이 메소드를 호출하는 main 메소드를 정의해 보자.
 * 단 메소드 호출 시 전달되는 값의 순서에 상관없이 절댓값이 계산되고 출력되어야 한다.
 */

public class Answer02 {
    public static void main(String[] args) {
        int x = 5, y = 20;
        printAbs(x, y);
    }

    static void printAbs(int x, int y) {
        if(x >= y)
            System.out.println(x-y);
        else
            System.out.println(y-x);
    }
}
