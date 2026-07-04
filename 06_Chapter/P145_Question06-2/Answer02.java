/*
 * [문제 06-2] 값을 반환하는 메소드의 정의 - 문제 2
 *
 * 전달된 값이 소수(Prime Number)인지 아닌지를 판단하여 소수인 경우 true를,
 * 소수가 아닌 경우 false를 반환하는 메소드를 정의하고,
 * 이 메소드의 호출 결과를 기반으로 1 이상 100 이하의 소수를 전부 출력하는
 * main 메소드를 정의하자.
 */

public class Answer02 {
    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++) {
            if(Prime(i))
                System.out.println(i);
        }
    }
    public static boolean Prime(int n) {
        int cnt = 0;
        for(int i = 2; i <= n; i++) {
            if(n % i == 0) {
                cnt++;
            }
        }
        if(cnt == 1)
            return true;
        else
            return false;
    }
}
