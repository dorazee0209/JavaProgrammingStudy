public class P304_TwoDimenArray2 {
    public static void main(String[] args) {
        int[][] arr = new int[3][4];
        int num = 1;

        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                arr[i][j] = num++;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.printf("%d\t", arr[i][j]);
            System.out.println();
        }
    }
}

