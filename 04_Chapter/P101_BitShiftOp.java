public class P101_BitShiftOp {
    public static void main(String[] args) {
        byte num;

        num = 2;    // 0000 0010
        System.out.print((byte)(num << 1) + " ");
        System.out.print((byte)(num << 2) + " ");
        System.out.println((byte)(num << 3) + " ");
    
        num = 8;    // 0000 1000
        System.out.print((byte)(num >> 1) + " ");
        System.out.print((byte)(num >> 2) + " ");
        System.out.println((byte)(num >> 3) + " ");

        num = -8;    // 1111 1000
        System.out.print((byte)(num >> 1) + " ");
        System.out.print((byte)(num >> 2) + " ");
        System.out.println((byte)(num >> 3) + " ");

    }
}

