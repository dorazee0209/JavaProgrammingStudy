/*
 * [문제 20-1] 난수의 생성
 *
 * 프로그램 사용자로부터 임의의 정수 A와 Z를 입력받는다. 그리고 A와 Z를 포함하여 그 사이에
 * 있는 난수 10개를 생성하여 출력하는 프로그램을 작성해보자.
 */
import java.util.Random;
import java.util.Scanner;

public class Answer01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("정수 입력(1): ");
        int from = sc.nextInt();
        System.out.print("정수 입력(2): ");
        int to = sc.nextInt();

        for (int i = 0; i < 10; i++) {
            prntRandNum(from, to);
        }
    }

    public static void prntRandNum(int from, int to) {
        Random r = new Random(System.currentTimeMillis());
        System.out.println(r.nextInt(from, to+1));
    }
}
