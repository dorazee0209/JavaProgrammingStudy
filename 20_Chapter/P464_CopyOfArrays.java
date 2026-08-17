import java.util.Arrays;

public class P464_CopyOfArrays {
    public static void main(String[] args) {
        double[] arOrg = new double[5];
        for (int i = 0; i < 5; i++) {
            String n = (i+1) + "." + (i+1);
            arOrg[i] = Double.parseDouble(n);
        }

        // 배열 전체 복사
        double[] arCpy1 = Arrays.copyOf(arOrg, arOrg.length);

        // 세번째 요소까지만 복사
        double[] arCpy2 = Arrays.copyOf(arOrg, 3);

        for(double i : arCpy1)
            System.out.printf("%.1f\t", i);
        System.out.println();

        for(double i : arCpy2)
            System.out.printf("%.1f\t", i);
        System.out.println();
    }
}

