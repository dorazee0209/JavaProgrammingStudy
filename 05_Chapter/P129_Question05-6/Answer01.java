/*
 * [문제 05-6] continue문과 break문의 활용 - 문제 1
 *
 * 예제 ContinueBasic.java에 존재하는 while문을 for문으로 변경해보자.
 *
 *     class ContinueBasic {
 *         public static void main(String[] args) {
 *             int num = 0;
 *             int count = 0;
 *
 *             while(num++ < 100) {
 *                 if(num % 5 != 0 || num % 7 != 0) {
 *                     continue;
 *                 }
 *                 count++;
 *                 System.out.println(num);
 *             }
 *             System.out.println("count: " + count);
 *         }
 *     }
 *
 *  (실행 결과: 35, 70 출력 후 count: 2)
 */

public class Answer01 {
    public static void main(String[] args) {
        int count = 0;

        for(int i = 1; i < 100; i++) {
            if(i % 5 != 0 || i % 7 != 0) {
                continue;
            }
            count++;
            System.out.println(i);
        }
        System.out.println(count);
    }
}
