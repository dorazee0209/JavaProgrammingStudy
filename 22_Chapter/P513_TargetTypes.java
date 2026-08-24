class Box<T> {
    private T ob;
    public void set(T ob) { this.ob = ob; }
    public T get() { return ob; };
}

class EmptyBoxFactory {
    public static <T> Box<T> makeBox() {
        Box<T> box = new Box<>();
        return box;
    }
}

public class P513_TargetTypes {
    public static void main(String[] args) {
        Box<Integer> iBox = EmptyBoxFactory.<Integer>makeBox();
        iBox.set(25);
        System.out.println(iBox.get());
    }
}

