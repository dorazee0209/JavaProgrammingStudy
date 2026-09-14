import java.util.Arrays;
import java.util.stream.IntStream;

public class P731_MyFirstStream {
    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 5};
        IntStream stm1 = Arrays.stream(ar);
        IntStream stm2 = stm1.filter(n -> n%2 == 1);
        int sum = stm2.sum();
        System.out.println(sum);
    }
}

