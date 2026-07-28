interface Printable {
    void Print(String doc);
    default void PrintCMYK(String doc) {}; // 인터페이스의 디폴트 메소드
}

class Prn731Drv implements Printable {
    @Override
    public void Print(String doc) {
        System.out.println("From MD-731 printer");
        System.out.println(doc);
    }
}

class Prn909Drv implements Printable {
    @Override
    public void Print(String doc) {
        System.out.println("From MD-909 black & white ver");
        System.out.println(doc);
    }

    @Override
    public void PrintCMYK(String doc) {
        System.out.println("From MD-909 CMYK ver");
        System.out.println(doc);
    }
}

public class P372_PrinterDriver4 {
    public static void main(String[] args) {
        String myDoc = "This is a report about...";
        Printable p = new Prn731Drv();
        p.Print(myDoc);
        System.out.println();

        p = new Prn909Drv();
        p.Print(myDoc);
    }
}