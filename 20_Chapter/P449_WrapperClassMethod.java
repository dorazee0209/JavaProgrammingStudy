public class P449_WrapperClassMethod {
    public static void main(String[] args) {
        // 클래스 메소드를 통한 인스턴스 생성 방법 2가지
        Integer n1 = new Integer(5); // 숫자 기반
        Integer n2 = new Integer("1024"); // 문자 기반

        // 대소비교와 합을 계산하는 클래스 메소드
        System.out.printf("Max: %d\n", Integer.max(n1, n2));
        System.out.printf("Min: %d\n", Integer.min(n1, n2));
        System.out.printf("Sum: %d\n\n", Integer.sum(n1, n2));

        // 정수에 대한 2, 8, 16진 표현 결과를 반환하는 클래스 메소드
        System.out.printf("12의 2진수 표현: %s\n", Integer.toBinaryString(12));
        System.out.printf("12의 8진수 표현: %s\n", Integer.toOctalString(12));
        System.out.printf("12의 16진수 표현: %s\n", Integer.toHexString(12));
    }
}

