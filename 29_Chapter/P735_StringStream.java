import java.util.Arrays;
import java.util.stream.Stream;

public class P735_StringStream {
    public static void main(String[] args) {
        String[] names = {"Yoon", "Lee", "Park"};
        Stream<String> stm = Arrays.stream(names);
        stm.forEach(s -> System.out.println(s));
    }
}

