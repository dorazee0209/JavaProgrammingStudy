import java.util.*;

public class P559_ConversionCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        list = new ArrayList<>(list);

        // ArrayList<E> 인스턴스 순환
        for(Iterator<String> i = list.iterator(); i.hasNext(); )
            System.out.print(i.next() + "\t");
        System.out.println();

        // ArrayList<E> 인스턴스 기반으로 LinkedList<E> 인스턴스 생성
        list = new LinkedList<>(list);

        // LinkedList<E> 인스턴스의 순환
        for(Iterator<String> iter = list.iterator(); iter.hasNext(); )
            System.out.print(iter.next() + "\t");
        System.out.println();
    }
}

