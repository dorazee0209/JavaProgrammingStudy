import java.util.*;

public class P594_TreeMapIteration {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(45, "Brown");
        treeMap.put(37, "James");
        treeMap.put(23, "Martin");

        // Key만 담고있는 컬렉션 인스턴스 생성
        Set<Integer> keySet = treeMap.keySet();

        // Printing all Keys(based on for-each)
        System.out.println("Printing all Keys(based on for-each)");
        for(Integer e : keySet)
            System.out.println(e);
        System.out.println();

        // Printing all keys(based on Iterator)
        System.out.println("Printing all Keys(based on Iterator)");
        for(Iterator<Integer> i = keySet.iterator(); i.hasNext(); )
            System.out.println(i.next());
        System.out.println();

        // Printing all values(based on for-each)
        System.out.println("Printing all values(based on for-each)");
        for(Integer k : keySet)
            System.out.println(treeMap.get(k));
        System.out.println();

        // Printing all values(based on Iterator)
        System.out.println("Printing all values(based on Iterator)");
        for(Iterator<Integer> k = keySet.iterator(); k.hasNext(); )
            System.out.println(treeMap.get(k.next()));
        System.out.println();
    }
}
