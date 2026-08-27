import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class P581_ConvertCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Box", "Toy", "Box", "Toy");
        ArrayList<String> arr = new ArrayList<>(list);

        for(String s : arr)
            System.out.print(s + "\t");
        System.out.println();

        // filtering duplicated elements
        TreeSet<String> tree = new TreeSet<>(arr);
        for(String s : tree)
            System.out.print(s + "\t");
        System.out.println();
    }
}

