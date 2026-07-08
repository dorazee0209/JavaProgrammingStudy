/*
 * [문제 28-1] 메소드 참조
 *
 * 다음 예제를 메소드 참조 방식으로 수정해보자.
 *     import java.util.List;
 *     import java.util.ArrayList;
 *     import java.util.Collections;
 *
 *     class StrIgnoreCaseComp {
 *         public static void main(String[] args) {
 *             List<String> list = new ArrayList<>();
 *             list.add("robot");
 *             list.add("Lambda");
 *             list.add("box");
 *             Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));
 *             System.out.println(list);
 *         }
 *     }
 *
 * 참고로 Collections.sort 메소드가 다음과 같으니,
 *     public static <T> void sort(List<T> list, Comparator<? super T> c)
 *
 * 다음 문장을 메소드 참조 기반으로 수정한다고 생각하면 편하다.
 *     Comparator<? super T> c = (s1, s2) -> s1.compareToIgnoreCase(s2)
 */

public class Answer01 {
    public static void main(String[] args) {

    }
}
