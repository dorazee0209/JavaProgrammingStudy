interface Printable {
    public void print(String doc);
}

class SPrinterDriver implements Printable {
    @Override
    public void print(String doc) {
        System.out.println("From SAMSUNG printer");
        System.out.println(doc);
    }
}

class LPrinterDriver implements Printable {
    @Override
    public void print(String doc) {
        System.out.println("From LG printer");
        System.out.println(doc);
    }
}

public class P366_PrinterDriver {
    public static void main(String[] args) {
        String myDoc = "This is a report about...";

        Printable p = new SPrinterDriver();
        p.print(myDoc);
        System.out.println();

        p = new LPrinterDriver();
        p.print(myDoc);
    }
}

