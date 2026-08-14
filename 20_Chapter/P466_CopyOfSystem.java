public class P466_CopyOfSystem {
    public static void main(String[] args) {
        double[] org = new double[5];
        double[] cpy = new double[3];
        for (int i = 1; i <= 5; i++) {
            String num = i + "." + i;
            org[i-1] = Double.parseDouble(num);
        }

        // 배열 org의 idx = 1에서 cpy의 idx = 0으로 3개의 요소를 복사
        System.arraycopy(org, 1, cpy, 0, 3);

        for(double i : cpy)
            System.out.printf("%.1f\t", i);
        System.out.println();
    }
}

