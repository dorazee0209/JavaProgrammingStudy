import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class P560_PrimitiveCollection {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        for(Iterator<Integer> i = list.iterator(); i.hasNext(); )
            System.out.print(i.next() + "\t");
        System.out.println();
    }
}

