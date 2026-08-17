import java.lang.reflect.Array;
import java.util.Arrays;

public class P467_ArrayEquals {
    public static void main(String[] args) {
        int[] arr1 = new int[5];
        for (int i = 1; i <= 5; i++) {
            arr1[i-1] = i;
        }
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        System.out.println(Arrays.toString(arr2));

        System.out.println(Arrays.equals(arr1, arr2));
    }
}