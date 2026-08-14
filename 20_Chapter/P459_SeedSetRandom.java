import java.util.Random;

public class P459_SeedSetRandom {
    public static void main(String[] args) {
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 7; i++) {
            System.out.println(r.nextInt(1000));
        }
    }
}