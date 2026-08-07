class Point implements Cloneable {
    private int xPos;
    private int yPos;

    public Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void showPosition() {
        System.out.printf("(%d, %d)\n", xPos, yPos);
    }
    public void changePosition(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Rectangle implements Cloneable {
    private Point upperLeft;
    private Point downRight;

    public Rectangle(int x1, int y1, int x2, int y2) {
        upperLeft = new Point(x1, y1);
        downRight = new Point(x2, y2);
    }
    public void changePosition(int x1, int y1, int x2, int y2) {
        upperLeft.changePosition(x1, y1);
        downRight.changePosition(x2, y2);
    }
    public void showPosition() {
        System.out.print("upperLeft: ");
        upperLeft.showPosition();
        System.out.print("doweRight: ");
        downRight.showPosition();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class P431_ShallowCopy {
    public static void main(String[] args) {
        Rectangle org = new Rectangle(1, 1, 9, 9);
        Rectangle cpy;

        try {
            cpy = (Rectangle)org.clone();
            org.showPosition();
            cpy.showPosition();

            System.out.println("\nChange cpy\n");
            cpy.changePosition(1, 2, 3, 4);
            org.showPosition();
            cpy.showPosition();
            
            System.out.println("\nChange org\n");
            org.changePosition(2, 2, 7, 7);
            org.showPosition();
            cpy.showPosition();
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

