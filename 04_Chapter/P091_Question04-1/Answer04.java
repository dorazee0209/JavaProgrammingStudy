/*
 * [문제 04-1] 연산자의 활용과 연산의 특성 파악 - 문제 4
 *
 * 다음 계산 결과를 출력하는 프로그램을 작성하되, 덧셈 연산의 횟수를 최소화해
 * 작성해보자.
 *     3 + 6        3 + 6 + 9        3 + 6 + 9 + 12
 */

public class Answer04{
    public static void main(String[] args){
        int num = 3;
        int last = 6;
        int result = num;
    
        for(int i = 0; i < 3; i++) {
            System.out.println(result += last);
            if(i == 2)
                break;
            last += num;
        }
    }
}
