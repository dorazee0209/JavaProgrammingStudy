import java.util.function.ToIntBiFunction;

public class P702_YesObjectMethodRef {
    public static void main(String[] args) {
        IBox ib1 = new IBox(5);
        IBox ib2 = new IBox(7);

        ToIntBiFunction<IBox, IBox> tibf = IBox::larger;

        int bigNum = tibf.applyAsInt(ib1, ib2);
        System.out.println(bigNum);
    }
}
// ToIntBiFunction<T, U> int applyAsInt(T t, U u)

class IBox {
    private int n;
    public IBox(int i) {
        n = i;
    }
    public int larger(IBox b) {
        if(n > b.n)
            return n;
        else
            return b.n;
    }
}