public class P447_AutoBoxingUnboxing2 {
    public static void main(String[] args) {
        Integer num = 10; // Auto Boxing
        num++; // Auto Boxing & Unboxing
        System.out.println(num);

        num += 3; // Auto Boxing & Unboxing
        
        int r = num + 5; // Auto Unboxing
        Integer rObj = num - 5; // Auto Unboxing
        System.out.println(r);
        System.out.println(rObj);
    }
}

