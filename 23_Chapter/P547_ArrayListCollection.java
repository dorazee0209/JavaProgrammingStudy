import java.util.List;
import java.util.ArrayList;

public class P547_ArrayListCollection {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 컬렉션 인스턴스에 문자열 인스턴스 저장
        list.add("Toy");
        list.add("Box");
        list.add("Robot");

        // 저장된 문자열의 인스턴스 참조
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + "\t");
        System.out.println();

        list.remove(0); // 첫 번째 인스턴스 삭제

        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + "\t");
        System.out.println();
    }
}