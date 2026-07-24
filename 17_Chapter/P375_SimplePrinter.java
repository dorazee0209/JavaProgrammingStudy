interface Printable {
    static void printLine(String str) {
        System.out.println(str);
    }
    default void print(String doc) {
        printLine(doc); // 인터페이스의 static 메소드 호출
    }
}

// 오버라이드로 구현해야할 메소드가 존재하지 않는다.
class Printer implements Printable {}

public class P375_SimplePrinter {
    public static void main(String[] args) {
        String myDoc = "This is a report about...";
        Printable p = new Printer();
        p.print(myDoc);
        System.out.println();

        // 인터페이스의 static 메소드 직접 호출
        Printable.printLine("eol");
    }
}