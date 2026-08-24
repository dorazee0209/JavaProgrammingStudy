interface Getable<T> {
    public T get();
}

class Box<T> implements Getable<String> {
    private T ob;
    public void set(T ob) {
        this.ob = ob;
    }

    @Override
    public String get() {
        return this.ob.toString();
    }
}

class Toy {
    @Override
    public String toString() {
        return "I am a toy.";
    }
}

public class P539_GetableGenericInterface2 {
    public static void main(String[] args) {
        Box<Toy> box = new Box<>();
        box.set(new Toy());

        Getable<String> gt = box;
        System.out.println(gt.get());
    }
}

