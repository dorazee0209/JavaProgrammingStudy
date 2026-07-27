import javax.tools.SimpleJavaFileObject;

interface Printable {
    void printLine(String str);
}

class SimplePrinter implements Printable{ // Implementing interface "Printable" directly
    @Override
    public void printLine(String str) {
        System.out.println(str);
    }
}

class MultiPrinter extends SimplePrinter {  // Implementing interface "Printable" indirectly
    @Override
    public void printLine(String str) {
        super.printLine("Start of multi...");
        super.printLine(str);
        super.printLine("End of multi.");
    }
}

public class P376_InstanceofInterface {
    public static void main(String[] args) {
        Printable p1 = new SimplePrinter();
        Printable p2 = new MultiPrinter();

        if(p1 instanceof Printable)
            p1.printLine("This is a simple printer.");
        System.out.println();

        if(p2 instanceof Printable)
            p2.printLine("This is a multi printer");
    }
}