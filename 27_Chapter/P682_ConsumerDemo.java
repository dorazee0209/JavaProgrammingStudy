import java.util.function.Consumer;

public class P682_ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> c = s -> System.out.println(s);
        c.accept("Pineapple");
        c.accept("Strawberry");
    }
}

