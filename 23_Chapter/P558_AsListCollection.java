import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class P558_AsListCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        list = new ArrayList<>(list);

        // for문 기반 반복자 획득과 순차적 참조
        for(Iterator i = list.iterator(); i.hasNext(); )
            System.out.print(i.next() + "\t");
        System.out.println();

        // Box를 삭제하기 위한 반복문
        for(Iterator i = list.iterator(); i.hasNext(); )
            if(i.next().equals("Box"))
                i.remove();

        for(Iterator i = list.iterator(); i.hasNext(); )
            System.out.print(i.next() + "\t");
        System.out.println();
    }
}