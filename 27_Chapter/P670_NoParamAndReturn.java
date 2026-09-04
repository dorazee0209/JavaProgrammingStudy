import java.util.Random;

public class P670_NoParamAndReturn {
    public static void main(String[] args) {
        Generator gen = () -> {
            Random rand = new Random();
            return rand.nextInt();
        };

        System.out.println(gen.rd());
    }
}

interface Generator {
    int rd(); // 매개변수 없는 메소드
}