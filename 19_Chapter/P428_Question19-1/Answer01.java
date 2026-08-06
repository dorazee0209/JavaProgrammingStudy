/*
 * [문제 19-1] equals 메소드의 정의
 *
 * 아래의 Point 클래스와 Rectangle 클래스에 내용 비교를 위한 equals 메소드를 각각 삽입하
 * 자. 그리고 정의한 equals 메소드의 확인을 위한 main 메소드도 직접 정의하자.
 *
 *     class Point {
 *         private int xPos;
 *         private int yPos;
 *         public Point(int x, int y) {
 *             xPos = x;
 *             yPos = y;
 *         }
 *     }
 *     class Rectangle {
 *         private Point upperLeft;         // 좌측 상단 좌표
 *         private Point lowerRight;        // 우측 하단 좌표
 *         public Rectangle(int x1, int y1, int x2, int y2) {
 *             upperLeft = new Point(x1, y1);
 *             lowerRight = new Point(x2, y2);
 *         }
 *     }
 */

class Point {
    private int xPos;
    private int yPos;
    public Point(int x, int y) {
        xPos = x;
        yPos = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.xPos == ((Point)obj).xPos && this.yPos == ((Point)obj).yPos)
            return true;
        else
            return false;
    }
}

class Rectangle {
    private Point upperLeft;
    private Point lowerRight;
    public Rectangle(int x1, int y1, int x2, int y2) {
        upperLeft = new Point(x1, y1);
        lowerRight = new Point(x2, y2);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.upperLeft.equals(((Rectangle)obj).upperLeft) && this.lowerRight.equals(((Rectangle)obj).lowerRight))
            return true;
        else 
            return false;
    }
}

public class Answer01 {
    
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(12, 2);

        Rectangle r1 = new Rectangle(1, 1, 1, 1);
        Rectangle r2 = new Rectangle(1, 1, 1, 1);
        Rectangle r3 = new Rectangle(2, 2, 2, 2);

        if(p1.equals(p2))
            System.out.println("Same contents");
        else
            System.out.println("Diff");

        if(p1.equals(p3))
            System.out.println("Same contents");
        else
            System.out.println("Diff");

        if(r1.equals(r2))
            System.out.println("Same rec");
        else
            System.out.println("Diff rec");

        if(r1.equals(r3))
            System.out.println("Same rec");
        else
            System.out.println("Diff rec");
    }
}
