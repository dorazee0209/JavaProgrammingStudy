import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

public class P687_ToIntFunctionDemo {
    public static void main(String[] args) {
        ToIntFunction<String> f = s -> s.length();
        System.out.println(f.applyAsInt("Robot"));
        System.out.println(f.applyAsInt("System"));
    }
}

