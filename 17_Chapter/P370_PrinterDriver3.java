interface Printable {
    void Print(String doc);
}

interface ColorPrintable extends Printable { // Printable을 상속하는 인터페이스
    void printCMYK(String doc);
}

class Prn909Drv implements ColorPrintable {
    @Override
    public void Print(String doc) {
        System.out.println("From MD-909 black & white ver");
        System.out.println(doc);
    }

    @Override
    public void printCMYK(String doc) {
        System.out.println("From MD-909 CMYK ver");
        System.out.println(doc);
    }
}

public class P370_PrinterDriver3 {
    public static void main(String[] args) {
        String myDoc = "This is a report about";
        ColorPrintable p = new Prn909Drv();
        p.Print(myDoc);
        System.out.println();
        p.printCMYK(myDoc);
    }
}