import java.math.BigInteger;

public class P451_SoBigInteger {
    public static void main(String[] args) {
        // long형으로 표현 가능한 값의 최대 크기 출력 
        System.out.printf("Max: %d\n",Long.MAX_VALUE);
        System.out.printf("Min: %d\n\n", Long.MIN_VALUE);

        // 매우 큰 수를 BigInteger 인스턴스로 표현
        BigInteger big1 = new BigInteger("10000000000000000000");
        BigInteger big2 = new BigInteger("-9999999999999999999");

        // BigInteger 기반 덧셈
        BigInteger r1 = big1.add(big2);
        System.out.println("덧셈 결과: " + r1);

        // BigInteger 기반 덧셈
        BigInteger r2 = big1.multiply(big2);
        System.out.println("곱셈 결과: " + r2);
        System.out.println();
    }
}

