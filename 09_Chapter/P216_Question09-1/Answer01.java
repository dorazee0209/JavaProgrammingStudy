/*
 * [문제 09-1] 다양한 클래스의 정의
 *
 * 먼저 클래스 하나를 다음과 같이 제시하겠다.
 *
 *     class Point {
 *         int xPos, yPos;
 *         public Point(int x, int y) {
 *             xPos = x;
 *             yPos = y;
 *         }
 *         public void showPointInfo() {
 *             System.out.println("[" + xPos + ", " + yPos + "]");
 *         }
 *     }
 *
 * 이 클래스를 기반으로(활용하여) 원을 의미하는 Circle 클래스를 정의하자.
 * Circle 클래스는 좌표 상의 위치 정보(원의 중심 좌표)와 반지름의 길이
 * 정보를 저장할 수 있어야 한다. 그리고 다음 수준의 main 메소드를 기반으로
 * Circle 클래스를 테스트하자.
 *
 *     public static void main(String[] args) {
 *         Circle c = new Circle(2, 2, 4);    // 좌표 [2, 2] 반지름 4인 원의 생성
 *         c.showCircleInfo();                // 원의 정보 출력
 *     }
 *
 * 위의 main 메소드에서 showCircleInfo 메소드 호출을 통해서 원의 정보를
 * 출력했을 때, 원의 좌표 정보와 반지름 정보는 반드시 출력이 되도록 구현해야 한다.
 */

public class Answer01 {
    public static void main(String[] args) {

    }
}
