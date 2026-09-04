import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class P677_IntPredicateDemo {
    public static int sum(IntPredicate ip, List<Integer> list) {
        int s = 0;
        for(Integer e : list) {
            if(ip.test(e))
                s += e;
        }
        return s;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 7, 9, 11, 12);
        int s;
        s = sum(n -> n%2 == 0, list);
        System.out.println("Sum of even num: " + s);

        s = sum(n -> n%2 == 1, list);
        System.out.println("Sum of odd num: " + s);
    }
}