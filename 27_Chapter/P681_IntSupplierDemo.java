import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;

public class P681_IntSupplierDemo {
    public static List<Integer> makeIntList(IntSupplier is, int n) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++)
            list.add(is.getAsInt());

        return list;
    }

    public static void main(String[] args) {
        IntSupplier is = () -> {
            Random rand = new Random();
            return rand.nextInt(50);
        };

        List<Integer> list = makeIntList(is, 5);
        System.out.println(list);

        list = makeIntList(is, 10);
        System.out.println(list);
    }
}

