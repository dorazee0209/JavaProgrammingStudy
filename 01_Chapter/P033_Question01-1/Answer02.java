/*
 * [문제 01-1] 클래스의 이름과 문자열의 출력 - 문제 2
 *
 * 다음 두 문장의 출력 결과를 확인하는 프로그램을 작성해 보자.
 *     System.out.println("2 + 5 = " + 2 + 5);
 *     System.out.println("2 + 5 = " + (2 + 5));
 * 그리고 자바 프로그램에서 소괄호가 지니는 의미가 무엇인지, 수학에서 의미하는
 * 소괄호와 유사한 의미를 갖는지 생각해 보자. 참고로 이 문제는 코드의 관찰
 * 습관을 유도하기 위한 것이다.
 */
public class Answer02{
    public static void main(String[] args){
        System.out.println("2 + 5 = " + 2 + 5);
        System.out.println("2 + 5 = " + (2 + 5));
    }
}
