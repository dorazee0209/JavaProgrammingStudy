class Person {
    private int ssN;
    private int passN;

    Person(int ssN, int passN) {
        this.ssN = ssN;
        this.passN = passN;
    }
    Person(int ssN) {
        this.ssN = ssN;
        passN = 0;
    }

    /*
     * 안 되는 이유: 이건 자바 문법이 아니라 C/C++의 "기본 인자(default argument)" 문법이다.
     * 자바는 매개변수 목록에 "타입 이름"만 올 수 있고 "= 기본값"을 붙이는 문법 자체가 없다.
     * 그래서 아래 코드는 컴파일 에러(그것도 의미 문제가 아니라 파싱 단계 구문 에러)가 난다.
     * 자바에서 이와 같은 효과를 내려면 지금 위에서처럼 매개변수 개수가 다른 생성자를
     * 직접 오버로딩해서 여러 개 정의해야 한다.
     *
     * Person(int ssN, int passN = 0) {
     *     this.ssN = ssN;
     *     this.passN = passN;
     * }
     */

    /*
     * 단, 다음은 가능하다.
     * "생성자가 생성자를 호출"하는 방식(생성자 체이닝)으로 중복을 줄일 수 있다.
     * this(...)는 반드시 생성자의 첫 줄에서만 호출 가능하며, 자기 자신과 다른
     * 매개변수 목록을 가진 "같은 클래스의 다른 생성자"를 그 자리에서 실행해준다.
     *
     * Person(int ssN, int passN) {
     *     this.ssN = ssN;
     *     this.passN = passN;
     * }
     * Person(int ssN) {
     *     this(ssN, 0);   // 위의 생성자를 그대로 호출 -- passN 자리에 0을 넘겨줌
     * }
     */

    void showPersonInfo() {
        System.out.println("SSN: " + this.ssN);

        if(this.passN != 0)
            System.out.println("Passport Number: " + this.passN);
        else
            System.out.println("Not have passport.");
    }
}

public class P242_ConOverloading {
    public static void main(String[] args) {
        // Info for those who have passport.
        Person jung = new Person(335577, 112233);

        // Info for those who don't have passport.
        Person hong = new Person(775544);

        jung.showPersonInfo();
        hong.showPersonInfo();
    }
}