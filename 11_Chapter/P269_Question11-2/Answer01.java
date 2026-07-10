/*
 * [문제 11-2] StringBuilder 클래스의 활용
 *
 * 다음 주민등록 번호의 중간에 삽입된 - 를 지우고 공백으로 채워서 출력하는 프로그램을 작성해보
 * 자. 단 StringBuilder 클래스를 활용하여 빈번한 문자열의 생성이 발생하지 않도록 해야 한다.
 *     990925-1012999
 *
 * 답안은 출판사 홈페이지를 통해서 제공합니다.
 */

public class Answer01 {
    public static void main(String[] args) {
        StringBuilder SSN = new StringBuilder("990925-1012999");
        System.out.println(SSN.delete(6, 7).insert(6, " ").toString());
    }
}

/*
 * 참고: delete(6, 7) + insert(6, " ") 두 단계 대신,
 * replace(6, 7, " ") 하나로도 "삭제 후 그 자리에 삽입"을 동일하게 처리할 수 있다.
 *     SSN.replace(6, 7, " ");
 * 결과는 완전히 같지만 메소드 호출이 한 번 줄어든다 (공식 답안 키 방식).
 */
