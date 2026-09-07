import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class P675_PredicateDemo {
    public static int sum(Predicate<Integer> p, List<Integer> lst) {
        int sum = 0;
        for(Integer e : lst)
            if(p.test(e))
                sum += e;
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 7, 9, 11, 12);
        int s;
        s = sum(n -> n%2 == 0, list);
        System.out.println("Sum of even numbers: " + s);

        s = sum(n -> n%2 == 1, list);
        System.out.println("Sum of odd numbers: " + s);
    }
}

