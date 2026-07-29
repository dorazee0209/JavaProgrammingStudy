import java.util.Scanner;

public class P387_ExceptionCase2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("a/b...a? ");
            int a = sc.nextInt();
            System.out.println("a/b...b? ");
            int b = sc.nextInt();
            System.out.printf("%d / %d = %d\n", a, b, a/b); // Point where exception occurs
        }
        catch(ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Bye~");
    }
}