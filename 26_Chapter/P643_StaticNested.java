public class P643_StaticNested {
    public static void main(String[] args) {
        Outer.Nested01 nst1 = new Outer.Nested01();
        Outer.Nested01.add(3);

        Outer.Nested02 nst2 = new Outer.Nested02();
        System.out.println(outer.Nested02.get());
    }
}

class Outer {
    public static int num = 0;

    static class Nested01 {
        void add(int n) {
            num += n;
        }
    }
    static class Nested02 {
        int get() {
            return num;
        }
    }
}