import java.time.LocalDate;

class Test {
    static String date;

    Test() {
        LocalDate ld = LocalDate.now();
        date = ld.toString();
    }

    void show() {
        System.out.println(date);
    }
}

public class P235_DateOfExecution {
    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.date);
    }
}
