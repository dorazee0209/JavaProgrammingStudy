// Predicate<? super E> filter

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class P689_RemoveIfDemo {
    public static void main(String[] args) {
        List<Integer> ls1 = Arrays.asList(1, -2, 3, -4, 5);
        ls1 = new ArrayList<>(ls1);

        List<Double> ls2 = Arrays.asList(1.1, -2.2, 3.3, -4.4, 5.5);
        ls2 = new ArrayList<>(ls2);

        Predicate<Number> p = n -> n.doubleValue() < 0.0; // 삭제 조건
        ls1.removeIf(p); // List<Integer> 인스턴스에 전달
        ls2.removeIf(p); // List<Double> 인스턴스에 전달

        System.out.println(ls1);
        System.out.println(ls2);
    }
}

