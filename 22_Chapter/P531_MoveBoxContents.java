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

class Toy {
    @Override
    public String toString() {
        return "I am a toy.";
    }
}

class BoxContentsMover {
    public static void moveBox(Box<? extends Toy> box1, Box<? super Toy> box2) {
        box2.set(box1.get());
    }
}

public class P531_MoveBoxContents {
    public static void main(String[] args) {
        Box<Toy> box1 = new Box<>();
        Box<Toy> box2 = new Box<>();

        box1.set(new Toy());
        BoxContentsMover.moveBox(box1, box2);
        System.out.println(box2);
    }
}