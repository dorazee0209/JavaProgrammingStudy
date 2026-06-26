/*
 * [문제 04-1] 연산자의 활용과 연산의 특성 파악 - 문제 1
 *
 * int형 변수 num1, num2, num3에 각각 10, 20, 30이 저장된 상태에서 다음 문장을
 * 실행하면 변수에는 각각 얼마가 저장되겠는가?
 *     num1 = num2 = num3;
 * 이의 확인을 위한 코드를 작성하고 그러한 결과를 보이는 이유를 설명해보자.
 */

public class Answer01{
    public static void main(String[] args){
        int num1 = 10;
        int num2 = 20;
        int num3 = 30;

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("num3 = " + num3);

        num1 = num2 = num3;

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("num3 = " + num3);
    }
}

/*
 * 연산자의 결합 방향에 따라 num3의 값이 num2에 먼저 저장되고,
 * num2의 값이 num1에 저장.
 * */
