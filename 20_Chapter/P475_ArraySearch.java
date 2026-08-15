import java.util.Arrays;

public class P475_ArraySearch {
    public static void main(String[] args) {
        int[] ar = {33, 55, 11, 44, 22};
        Arrays.sort(ar);
        for(int i : ar)
            System.out.print(i + "\t");
        System.out.println();

        System.out.printf("Index of 33: %d\n", Arrays.binarySearch(ar, 33));
    }
}

