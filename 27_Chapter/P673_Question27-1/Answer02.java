/*
 * [문제 27-1] 람다식 작성하기
 *
 * • 문제 2
 * 본 Chapter의 첫 번째 예제인 SLenComparator.java를 람다식 기반으로 수정해보자.
 * 수정 결과에서는 클래스 SLenComp의 정의가 지워져야 한다.
 */

import java.util.*;

public class Answer02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Robot");
        list.add("Lambda");
        list.add("Box");

        System.out.println(list);

        Comparator<String> c = (s1, s2) -> s1.length() - s2.length();
        Collections.sort(list, c);

        System.out.println(list);
    }
}

/*
 * 원본 예제: P664_SLenComparator.java
 *
 * import java.util.ArrayList;
 * import java.util.Collections;
 * import java.util.Comparator;
 * import java.util.List;
 *
 * public class P664_SLenComparator {
 *     public static void main(String[] args) {
 *         List<String> list = new ArrayList<>();
 *         list.add("Robot");
 *         list.add("Lambda");
 *         list.add("Box");
 *
 *         Collections.sort(list, new SLenComp());
 *
 *         for(String e : list) {
 *             System.out.println(e);
 *         }
 *     }
 * }
 *
 * class SLenComp implements Comparator<String> {
 *     @Override
 *     public int compare(String s1, String s2) {
 *         return s1.length() - s2.length();
 *     }
 * }
 */
