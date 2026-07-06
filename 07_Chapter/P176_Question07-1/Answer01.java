/*
 * [문제 07-1] 생성자를 포함하는 클래스의 정의
 *
 * 밑변과 높이 정보를 저장할 수 있는 Triangle 클래스를 정의하자.
 * (그 안에 적절한 생성자도 정의하자.)
 * 그리고 밑변과 높이 정보를 변경할 수 있는 메소드와
 * 삼각형의 넓이를 계산해서 반환하는 메소드도 함께 정의하자.
 * 물론 이 클래스의 활용의 예를 보이는 main 메소드도 함께 작성해야 한다.
 */

class Triagle {
    int len;
    int hei;

    public Triagle(int len, int hei) {
        this.len = len;
        this.hei = hei;
    }

    public void chComp(int len, int hei) {
        this.len = len;
        this.hei = hei;
    }

    public double calS() {
        double S = len * hei / 2;
        System.out.println("S = " + S);
        return S;
    }
}

public class Answer01 {
    public static void main(String[] args) {
        Triagle t1 = new Triagle(3, 6);
        t1.calS();

        t1.chComp(6, 6);
        t1.calS();
    }
}
