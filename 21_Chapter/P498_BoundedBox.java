class Box<T extends Number> {
    private T ob;

    public void set(T ob) {
        this.ob = ob;
    }

    public T get() {
        return ob;
    }
}

public class P498_BoundedBox {
    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>();
        box1.set(24);

        Box<Double> box2 = new Box<>();
        box2.set(5.97);

        System.out.println(box1.get());
        System.out.println(box2.get());
    }
}