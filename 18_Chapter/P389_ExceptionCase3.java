// input: k

import java.util.Scanner;

public class P389_ExceptionCase3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("a/b...a? ");
        int a = sc.nextInt(); // Probably input err occurs
        System.out.println("a/b...b? ");
        int b = sc.nextInt(); // Probably input err occurs
        System.out.printf("%d / %d = %d\n", a, b, a/b);
        System.out.println("Bye~");
    }
}