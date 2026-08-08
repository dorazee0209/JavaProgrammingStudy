public class P444_BoxingUnboxing {
    public static void main(String[] args) {
        Integer iObj = new Integer(10); // Boxing
        Double dObj = new Double(3.14); // Boxing
        System.out.println(iObj);
        System.out.println(dObj);
        System.out.println();

        int num1 = iObj.intValue(); // Unboxing
        double num2 = dObj.doubleValue(); // Unboxing
        System.out.println(num1);
        System.out.println(num2);
        System.out.println();

        // Wrapper instance 값 증가 방법
        iObj = new Integer(iObj.intValue() + 10);
        dObj = new Double(dObj.doubleValue() + 1.2);
        System.out.println(iObj);
        System.out.println(dObj);
    }
}

