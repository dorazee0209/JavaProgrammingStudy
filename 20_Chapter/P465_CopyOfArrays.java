import java.util.Arrays;

public class P465_CopyOfArrays {
    public static void main(String[] args) {
        double[] arOrg = new double[5];
        for (int i = 1; i <= 5; i++) {
            String n;
            n = i + "." + i;
            arOrg[i-1] = Double.parseDouble(n);
        }

        double[] cpOrg = Arrays.copyOfRange(arOrg, 1, 4);

        for(double i : cpOrg)
            System.out.printf("%.1f\t", i);
        System.out.println();
    }
}

