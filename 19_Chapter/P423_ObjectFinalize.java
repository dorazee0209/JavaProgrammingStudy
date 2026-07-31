class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("destroyed: " + name);
    }
}

public class P423_ObjectFinalize {
    public static void main(String[] args) {
        Person p1 = new Person("Yoon");
        Person p2 = new Person("Kim");

        p1 = null; // Let p1 be the target of GC
        p2 = null; // Let p2 be the target of GC

//        System.gc();
//        System.runFinalization();

        System.out.println("EOP");
    }
}