public class P648_UseMemberInner {
    public static void main(String[] args) {
        Papers p = new Papers("Doc content");
        Printable prn = p.getPrinter();
        prn.print();
    }
}

interface Printable {
    void print();
}

class Papers {
    private String con;
    public Papers(String s) { this.con = s; }
    public Printable getPrinter() {
        return new Printer();
    }

    private class Printer implements Printable {
        @Override
        public void print() {
            System.out.println(con);
        }
    }
}