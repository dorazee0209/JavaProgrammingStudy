import java.util.*;
import java.util.function.Consumer;

public class P694_ArrangeList2 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
        ls = new ArrayList<>(ls);

        Consumer<List<Integer>> c = Collections::reverse;
        c.accept(ls);
        System.out.println(ls);
    }
}
// Consumer<T>      void accept(T t)