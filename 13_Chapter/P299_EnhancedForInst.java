class Box {
    private String contents;
    private int boxNum;

    Box(int num, String cont) {
        boxNum = num;
        contents = cont;
    }

    public int getBoxNum() {
        return boxNum;
    }
    public String toString() {
        return contents;
    }
}

public class P299_EnhancedForInst {
    public static void main(String[] args) {
        Box[] arr = new Box[5];
        arr[0] = new Box(101, "Coffee");
        arr[1] = new Box(202, "Computer");
        arr[2] = new Box(303, "Apple");
        arr[3] = new Box(404, "Dress");
        arr[4] = new Box(505, "Fairy-tale book");

        for (Box x : arr) {
            if(x.getBoxNum() == 505)
                System.out.println(x);
        }
    }
}