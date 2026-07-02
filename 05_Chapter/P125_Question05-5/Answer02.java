/*
 * [문제 05-5] for문의 활용 - 문제 2
 *
 * 구구단 중 5단을 출력하는 프로그램을 for문을 이용해서 작성해보자.
 */

public class Answer02{
    public static void main(String[] args){
        final int DAN = 5;
        for(int i = 1; i < 10; i ++) {
            System.out.println(DAN + " * " + i + " = " + (DAN * i));
        }
    }
}
