import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

public class P683_ObjIntConsumerDemo {
    public static void main(String[] args) {
        ObjIntConsumer<String> c = (s, i) -> System.out.println(i + ". " + s);

        int i = 0;
        c.accept("Toy", ++i);
        c.accept("Book", ++i);
        c.accept("Candy", ++i);
    }
}

