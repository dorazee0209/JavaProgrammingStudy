/*
 * [문제 05-4] while문과 do ~ while문의 활용 - 문제 1
 *
 * 1부터 99까지의 합을 구하는 프로그램을 작성하되 while문을 이용해서
 * 작성해보자.
 */

public class Answer01{
    public static void main(String[] args){
        int result = 0;
        int i = 0;

        while(i < 100) {
            result += i;
            i++;
        }

        System.out.println("result =" + result);
    }
}
