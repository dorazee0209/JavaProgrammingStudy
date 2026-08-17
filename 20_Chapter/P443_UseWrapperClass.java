public class P443_UseWrapperClass {
    public static void showData(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        Integer iInt = new Integer(3);
        showData(iInt);
        showData(new Double(3.14));
    }
}

