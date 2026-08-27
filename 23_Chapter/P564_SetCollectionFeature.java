import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class P564_SetCollectionFeature {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Toy");
        set.add("Box");
        set.add("Robot");
        set.add("Box");
        System.out.println("인스턴스 수: " + set.size());

        // 반복자를 이용한 전체 출력
        for(Iterator<String> i = set.iterator(); i.hasNext(); )
            System.out.print(i.next() + "\t");
        System.out.println();

        // for-each문을 이용한 전체 출력
        for(String str : set) {
            System.out.print(str + "\t");
        }
        System.out.println();
        }
    }