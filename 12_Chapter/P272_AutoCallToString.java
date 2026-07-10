class Box {
    private String conts;

    Box(String cont) {
        this.conts = cont;
    }

    public String toString() {
        return conts;
    }
}

public class P272_AutoCallToString {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("12");
        sb.append(34);
        System.out.println(sb.toString());
        System.out.println(sb);

        Box box = new Box("Camera");
        System.out.println(box.toString());
        System.out.println(box);
    }
}

