/*
 * [문제 04-2] 비트 연산자 그리고 비트 쉬프트 연산자 - 문제 1
 *
 * 본문에서 정수 7의 비트 열을 기반으로 2의 보수를 취하면 -7이 됨을 설명하였다.
 * 실제로 그런지 정수 7에 대한 2의 보수를 계산하여 출력하는 프로그램을 작성해 보자.
 * 참고로 ~ 연산자를 사용하면 쉽게 2의 보수를 구할 수 있다.
 */

public class Answer01{
    public static void main(String[] args){
        byte num = 0b0000_0111;
        num = (byte)(~num + 1);
        System.out.println(num);
    }
}
