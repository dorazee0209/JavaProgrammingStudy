class Inum {
    private int num;

    public Inum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.num == ((Inum)obj).num)
            return true;
        else
            return false;
    }
}

public class P425_ObjectEquality {
    public static void main(String[] args) {
        Inum n1 = new Inum(10);
        Inum n2 = new Inum(12);
        Inum n3 = new Inum(10);

        mkSentences(n1, n2, n1.equals(n2));
        mkSentences(n1, n3, n1.equals(n3));
    }
    public static void mkSentences(Inum tg1, Inum tg2, boolean bool) {
        System.out.printf("%s..%s: %s\n", tg1.getClass().getName(), tg2.getClass().getName(), bool ? "Same" : "Diff");
    }
}