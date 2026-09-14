import javax.swing.text.html.Option;
import java.util.OptionalInt;

public class P727_OptionalIntBase {
    public static void main(String[] args) {
        OptionalInt op1 = OptionalInt.of(3);
        OptionalInt op2 = OptionalInt.empty();

        System.out.println("[Step 1.]: ");
        op1.ifPresent(i -> System.out.print(i + "\t"));
        op2.ifPresent(i -> System.out.print(i + "\t"));
        System.out.println();

        System.out.println("[Step 2.]: ");
        System.out.print(op1.orElse(100) + "\t");
        System.out.print(op2.orElse(100) + "\t");
    }
}

