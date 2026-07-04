/*
 * [문제 06-3] 재귀 메소드의 정의 - 문제 1
 *
 * 인자로 정수 n을 전달받아서 2의 n승을 계산하여 반환하는 메소드를
 * 재귀의 형태로 정의하고, 이를 호출하는 main 메소드를 정의해보자.
 */

public class Answer01 {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            System.out.println("2 to the power of " + i + " = " + exp_2(i));
        }
    }

    public static int exp_2(int n) {
        if(n == 0) {
            return 1;
        }
        else if(n == 1) {
            return 2;
        }
        else {
            return 2 * exp_2(n-1);
        }
    }
}
