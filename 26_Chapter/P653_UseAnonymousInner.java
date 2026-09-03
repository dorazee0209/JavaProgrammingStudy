public class P653_UseAnonymousInner {
    public static void main(String[] args) {
        Papers p = new Papers("Content..");
        Printable prn = p.getPrinter();
        prn.print();
    }
}

interface Printable {
    public void print();
}

class Papers {
    private String con;

    public Papers(String s) {
        con = s;
    }

    public Printable getPrinter() {
        return new Printable() {
            @Override
            public void print() {
                System.out.println(con);
            }
        };
    }
}