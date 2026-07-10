import java.util.Arrays;

public class P294_ArrayUtils {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];

        Arrays.fill(arr1, 7);
        System.arraycopy(arr1, 0, arr2, 3, 4);

        for (int i = 0; i < arr1.length; i++)
            System.out.print(arr1[i] + " ");
        System.out.println();

        for (int i = 0; i < arr2.length; i++)
            System.out.print(arr2[i] + " ");
    }
}

