public class P099_BitOperator {
    public static void main(String[] args) {
        byte n1 = 5;    // 0000 0101
        byte n2 = 3;    // 0000 0011
        byte n3 = -1;   // 1111 1111

        byte r1 = (byte)(n1 & n2);  // 0000 0001
        byte r2 = (byte)(n1 | n2);  // 0000 0111
        byte r3 = (byte)(n1 ^ n2);  // 0000 0110
        byte r4 = (byte)(~n3);      // 0000 0000

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
    }
}

