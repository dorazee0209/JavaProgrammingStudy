class Box<T> {
    private T ob;
    public void set(T ob) {
        this.ob = ob;
    }
    public T get() {
        return ob;
    }
    @Override
    public String toString() {
        return ob.toString();
    }
}

class Unboxer {
    public static <T> T openBox(Box<T> ob) {
        return ob.get();
    }

    // 상자 안의 내용물을 확인하는(출력하는) 기능의 제네릭 메소드
    public static <T> void peekBox(Box<T> ob) {
        System.out.println(ob);
    }
}

public class P515_WildcardBoxer {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.set("So Simple String");
        Unboxer.peekBox(box);
    }
}

