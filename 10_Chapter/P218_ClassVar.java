class InstCnt {
    static int instNum;

    InstCnt() {
        instNum++;
        System.out.println("Mk instance: " + instNum);
    }
}

public class P218_ClassVar {
    public static void main(String[] args) {
        InstCnt cnt1 = new InstCnt();
        InstCnt cnt2 = new InstCnt();
        InstCnt cnt3 = new InstCnt();
    }
}