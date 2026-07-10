public class P260_CompString {
    public static void main(String[] args) {
        String str1 = "Lexicographically";
        String str2 = "lexicographically";
        int cmp;

        if(str1.equals(str2))
            System.out.println("Same");
        else
            System.out.println("Diff");

        cmp = str1.compareTo(str2);
        if(cmp == 0)
            System.out.println("Same");
        else if(cmp < 0)
            System.out.println("Preceding str: " + str1);
        else
            System.out.println("Preceding str: " + str2);

        cmp = str1.compareToIgnoreCase(str2);
        if(cmp == 0)
            System.out.println("Same");
        else
            System.out.println("Diff");
    }
}