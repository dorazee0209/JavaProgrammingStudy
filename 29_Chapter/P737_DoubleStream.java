import java.util.Arrays;

public class P737_DoubleStream {
    public static void main(String[] args) {
        double[] dArr = {1.1, 2.2, 3.3, 4.4, 5.5};
        Arrays.stream(dArr).forEach(i -> System.out.println(i));
        System.out.println();

        Arrays.stream(dArr, 1, 4).forEach(i -> System.out.println(i));
    }
}

