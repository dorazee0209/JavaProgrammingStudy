class SimpleBox {
    private int data;

    SimpleBox(int data) {
        this.data = data;
    }

    void setData(int data) {
        this.data = data;
    }
    int getData() {
        return this.data;
    }
}

public class P245_ThisInst {
    public static void main(String[] args) {
        SimpleBox sb = new SimpleBox(99);
        System.out.println(sb.getData());

        sb.setData(77);
        System.out.println(sb.getData());
    }
}

