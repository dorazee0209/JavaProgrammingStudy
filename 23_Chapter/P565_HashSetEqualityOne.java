import java.util.HashSet;

public class P565_HashSetEqualityOne {
    public static void main(String[] args) {
        HashSet<Num> set = new HashSet<>();
        set.add(new Num(7799));
        set.add(new Num(9955));
        set.add(new Num(7799));
        System.out.println("# of instance: " + set.size());

        for(Num n : set) {
            System.out.print(n.toString() + "\t");
        }
    }
}

class Num {
    private int num;

    public Num(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.valueOf(this.num);
    }
}