/*
 * [문제 28-2] 람다와 생성자 참조
 *
 * • 문제 2
 * 위 문제 1에서 요구한 내용의 구현 결과를 '메소드 참조' 기반으로 수정해보자.
 */

import java.util.function.BiFunction;

public class Answer02 {
    public static void main(String[] args) {
        // 문제 1의 bf 선언을 '생성자 참조' 기반으로 다시 작성
        BiFunction<Integer, String, Box<Integer, String>> bf = Box::new;


        Box<Integer, String> b1 = bf.apply(1, "Toy");
        Box<Integer, String> b2 = bf.apply(2, "Robot");
        b1.showIt();
        b2.showIt();
    }
}

class Box<T, U> {
    private T id;
    private U con;
    public Box(T i, U c) {
        id = i;
        con = c;
    }
    public void showIt() {
        System.out.println("ID: " + id + ", " + "Contents: " + con);
    }
}
