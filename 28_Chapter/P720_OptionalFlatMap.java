import java.util.Locale;
import java.util.Optional;

public class P720_OptionalFlatMap {
    public static void main(String[] args) {
        Optional<String> o1 = Optional.of("Optional String");
        Optional<String> o2 = o1.map(s -> s.toUpperCase());
        System.out.println(o2.get());

        Optional<String> o3 = o1.flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println(o3.get());
    }
}

