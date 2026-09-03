public class P667_TwoParamNoReturn {
    public static void main(String[] args) {
        Calculate c;
        c = (a, b) -> System.out.println(a + b);
        c.cal(4, 3);

        c = (a, b) -> System.out.println(a - b);
        c.cal(4, 3);

        c = (n1, n2) -> System.out.println(n1 * n2);
        c.cal(4, 3);
    }
}

interface Calculate {
    void cal(int n1, int n2);
}