interface Printable { // MS사가 제공한 인터페이스
    void Print(String doc); // 흑백 출력을 위한 추상 메소드
}

class Prn204Drv implements Printable { // S사의 흑백 프린터 드라이버
    @Override
    public void Print(String doc) {
        System.out.println("From MD-204 printer");
        System.out.println(doc);
    }
}

class Prn731Drv implements Printable { // L사의 흑백 프린터 드라이버
    @Override
    public void Print(String doc) {
        System.out.println("From MD-731 printer");
        System.out.println(doc);
    }
}

public class P368_PrinterDriver2 {
    public static void main(String[] args) {
        String myDoc = "This is a report about...";

        Printable p = new Prn204Drv();
        p.Print(myDoc);
        System.out.println();

        p = new Prn731Drv();
        p.Print(myDoc);
    }
}