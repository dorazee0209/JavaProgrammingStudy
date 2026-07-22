class Cake {
    public void yummy() {
        System.out.println("yummy cake");
    }
}

class CheeseCake extends Cake{
    public void yummy() {
        super.yummy();
        System.out.println("yummy cheese cake");
    }
    public void tasty() {
        super.yummy();
        System.out.println("yummy tasty cake");
    }
}

public class P340_YummyCakeSuper {
    public static void main(String[] args) {
        CheeseCake cake = new CheeseCake();
        cake.yummy();
        cake.tasty();

        Cake c = new CheeseCake();
        c.yummy();
    }
}