/*
 * [문제 21-1] [제네릭 클래스 정의하기]
 *
 * • 문제 1
 * 다음은 앞서 예제에서 작성한 수납공간이 둘로 나눠져 있는 상자를 표현한 제네릭 클래스이다.
 *
 *     class DBox<L, R> {
 *         private L left;
 *         private R right;
 *         public void set(L o, R r) {
 *             left = o;
 *             right = r;
 *         }
 *         public String toString() { return left + " & " + right; }
 *     }
 *
 * 이어서 수납공간이 둘로 나눠져 있는 상자를 표현한 클래스를 DDBox<U, D>라는 이름으로 하
 * 나 더 정의하여 DBox<L, R> 인스턴스 둘을 이 상자에 저장하고자 한다. 그럼 다음 main 메소
 * 드를 기반으로 컴파일 및 실행이 가능하도록 DDBox<U, D> 제네릭 클래스를 정의해보자.
 *
 *     public static void main(String[] args) {
 *         DBox<String, Integer> box1 = new DBox<>();
 *         box1.set("Apple", 25);
 *         DBox<String, Integer> box2 = new DBox<>();
 *         box2.set("Orange", 33)
 *         DDBox<DBox<String, Integer>, DBox<String, Integer>> ddbox = new DDBox<>();
 *         ddbox.set(box1, box2);      // 두 개의 상자를 하나의 상자에 담음
 *         System.out.println(ddbox);      // 상자의 내용물 출력
 *     }
 *
 * 그리고 위 main 메소드의 실행 결과로 다음의 출력을 보이게 하자. (출력 형태는 이와 달라도 괜
 * 찮다. 내용물만 전부 출력이 되면 된다.)
 *
 *     C:\JavaStudy>java DDBoxDemo
 *     Apple & 25
 *     Orange & 33
 */

public class Answer01 {
    public static void main(String[] args) {

    }
}
