import java.util.ArrayDeque;
import java.util.Deque;

public class P587_ArrayDequeCollection {
    public static void main(String[] args) {
        Deque<String> deq = new ArrayDeque<>();

        // input First
        deq.offerFirst("1. Box");
        deq.offerFirst("2. Toy");
        deq.offerFirst("3. Robot");

        // polling from First
        System.out.println(deq.poll());
        System.out.println(deq.poll());
        System.out.println(deq.poll());
    }
}