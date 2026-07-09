// 또 다른 용도의 static 선언
// static 초기화 블록

import java.time.LocalDate;

public class P235_DateOfExecution {
    static String date;

    static { // 클래스 로딩 시 단 한 번 실행되는 영역
        LocalDate ld = LocalDate.now();
        date = ld.toString();
    }

    public static void main(String[] args) {
        System.out.println(date);
    }
}
