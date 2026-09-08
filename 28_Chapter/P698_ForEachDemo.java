import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P698_ForEachDemo {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Box", "Robot");
        ls = new ArrayList<>(ls);

        ls.forEach(s -> System.out.println(s)); // 람다식 기반
        ls.forEach(System.out::println); // 메소드 참조 기반
    }
}

