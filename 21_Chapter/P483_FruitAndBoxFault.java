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

public class P483_FruitAndBoxFault{
    public static void main(String[] args) {
        Box aBox = new Box();
        Box oBox = new Box();

        // 사과와 오렌지가 아닌 문자열을 담는다.
        aBox.set("Apple");
        oBox.set("Orange");

        // 과일이 담기지도 않았는데 꺼내기 시도
        Apple a = (Apple)aBox.get();
        Orange o = (Orange)oBox.get();

        System.out.println(a);
        System.out.println(o);
    }
}

