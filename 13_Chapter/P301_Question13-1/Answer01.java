/*
 * [문제 13-1] 배열과 메소드
 *
 * int형 1차원 배열을 매개변수로 전달받아서 배열에 저장된 최댓값, 최솟값을 찾아서 반환하는 메
 * 소드를 각각 다음의 형태로 정의하자.
 *     public static int minValue(int[] arr)      // 최솟값 반환
 *     public static int maxValue(int[] arr)      // 최댓값 반환
 *
 * 단 반복문을 사용할 때 minValue의 정의에서는 일반적인 for문을 사용하고 maxValue의 정
 * 의에서는 새로운 for문(enhanced for문)을 사용하기로 하자.
 *
 * 답안은 출판사 홈페이지를 통해서 제공합니다.
 */
import java.util.Scanner;

public class Answer01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(args[0]);
        final int CNT = sc.nextInt();
        if(CNT != args.length - 1) {
            System.err.println("Wrong param");
            return;
        }

        int[] arr = new int[CNT];
        for (int i = 0; i < CNT; i++) {
            arr[i] = Integer.parseInt(args[i+1]);
            }

        System.out.printf("Min value: %d\n", minValue(arr));
        System.out.printf("Max value: %d\n" ,maxValue(arr));
    }

    public static int minValue(int[] arr) {
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++)
            if(temp > arr[i])
                temp = arr[i];
        return temp;
    }
    public static int maxValue(int[] arr) {
        int temp = arr[0];
        for (int i : arr)
            if(temp < i)
                temp = i;
        return temp;
    }
}
