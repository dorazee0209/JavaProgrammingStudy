import java.util.*;

public class P595_ComparatorTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>(new AgeComparator());
        treeMap.put(45, "Brown");
        treeMap.put(37, "James");
        treeMap.put(23, "Martin");

        // key만 담고 있는 컬렉션 인스턴스 생성
        Set<Integer> ks = treeMap.keySet();

        // 전체 key 출력(for-each)
        for(Integer e : ks)
            System.out.println(e);
        System.out.println();

        // 전체 value 출력(for-each)
        for(Integer e : ks)
            System.out.println(treeMap.get(e));
        System.out.println();

        // 전체 value 출력(for-each)
        for(Iterator<Integer> e = ks.iterator(); e.hasNext(); )
            System.out.println(treeMap.get(e.next()));
        System.out.println();
    }
}

class AgeComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer n1, Integer n2) {
        return n2.intValue() - n1.intValue();
    }
}
