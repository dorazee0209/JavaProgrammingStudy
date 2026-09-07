public class P669_OneParamAndReturn {
    public static void main(String[] args) {
        HowLong hl = s -> s.length();
        System.out.println(hl.len("I am so happy."));
    }
}

interface HowLong {
    int len(String s);
}