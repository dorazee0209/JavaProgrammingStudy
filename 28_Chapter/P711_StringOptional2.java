import java.util.Optional;

public class P711_StringOptional2 {
    public static void main(String[] args) {
        Optional<String> os1 = Optional.of(new String("Toy1"));
        Optional<String> os2 = Optional.ofNullable(new String("Toy2"));

        os1.ifPresent(s -> System.out.println(s));
        os2.ifPresent(System.out::println);
    }
}