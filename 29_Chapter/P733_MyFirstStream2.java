import java.util.Arrays;
import java.util.stream.IntStream;

public class P733_MyFirstStream2 {
    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 5};

       int sum = Arrays.stream(ar)
                            .filter(i -> i % 2 == 1)
                            .sum();
        System.out.println(sum);
    }
}

