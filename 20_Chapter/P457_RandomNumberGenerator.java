import java.util.Random;

public class P457_RandomNumberGenerator {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 7; i++) {
            System.out.println(r.nextInt(1_000));
        }
    }
}

