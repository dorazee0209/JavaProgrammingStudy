class Box<T> {
    private T ob;

    public void set(T ob) {
        this.ob = ob;
    }

    public T get() {
        return ob;
    }
}

public class P494_PrimitivesAndGenerics {
    public static void main(String[] args) {
        Box<Integer> iBox = new Box<>();
        iBox.set(10); // Auto Boxing
        int num = iBox.get(); // Auto Unboxing
        System.out.println(num);
    }
}

