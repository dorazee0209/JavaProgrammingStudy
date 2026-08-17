import java.util.StringTokenizer;

public class P462_TokenizerString {
    public static void main(String[] args) {
        StringTokenizer st1 = new StringTokenizer("PM:08:15", ":");

        while(st1.hasMoreTokens())
            System.out.print(st1.nextToken() + ' ');
        System.out.println();

        StringTokenizer st2 = new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");

        while(st2.hasMoreTokens())
            System.out.print(st2.nextToken() + ' ');
        System.out.println();
    }
}