/*
 * [문제 05-2] 조건 연산자
 *
 * 예제 CondOp.java를 조건 연산자를 사용하지 않고 대신에 if ~ else문을
 * 사용하는 형태로 수정해보자.
 *
 *     class CondOp {
 *         public static void main(String[] args) {
 *             int num1 = 50;
 *             int num2 = 100;
 *             int big;
 *             int diff;
 *
 *             big = (num1 > num2) ? num1 : num2;
 *             System.out.println("큰 수: " + big);
 *
 *             diff = (num1 > num2) ? (num1 - num2) : (num2 - num1);
 *             System.out.println("절댓값: " + diff);
 *         }
 *     }
 */

public class Answer01{
    public static void main(String[] args){
        int num1 = 50;
        int num2 = 100;
        int big;
        int diff;
        
        if(num1 > num2) {
            big = num1;
            diff = num1 - num2;
        }
        else {
            big = num2;
            diff = num2 - num1;
        }

        System.out.println("The bigger one: " + big);
        System.out.println("Abs: " + diff);
    }
}
