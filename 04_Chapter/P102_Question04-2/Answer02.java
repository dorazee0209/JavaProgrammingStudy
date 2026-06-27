/*
 * [문제 04-2] 비트 연산자 그리고 비트 쉬프트 연산자 - 문제 2
 *
 * int형 정수 15678의 오른쪽 끝에서 세 번째 비트와 다섯 번째 비트가
 * 각각 어떻게 되는지 확인하여 출력하는 프로그램을 작성해 보자.
 * 참고로 이 문제는 조금 부담되는 문제이니 해결하지 못했다고 하여
 * 실망할 필요는 없다.
 */

public class Answer02{
    public static void main(String[] args){
        int num = 15678;

        // 오른쪽으로 밀어서 원하는 비트를 맨 끝으로 보낸 뒤, & 1로 추출
        int third = (num >> 2) & 1;   // 오른쪽에서 3번째 비트
        int fifth = (num >> 4) & 1;   // 오른쪽에서 5번째 비트

        System.out.println("3rd bit = " + third);
        System.out.println("5th bit = " + fifth);
    }
}
