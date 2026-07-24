class ParentAdder {
    public int add(int n1, int n2) {
        return n1 + n2;
    }
}

class ChildAdder extends ParentAdder {
    @Override
    public double add(double n1, double n2) {
        System.out.println("adding...");
        return n1 + n2;
    }
}

public class P360_OverrideMistake {
    public static void main(String[] args) {
        ParentAdder adder = new ChildAdder();
        System.out.println(adder.add(3, 4));
//        System.out.println(adder.add(3.1, 4.1));
    }
}