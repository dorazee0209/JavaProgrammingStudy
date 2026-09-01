import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class P654_SortComparator {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("ROBOT");
        strList.add("APPLE");
        strList.add("BOX");

        StrComp cmp = new StrComp();
        Collections.sort(strList, cmp);
//        System.out.println(strList);
        for(String e : strList)
            System.out.println(e);
    }
}

class StrComp implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}