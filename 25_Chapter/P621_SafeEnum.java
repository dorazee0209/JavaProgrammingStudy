public class P621_SafeEnum {
    public static void main(String[] args) {
        who(Person.MAN);
//        who(Animal.DOG); <- 주석 처리 해제 후 실행 시 오류 발생
    }

    static void who(Person person) {
        switch (person) {
            case MAN:
                System.out.println("남성 손님입니다.");
                break;
            case WOMAN:
                System.out.println("여성 손님입니다.");
                break;
        }
    }
}

enum Person {
    MAN, WOMAN
}

enum Animal {
    DOG, CAT
}