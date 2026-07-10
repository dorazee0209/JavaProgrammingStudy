public class P267_ReturnStringBuilder {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("123");
        StringBuilder sb2 = sb1.append(45678);

        System.out.println(sb1);
        System.out.println(sb2);

        sb2.delete(0, 5);

        System.out.println(sb1.toString());
        System.out.println(sb2.toString());

        if(sb1 == sb2)
            System.out.println("refer to the same instance");
        else
            System.out.println("refer to different instances");
    }
}

