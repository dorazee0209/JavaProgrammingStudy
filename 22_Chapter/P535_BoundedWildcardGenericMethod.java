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
        return "I am a toy.";
    }
}

class Robot {
    @Override
    public String toString() {
        return "I am a robot.";
    }
}

class BoxHandler {
    public static <T> void outBox(Box<? extends T> box) {
        T t = box.get();
        System.out.println(t);
    }

    public static <T> void inBox(Box<? super T> box, T t) {
        box.set(t);
    }
}

public class P535_BoundedWildcardGenericMethod {
    public static void main(String[] args) {
        Box<Toy> toyBox = new Box<>();
        BoxHandler.inBox(toyBox, new Toy());
        BoxHandler.outBox(toyBox);

        Box<Robot> robotBox = new Box<>();
        BoxHandler.inBox(robotBox, new Robot());
        BoxHandler.outBox(robotBox);
    }
}