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
    public static void peekBox(Box<? extends Number> box) {
        System.out.println(box);
    }
}

public class P519_UpperBoundedWildcard {
    public static void main(String[] args) {
        Box<Integer> iBox = new Box<>();
        iBox.set(1234);
        Box<Double> dBox = new Box<>();
        dBox.set(10.099);

        System.out.println(iBox);
        System.out.println(dBox);
    }
}

