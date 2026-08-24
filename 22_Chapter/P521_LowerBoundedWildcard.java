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
    public static void peekBox(Box<? super Integer> box) {
        System.out.println(box);
    }
}

public class P521_LowerBoundedWildcard {
    public static void main(String[] args){
        Box<Integer> iBox = new Box<>();
        iBox.set(3);

        Box<Number> nBox = new Box<>();
        nBox.set(new Integer(4));

        Box<Object> oBox = new Box<>();
        oBox.set("My simple instance.");

        Unboxer.peekBox(iBox);
        Unboxer.peekBox(nBox);
        Unboxer.peekBox(oBox);
    }
}

