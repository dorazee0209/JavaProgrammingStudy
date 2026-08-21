class Box<T> {
    private T ob;
    public void set(T ob) { this.ob = ob; }
    public T get() { return ob; }
    @Override
    public String toString() {
        return ob.toString();
    }
}

class Unboxer {
    public static <T> T openBox(Box<T> box) {
        return box.get();
    }
    public static void peekBox(Box<?> box) { // 와일드카드 사용
        System.out.println(box);
    }
}

public class P516_wildcardUnboxer2 {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.set("So Simple String.");
        Unboxer.peekBox(box);
    }
}

