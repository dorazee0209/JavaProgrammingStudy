import java.util.ArrayDeque;
import java.util.Deque;

public class P588_DefinedStack {
    public static void main(String[] args) {
        DIStack<String> stk = new DCStack<>(new ArrayDeque<>());

        // PUSH 연산
        stk.push("1. Box");
        stk.push("2. Toy");
        stk.push("3. Robot");

        // POP 연산
        System.out.println(stk.pop());
        System.out.println(stk.pop());
        System.out.println(stk.pop());
    }
}

interface DIStack<E> {
    public boolean push(E item);
    public E pop();
}

class DCStack<E> implements DIStack<E> {
    private Deque<E> deq;

    public DCStack(Deque<E> deq) {
        this.deq = deq;
    }

    public boolean push(E item) {
        return deq.offerFirst(item);
    }

    public E pop() {
        return deq.pollFirst();
    }
}