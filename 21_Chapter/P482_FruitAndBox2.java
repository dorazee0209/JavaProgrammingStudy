class Apple {
    @Override
    public String toString() {
        return "I am an apple.";
    }
}

class Orange {
    @Override
    public String toString() {
        return "I am an orange.";
    }
}

class Box {
    private Object ob;

    public void set(Object o) {
        ob = o;
    }

    public Object get() {
        return ob;
    }
}

public class P482_FruitAndBox2 {
    public static void main(String[] args) {
        Box aBox = new Box();
        Box oBox = new Box();

        aBox.set(new Apple());
        oBox.set(new Orange());

        Apple a = (Apple)aBox.get();
        Orange o = (Orange)oBox.get();

        System.out.println(a);
        System.out.println(o);
    }
}

