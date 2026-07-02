/*
 * [문제 05-7] 반복문 중첩의 활용 - 문제 2
 *
 * 다음 식을 만족하는 모든 A와 B의 조합을 구하는 프로그램을 작성하자.
 *
 *       A B
 *     + B A
 *     -----
 *       9 9
 *
 *  (A, B는 각 자리의 숫자. 두 자리 수 AB 와 BA 를 더하면 99 가 되는
 *   모든 A, B 조합을 찾아 출력한다.)
 */

public class Answer02 {
    public static void main(String[] args) {
        for(int i = 10; i <= 99; i++) {
            for(int j = 10; j <= 99; j++) {
                if((i%10 == j/10)&&(j%10 == i/10)) {
                    if(i + j == 99) {
                        System.out.println(i + ", " + j);
                    }
                }
            }
        }
    }
}
