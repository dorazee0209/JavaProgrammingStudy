interface Printable {
    public void print(String doc);
}

class Printer implements Printable {
    @Override
    public void print(String doc) {
        System.out.println(doc);
    }
}

public class P363_PrintableInterface {
    public static void main(String[] args) {
        Printable p = new Printer();
        p.print("Hello Java");
    }
}