public class P660_Lambda4 {
    public static void showString(Printable p, String s) {
        p.print(s);
    }
    public static void main(String[] args) {
        showString((s) -> { System.out.println(s);} , "What is Lambda?");
    }
}

interface Printable {
    void print(String s);
}