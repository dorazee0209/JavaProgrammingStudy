/*
 * [문제 11-1] String 클래스의 활용
 *
 * 다음 주민등록 번호의 중간에 삽입된 - 를 지우고 공백으로 채워서 출력하는 프로그램을 작성해
 * 보자.
 *     990925-1012999
 *
 * 답안은 출판사 홈페이지를 통해서 제공합니다.
 */

public class Answer01 {
    public static void main(String[] args) {
        String SSN = "990925-1012999";
        SSN = SSN.substring(0, 6).concat(" ").concat(SSN.substring(7));

        System.out.println(SSN);
    }
}
