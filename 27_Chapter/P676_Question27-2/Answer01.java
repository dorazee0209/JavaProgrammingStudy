/*
 * [문제 27-2] Predicate<T>
 *
 * 아래의 코드에서 주석으로 표시된 내용의 출력을 보이도록 show 메소드의 몸체를 채워 보자.
 *     import java.util.List;
 *     import java.util.Arrays;
 *     import java.util.function.Predicate;
 *
 *     class PredicateShow {
 *         public static <T> void show(Predicate<T> p, List<T> lst) {
 *             // 채워 넣을 부분
 *         }
 *         public static void main(String[] args) {
 *             List<Integer> lst1 = Arrays.asList(1, 3, 8, 10, 11);
 *             show(n -> n%2 != 0, lst1);    // 홀수만 출력
 *
 *             List<Double> lst2 = Arrays.asList(-1.2, 3.5, -2.4, 9.5);
 *             show(n -> n > 0.0, lst2);     // 0.0 보다 큰 수 출력
 *         }
 *     }
 */

import java.util.List;
import java.util.Arrays;
import java.util.function.Predicate;

public class Answer01 {
    public static <T> void show(Predicate<T> p, List<T> lst) {
        // 채워 넣을 부분
        int init = 0;
        for(T e : lst) {
            if(p.test(e)) {
                init++;
                if (init == 1) {
                    System.out.print("[");
                }
                if (init != 1) {
                    System.out.print(", ");
                }
                System.out.print(e);
            }
        }
        if(init != 0) {
            System.out.print("]");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> lst1 = Arrays.asList(1, 3, 8, 10, 11);
        show(n -> n%2 != 0, lst1);    // 홀수만 출력

        List<Double> lst2 = Arrays.asList(-1.2, 3.5, -2.4, 9.5);
        show(n -> n > 0.0, lst2);     // 0.0 보다 큰 수 출력
    }
}
