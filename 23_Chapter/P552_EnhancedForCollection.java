import java.util.LinkedList;
import java.util.List;

public class P552_EnhancedForCollection {
    public static void main(String[] args) {
        List<String> lst = new LinkedList<>();

        lst.add("Toy");
        lst.add("Box");
        lst.add("Robot");

        for (String e : lst)
            System.out.print(e + "\t");
        System.out.println();

        lst.remove(0);

        for (String e : lst)
            System.out.print(e + "\t");
        System.out.println();
    }
}