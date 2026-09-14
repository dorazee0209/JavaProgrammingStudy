import java.util.Arrays;
import java.util.stream.Stream;

public class P736_StringSteam2 {
    public static void main(String[] args) {
        String[] sArr = {"Yoon", "Lee", "Park"};
        Arrays.stream(sArr).forEach(n -> System.out.println(n));
    }
}

