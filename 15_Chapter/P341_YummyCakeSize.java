class Cake {
    public int size;

    public Cake(int size) {
        this.size = size;
    }

    public void showCakeSize() {
        System.out.println("size: " + size);
    }
}

class CheeseCake extends Cake {
    public int size;

    public CheeseCake(int sz, int size) {
        super(sz);
        this.size = size;
    }

    public void showCakeSize() {
        System.out.println("size: " + super.size);
        System.out.println("size2: " + size);
    }
}

public class P341_YummyCakeSize {
    public static void main(String[] args) {
        CheeseCake ca1 = new CheeseCake(5, 7);
        Cake ca2 = ca1;

        // ca2는 Cake형이므로 ca2.size는 Cake의 멤버 size를 의미함.
        System.out.println("size: " + ca2.size);

        // ca1은 CheeseCake형이므로 ca1.size는 CheeseCake의 멤버 size를 의미함.
        System.out.println("size: " + ca1.size);
        System.out.println();

        ca1.showCakeSize();
        System.out.println();
        ca2.showCakeSize();
    }
}

