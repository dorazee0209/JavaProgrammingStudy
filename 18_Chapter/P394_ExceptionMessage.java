public class P394_ExceptionMessage {
    public static void md1(int n) {
        md2(n,0); // Calling method below.
    }
    public static void md2(int n1, int n2) {
        int r = n1/n2; // Where exception occurs.
    }
    public static void main(String[] args) {
        md1(3);
        System.out.println("Bye~");
    }
}