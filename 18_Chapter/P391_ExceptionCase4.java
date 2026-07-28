import java.util.Scanner;
import java.util.InputMismatchException;

public class P391_ExceptionCase4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("a/b...a? ");
            int a = sc.nextInt();
            System.out.println("a/b...b? ");
            int b = sc.nextInt();
            System.out.printf("%d / %d = %d \n", a, b, a/b);
        }
        catch(InputMismatchException e) {
            e.getMessage();
        }

        System.out.println("Bye~");
    }
}