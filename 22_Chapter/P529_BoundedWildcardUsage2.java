class Box<T> {
    private T ob;

    public void set(T ob) {
        this.ob = ob;
    }
    public T get() {
        return ob;
    }
}

class Toy {
    @Override
    public String toString() {
        return "I am a Toy.";
    }
}

class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        Toy toy = box.get();
        System.out.println(toy);
    }
    public static void inBox(Box<? super Toy> box, Toy toy) {
        box.set(toy);
    }
}

public class P529_BoundedWildcardUsage2 {
    public static void main(String[] args) {
        Box<Toy> box = new Box<>();
        BoxHandler.inBox(box, new Toy());
        BoxHandler.outBox(box);
    }
}

