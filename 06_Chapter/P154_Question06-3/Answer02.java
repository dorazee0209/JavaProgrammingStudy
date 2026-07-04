/*
 * [문제 06-3] 재귀 메소드의 정의 - 문제 2
 *
 * 인자로 십진수 정수를 전달받아서 이에 해당하는 이진수 표현을 출력하는 메소드를
 * 재귀의 형태로 정의하고, 이를 호출하는 main 메소드를 정의해보자.
 */

public class Answer02 {
    public static void main(String[] args) {
        for (int i = 16; i >= 1; i--) {
            System.out.print(i + ": ");
            toBin(i);
            System.out.println();
        }
//        11 -> 5 -> 2 -> 1 : 1011
//        toBin(14);
    }

    public static void toBin(int n) {
        if(n == 1) {
            System.out.print(1);
            return;
        }
        else {
            toBin(n/2);
            System.out.print(n%2);
            return;
        }
    }
}
