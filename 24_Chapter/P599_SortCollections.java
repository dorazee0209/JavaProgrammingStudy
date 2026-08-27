import java.util.*;

public class P599_SortCollections {
    public static void main(String[] args) {
        List<String> sList = Arrays.asList("Toy", "Box", "Robot", "Weapon");
        sList = new ArrayList<>(sList);

        for(String e : sList)
            System.out.println(e);
        System.out.println();

        Collections.sort(sList);

        for(Iterator<String> e = sList.iterator(); e.hasNext(); )
            System.out.println(e.next());
        System.out.println();
    }
}