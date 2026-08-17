import java.util.Arrays;

public class P470_ArraySort {
    public static void main(String[] args) {
        int[] ar1 = {1, 5, 4, 2,3};
        double[] ar2 = {3.3, 2.2, 5.5, 1.1, 4.4};

        Arrays.sort(ar1);
        Arrays.sort(ar2);

        System.out.println(Arrays.toString(ar1));
        System.out.println(Arrays.toString(ar2));
    }
}

