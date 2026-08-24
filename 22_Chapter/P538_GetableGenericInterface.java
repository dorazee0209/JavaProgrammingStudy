interface Getable<T> {
    public T get();
}

// 인터페이스 Getable<T>를 구현하는 Box<T> 클래스
class Box<T> implements Getable<T> {
    private T ob;
    public void set(T ob) {
        this.ob = ob;
    }
    @Override
    public T get() {
        return ob;
    }
}

class Toy {
    @Override
    public String toString() {
        return "I am a toy.";
    }
}

public class P538_GetableGenericInterface {
    public static void main(String[] args) {
        Box<Toy> box = new Box<>();
        box.set(new Toy());

        // Box<T>가 Getable<T>를 구현하므로 참조 가능
        Getable<Toy> gt = box;
        System.out.println(gt.get());
    }
}

