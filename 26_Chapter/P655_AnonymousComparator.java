import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class P655_AnonymousComparator {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("Robot");
        strList.add("Box");
        strList.add("Rabbit");

        Comparator<String> cmp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        Collections.sort(strList, cmp);
        System.out.println(strList);
    }
}

