// Marker interface(s)
interface Upper { }
interface Lower { }

interface Printable {
    String getContents();
}

class Report implements Printable, Upper {
    String cons;

    Report(String cons) {
        this.cons = cons;
    }
    @Override
    public String getContents() {
        return cons;
    }
}

public class Printer {
    public void printContents(Printable doc) {
        if(doc instanceof Upper)
            System.out.println((doc.getContents()).toUpperCase());
        else if(doc instanceof Lower)
            System.out.println((doc.getContents()).toLowerCase());
        else
            System.out.println(doc.getContents());
    }
}

public class P378_MarkerInterface {
    public static void main() {
        Printer p1 = new Printer();
        Printable p2 = new Report("Simple funny news~");
        // or
        // Report p2 = new Report("Simple funny news~");

        p1.printContents(p2);
    }
}