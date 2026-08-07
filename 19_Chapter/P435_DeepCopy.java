class Point implements Cloneable {
    private int xPos;
    private int yPos;

    // Constructor
    public Point(int x, int y) {
        xPos = x;
        yPos = y;
    }

    // Getter
    public void showPosition() {
        System.out.printf("(%d, %d)\n", xPos, yPos);
    }

    // Setter
    public void changePosition(int x, int y) {
        xPos = x;
        yPos = y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Rectangle implements Cloneable {
    private Point upperLeft;
    private Point downRight;

    // Contructor
    public Rectangle(int x1, int y1, int x2, int y2) {
        upperLeft = new Point(x1, y1);
        downRight = new Point(x2, y2);
    }

    // Getter
    public void showPosition() {
        System.out.print("upperLeft: ");
        upperLeft.showPosition();
        System.out.print("downRight: ");
        downRight.showPosition();
    }

    // Setter
    public void changePosition(int x1, int y1, int x2, int y2) {
        upperLeft.changePosition(x1, y1);
        downRight.changePosition(x2, y2);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Object 클래스의 clone() 메소드 호출을 통한 복사본 생성
        Rectangle copy = (Rectangle)super.clone();

        // 깊은 복사의 형태로 복사본을 생성
        copy.upperLeft = (Point)upperLeft.clone();
        copy.downRight = (Point)downRight.clone();

        return copy;
    }
}

public class P435_DeepCopy {
    public static void main(String[] args) {
        Rectangle org = new Rectangle(1, 1, 9, 9);
        Rectangle cpy;

        try {
            cpy = (Rectangle)org.clone();
            showAll(org, cpy);

            System.out.println("Change cpy");
            cpy.changePosition(2, 2, 4, 4);
            showAll(org, cpy);

            System.out.println("Change org");
            org.changePosition(15, 15, 15, 15);
            showAll(org, cpy);
        }

        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void showAll(Rectangle r1, Rectangle r2) {
        r1.showPosition();
        r2.showPosition();
    }
}

