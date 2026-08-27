import java.util.HashSet;
import java.util.Iterator;

public class P568_HashSetEqualityTwo {
    public static void main(String[] args) {
        HashSet<Num> set = new HashSet<>();
        set.add(new Num(7799));
        set.add(new Num(9955));
        set.add(new Num(7799));

        System.out.println("# of instance: " + set.size());

        for(Iterator<Num> i = set.iterator(); i.hasNext(); )
            System.out.println(i.next().toString() + "\t");
        System.out.println();
    }
}

class Num {
    private int num;

    public Num(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public int hashCode() {
        return num % 3;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.num == ((Num)obj).num)
            return true;
        else
            return false;
    }
}