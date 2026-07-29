import java.util.InputMismatchException;
import java.util.Scanner;
//import java.util.InputMismatchException;

public class P392_ExceptionCase5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("a/b...a? ");
            int a = sc.nextInt();
            System.out.println("a/b...b? ");
            int b = sc.nextInt();
            System.out.printf("%d / %d = %d\n", a, b, a/b);
        }
        catch(ArithmeticException e) {
            e.getMessage();
        }
        catch(InputMismatchException e) {
            e.getMessage();
        }

        System.out.println("Bye~ ");
    }
}

