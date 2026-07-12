import java.util.Scanner;

class Box {
    private String conts;

    Box(String cont) {
        this.conts = cont;
    }

    public String toString() {
        return conts;
    }
}

public class P288_BoxArray {
    public static void main(String[] args) {
        Box[] arr = new Box[3];
        Scanner sc = new Scanner("First\nSecond\nThird");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Box(sc.nextLine());
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

/*
 * 참고: 원래(수정 전) 입력은 공백으로 구분된 형태였다.
 *     Scanner sc = new Scanner("First Second Third");
 * nextLine()은 "줄바꿈" 기준이라 이 입력에서는 첫 호출에 전체를 다 읽어버려서
 * NoSuchElementException이 났었다. 이땐 메소드를 바꾸는 방법도 있다 -- 입력은
 * 그대로 두고 nextLine() 대신 next()를 쓰면 된다.
 *     arr[i] = new Box(sc.next());
 * next()는 "공백류(스페이스/탭/줄바꿈)" 기준으로 토큰 하나씩 읽기 때문에,
 * 공백으로 구분된 "First Second Third"도 문제없이 세 조각으로 나눠 읽힌다.
 * 지금 소스는 반대로 입력 문자열을 \n으로 바꿔서 nextLine()에 맞춘 방식이다 --
 * 어느 쪽으로 고쳐도(메소드 변경 vs 입력 형태 변경) 결과는 동일하다.
 */

