public class P265_BuildString {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("123");

        sb.append(45678);
        System.out.println(sb.toString());

        sb.delete(0, 2);
        System.out.println(sb.toString());

        sb.replace(0, 3, "AB");
        System.out.println(sb.toString());

        sb.reverse();
        System.out.println(sb.toString());

        System.out.println(sb.substring(2, 4).toString());
    }
}

