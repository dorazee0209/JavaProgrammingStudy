public class P307_PartiallyFilledArray {
    public static void main(String[] args) {
        int[][] arr = {
                {11},
                {22, 33},
                {44, 55, 66}
        };

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
                System.out.printf("%d\t", arr[i][j]);
            System.out.println();
        }
    }
}