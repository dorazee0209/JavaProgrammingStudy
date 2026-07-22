class Cake {
    public void yummy() {
        System.out.println("Yummy cake~");
    }
}

class CheeseCake extends Cake {
    public void yummy() {
        System.out.println("Yummy cheese cake");
    }
}

public class P337_YummyCakeOverriding {
    public static void main(String[] args) {
        Cake c1 = new CheeseCake();
        CheeseCake c2 = new CheeseCake();

        c1.yummy();
        c2.yummy();
    }
}