public class P427_StringEquality {
    public static void main(String[] args) {
        String str1 = new String("So simple");
        String str2 = new String("So simple");
    
        cmpByInstance(str1, str2);
        cmpByReference(str1, str2);

    }
    public static void cmpByReference(String str1, String str2) {
        // 참조대상을 비교
        if(str1 == str2)
            System.out.println("참조 대상 동일");
        else
            System.out.println("참조대상 다름");
    }
    public static void cmpByInstance(String str1, String str2) {
        // 인스턴스 내용을 비교
        if(str1.equals(str2))
            System.out.println("내용 동일");
        else
            System.out.println("내용 다름");
    }
}
