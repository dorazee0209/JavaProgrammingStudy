import java.util.function.Function;

public class P704_StringMakeMethodRef {
    public static void main(String[] args) {
        Function<char[], String> f = String::new; // 생성자 참조 방식

        char[] src = {'R', 'o', 'b', 'o', 't'};
        String str = f.apply(src);
        System.out.println(str);
    }
}

