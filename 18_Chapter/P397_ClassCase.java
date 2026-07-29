class Board {}
class PBorad extends Board {}

public class P397_ClassCase {
    public static void main(String[] args) {
        Board p1 = new PBorad();
        PBorad p2 = (PBorad)p1; // Ok!

        System.out.println(".. Intermediate Location .. ");
        Board p3 = new Board();
        PBorad p4 = (PBorad)p3; // Exception!
    }
}