/*
 * [문제 05-3] switch문의 활용 - 문제 1
 *
 * 예제 SwitchBreak.java를 switch문이 아닌 if ~ else문을 이용하는
 * 형태로 변경해보자.
 *
 *     class SwitchBreak {
 *         public static void main(String[] args) {
 *             int n = 3;
 *
 *             switch(n) {
 *             case 1:
 *                 System.out.println("Simple Java");
 *                 break;
 *             case 2:
 *                 System.out.println("Funny Java");
 *                 break;
 *             case 3:
 *                 System.out.println("Fantastic Java");
 *                 break;
 *             default:
 *                 System.out.println("The best programming language");
 *             }
 *
 *             System.out.println("Do you like coffee?");
 *         }
 *     }
 */

public class Answer01{
    public static void main(String[] args){
            int n = 3;
            if(n == 1)
                System.out.println("Simple Java");
            else if(n == 2)
                System.out.println("Funny Java");
            else if(n == 3)
                System.out.println("Fantastic Java");
            else
                System.out.println("The best programming language");
                                                                     
            System.out.println("Do you like coffee?");
    }
}
