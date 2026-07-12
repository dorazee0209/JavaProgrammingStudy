class Box {
    private String conts;

    Box(String cont) {
        this.conts = cont;
    }

    public String toString() {
        return conts;
    }
}

public class P287_ArrayIsInstance2 {
    public static void main(String[] args) {
        Box[] arr = new Box[5];
        System.out.printf("Length: %d \n", arr.length);
    }
}

