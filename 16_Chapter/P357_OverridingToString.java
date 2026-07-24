class Cake {
    // Object 클래스의 toString 메소드를 오버라이딩
    public String toString() {
        // Object 클래스의 toString 메소드를 호출하여 반환 결과 출력
        System.out.println(super.toString());
        return "My birthday cake";
    }
}

class CheeseCake extends Cake {
    // Cake 클래스의 toString 메소드를 오버라이딩
    public String toString() {
//        System.out.println(super.toString());
        return "My birthday cheese cake";
    }
}

public class P357_OverridingToString {
    public static void main(String[] args) {
        Cake c1 = new Cake();
        Cake c2 = new CheeseCake();

        System.out.println(c1);
        System.out.println();

        System.out.println(c2);
    }
}