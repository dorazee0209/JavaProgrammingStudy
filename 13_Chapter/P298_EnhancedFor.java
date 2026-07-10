public class P298_EnhancedFor {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        for (int i : arr)
            System.out.printf("%d ", i);
        System.out.println();

        int sum = 0;
        for (int i : arr)
            sum += i;
        System.out.printf("Sum = %d \n", sum);
    }
}

