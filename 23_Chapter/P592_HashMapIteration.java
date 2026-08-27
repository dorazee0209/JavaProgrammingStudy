import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class P592_HashMapIteration {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        // Key-Value 기반 데이터 저장
        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        // Key만 담고 있는 컬렉션 인스턴스 생성
        Set<Integer> keySet = new HashSet<>(map.keySet());

        // 전체 key 출력(반복자 기반)
        for (Iterator<Integer> i = keySet.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }
        System.out.println();

        // Printing all values(based on for-each)
        for (Integer e : keySet) {
            System.out.println(map.get(e));
        }
        System.out.println();

        // Printing all valude(based on Iterator)
        for (Iterator<Integer> i = keySet.iterator(); i.hasNext(); ) {
            System.out.println(map.get(i.next()));
        }
        System.out.println();
    }
}