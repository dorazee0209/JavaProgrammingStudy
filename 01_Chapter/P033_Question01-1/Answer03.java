/*
 * [문제 01-1] 클래스의 이름과 문자열의 출력 - 문제 3
 *
 * 숫자 12를 총 5회 출력하는 프로그램을 작성하되 매 출력마다 그 방법을 달리해
 * 보자. 즉 다음과 같은 문장을 5회 구성하여 숫자 12를 5회 출력하되, 소괄호
 * 안의 내용은 모두 달라야 한다.
 *     System.out.println( . . . );
 */
public class Answer03{
    public static void main(String[] args){
        System.out.println("12");
        System.out.println(12);
        System.out.println("1"+2);
        System.out.println(1+"2");
        System.out.println("1"+"2");
    }
}
