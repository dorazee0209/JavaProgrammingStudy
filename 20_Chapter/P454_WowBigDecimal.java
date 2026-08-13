import java.math.BigDecimal;

public class P454_WowBigDecimal {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("1.6");
        BigDecimal d2 = new BigDecimal("0.1");
        System.out.println("Add: " + d1.add(d2));
        System.out.println("Mul: " + d1.multiply(d2));
    }
}