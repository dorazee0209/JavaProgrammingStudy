import java.util.Arrays;

class INum {
    private int num;
    public INum(int num) {
        this.num = num;
    }
}

public class P467_ArrayObjEquals {
    public static void main(String[] args) {
        INum[] ar1 = new INum[3];
        INum[] ar2 = new INum[3];

        for (int i = 0; i < 3; i++) {
            ar1[i] = new INum(i+1);
            ar2[i] = new INum(i+1);
        }

        System.out.println(Arrays.equals(ar1, ar2));
    }
}

