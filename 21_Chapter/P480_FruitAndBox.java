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

class AppleBox {
    private Apple ap;

    public void set(Apple a) {
        ap = a; // 사과를 담는다.
    }

    public Apple get() {
        return ap; // 사과를 꺼낸다.
    }
}

class OrangeBox {
    private Orange or;

    public void set(Orange o) {
        or = o; // 오렌지를 담는다.
    }

    public Orange get() {
        return or; // 오렌지를 꺼낸다.
    }
}

public class P480_FruitAndBox {
    public static void main(String[] args) {
        AppleBox aBox = new AppleBox();
        OrangeBox oBox = new OrangeBox();

        // 담는다.
        aBox.set(new Apple());
        oBox.set(new Orange());

        // 꺼낸다.
        Apple ap = aBox.get();
        Orange og = oBox.get();

        System.out.println(ap);
        System.out.println(og);
    }
}

