public class P275_FormatString {
    public static void main(String[] args) {
        int age = 20;
        double height = 170;
        String name = "KSJ";

        System.out.printf("Name: %s \n", name);
        System.out.printf("Age: %d \nHeight: %e \n", age, height);

        System.out.printf("%d - %o - %x \n\n", 77, 77, 77);
        System.out.printf("%g \n", 0.00014);
        System.out.printf("%g \n", 0.000014);
    }
}

