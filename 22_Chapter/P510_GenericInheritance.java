class Box<T> {
    protected T ob;

    public void set(T ob) {
        this.ob = ob;
    }

    public T get() {
        return ob;
    }
}

class SteelBox<T> extends Box<T> {
    public SteelBox(T o) { // 제너릭 클래스의 생성자
        ob = o;
    }
}

public class P510_GenericInheritance {
    public static void main(String[] args) {
        Box<Integer> iBox = new SteelBox<>(5959);
        Box<String> sBox = new SteelBox<>("Simple");

        System.out.println(iBox.get());
        System.out.println(sBox.get());
    }
}

