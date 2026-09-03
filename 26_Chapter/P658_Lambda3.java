public class P658_Lambda3 {
    public static void main(String[] args) {
        Printable prn = (s) -> {
            System.out.println(s);
        };

        prn.print("What is Lambda?");
    }
}

interface Printable {
    void print(String s);
}