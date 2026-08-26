import java.util.Iterator;
import java.util.TreeSet;

public class P573_SortedTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(4);
        System.out.println("# of instance: " + set.size());

        // for-each
        for(Integer e : set)
            System.out.print(e.toString() + "\t");
        System.out.println();

        // iterator
        for(Iterator<Integer> e = set.iterator(); e.hasNext(); )
            System.out.print(e.next().toString() + "\t");
        System.out.println();
    }
}

