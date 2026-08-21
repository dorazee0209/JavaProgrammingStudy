class Box<T> {
    private T ob;
    public void set(T ob) { this.ob = ob; }
    public T get() { return ob; }
}

class Toy {
    @Override
    public String toString() {
        return "I am a Toy.";
    }
}

class BoxHandler {
    public static void outBox(Box<Toy> box) {
        Toy t = box.get();
        System.out.println(t);
    }

    public static void inBox(Box<Toy> box, Toy n) {
        box.set(n);
    }
}

public class P523_BoundedWildcardBase {
    public static void main(String[] args){
        Box<Toy> box = new Box<>();
        BoxHandler.inBox(box, new Toy());
        BoxHandler.outBox(box);
    }
}

