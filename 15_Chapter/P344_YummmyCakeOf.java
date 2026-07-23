class Cake {}
class CheeseCake extends Cake {}
class StrawberryCheeseCake extends CheeseCake {}

public class P344_YummmyCakeOf {
    public static void main(String[] args) {
        Cake c1 = new StrawberryCheeseCake();
        CheeseCake c2 = new StrawberryCheeseCake();
        StrawberryCheeseCake c3 = new StrawberryCheeseCake();

        Object[] ary = new Object[3];
        ary[0] = c1;
        ary[1] = c2;
        ary[2] = c3;


//        if( instanceof Cake) {
//            System.out.println("케익 인스턴스 or");
//            System.out.println("케익 상속하는 인스턴스\n");
//        }
//        if( instanceof CheeseCake) {
//            System.out.println("치즈케익 인스턴스 or");
//            System.out.println("치즈케익 상속하는 인스턴스\n");
//        }
//        if(c1 instanceof StrawberryCheeseCake) {
//            System.out.println("딸기치즈케익 인스턴스 or");
//            System.out.println("딸기치즈케익 상속하는 인스턴스\n");
//        }

        for(Object i : ary) {
            if(i instanceof Cake) {
                System.out.println("케익 인스턴스 or");
                System.out.println("케익 상속하는 인스턴스\n");
            }
            if(i instanceof CheeseCake) {
                System.out.println("치즈케익 인스턴스 or");
                System.out.println("치즈케익 상속하는 인스턴스\n");
            }
            if(i instanceof StrawberryCheeseCake) {
                System.out.println("딸기치즈케익 인스턴스 or");
                System.out.println("딸기치즈케익 상속하는 인스턴스\n");
            }
            System.out.println("\n");
        }
    }
}

