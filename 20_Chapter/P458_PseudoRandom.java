import java.util.Random;

public class P458_PseudoRandom {
    public static void main(String[] args) {
        Random r = new Random(12);

        for (int i = 0; i < 7; i++) {
            System.out.println(r.nextInt(1_000));
        }
    }
}

