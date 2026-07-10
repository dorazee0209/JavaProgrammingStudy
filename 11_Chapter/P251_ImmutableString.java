public class P251_ImmutableString {
    public static void main(String[] args) {
        String str1 = "Simple String";
        String str2 = "Simple String";

        String str3 = new String("Simple String");
        String str4 = new String("Simple String");

        System.out.print("str1과 str2는 ");
        if(str1 == str2)
            System.out.print("동일");
        else
            System.out.print("다른");
        System.out.println(" 인스턴스 참조");

        System.out.println();
        System.out.print("str3과 str4는 ");
        if(str3 == str4)
            System.out.print("동일");
        else
            System.out.print("다른");
        System.out.println(" 인스턴스 참조");
    }
}