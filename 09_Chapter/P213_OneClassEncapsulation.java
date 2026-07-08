class SinusCap {
    void sniTake() {
        System.out.println("No more snivel");
    }

    void sneTake() {
        System.out.println("No more sneeze");
    }

    void snuTake() {
        System.out.println("No more snufle");
    }

    void take() {
        sniTake();
        sneTake();
        snuTake();
    }
}

class ColdPatient {
    void takeSinus(SinusCap cap) {
        cap.take();
    }
}

public class P213_OneClassEncapsulation {
    static void main() {
        ColdPatient suf = new ColdPatient();
        suf.takeSinus(new SinusCap());
    }
}

