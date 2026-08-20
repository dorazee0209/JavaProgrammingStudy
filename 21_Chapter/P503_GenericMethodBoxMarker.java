class Box<T> {
    private T ob;

    public void set(T ob) {
        this.ob = ob;
    }

    public T get() {
        return ob;
    }
}

class BoxFactory {
    public static <T> Box<T> makeBox(T o) {
        Box<T> box = new Box<>();
        box.set(o);
        return box;
    }
}

public class P503_GenericMethodBoxMarker {
    public static void main(String[] args) {
        Box<String> sBox = BoxFactory.makeBox("Sweet");
        System.out.println(sBox.get());

        Box<Double> dBox = BoxFactory.makeBox(7.59);
        System.out.println(dBox.get());
    }
}

