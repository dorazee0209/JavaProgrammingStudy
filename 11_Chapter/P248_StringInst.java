public class P248_StringInst {
    public static void showString(String str) {
        System.out.println(str);
        System.out.println(str.length());
    }

    public static void main(String[] args) {
        String str1 = new String("Simple String");
        String str2 = "The best string";

        System.out.println(str1);
        System.out.println(str1.length());
        System.out.println();
        P248_StringInst.showString(str1);
        System.out.println();

        System.out.println(str2);
        System.out.println(str2.length());
        System.out.println();
        P248_StringInst.showString(str2);
        System.out.println();

        P248_StringInst.showString("Funny String");
    }
}

/*
 * 참고: showString()을 "P248_StringInst." 없이 그냥 showString(...)으로만
 * 호출해도 동일하게 동작한다. main과 showString이 같은 클래스에 속한
 * static 메소드라서, 컴파일러가 "지금 이 클래스 안에서 찾기"를 자동으로
 * 해주기 때문이다. 클래스명은 "다른 클래스"의 static 멤버를 부를 때만 필수.
 */

