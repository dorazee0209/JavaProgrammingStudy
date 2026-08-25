import java.util.*;

public class P561_ListIteratorCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Robot", "Box", "Robot");
        list = new LinkedList<>(list);

        ListIterator<String> litr = list.listIterator();

        while(litr.hasNext()) {
            String str = litr.next();
            System.out.print(str + "\t");
            if(str.equals("Toy"))
                litr.add("Toy2");
        }
        System.out.println();

        while(litr.hasPrevious()) {
            String str;
            str = litr.previous();
            System.out.print(str + "\t");
            if(str.equals("Robot"))
                litr.add("Robot2");
        }
        System.out.println();

        for(Iterator<String> i = list.iterator(); i.hasNext(); )
            System.out.print(i.next() + "\t");
        System.out.println();
    }
}