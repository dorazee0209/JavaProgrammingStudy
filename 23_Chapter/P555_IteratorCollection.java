import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class P555_IteratorCollection {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("Toy");
        list.add("Robot");
        list.add("Box");
        list.add("Robot");

        // 반복자를 이용한 순차적 참조
        Iterator<String> itr = list.iterator();
        while(itr.hasNext())
            System.out.println(itr.next());
        System.out.println();

        itr = list.iterator(); // 반복자 재획득

        // 모든 Box 삭제
        while(itr.hasNext()) {
            String str = itr.next();
            if(str.equals("Box"))
                itr.remove();
        }

        itr = list.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println();
    }
}