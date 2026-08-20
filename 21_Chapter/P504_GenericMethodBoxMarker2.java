class Box<T> {
    private T ob;

    public void set(T ob) {
        this.ob = ob;
    }

    public T get() {
        return ob;
    }
}

class Unboxer {
      public static <T> T openBox(Box<T> box) {
          return box.get();
      }
}

public class P504_GenericMethodBoxMarker2 {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.set("My generic method");

        String str = Unboxer.openBox(box);
        System.out.println(str);
    }
}