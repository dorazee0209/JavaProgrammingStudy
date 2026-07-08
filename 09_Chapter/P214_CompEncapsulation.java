class SnivelCap {
    void take() {
        System.out.println("No more snivel");
    }
}

class SneezeCap {
    void take() {
        System.out.println("No more sneeze");
    }
}

class SnuffleCap {
    void take() {
        System.out.println("No more snufle");
    }
}

class SinusCap {
    SnivelCap si_cap = new SnivelCap();
    SneezeCap sz_cap = new SneezeCap();
    SnuffleCap sf_cap = new SnuffleCap();

    void take() {
        si_cap.take();
        sz_cap.take();
        sf_cap.take();
    }
}

class ColdPatient {
    void takeSinus(SinusCap cap) {
        cap.take();
    }
}

public class P214_CompEncapsulation {
    public static void main(String[] args) {
        ColdPatient suf = new ColdPatient();

        suf.takeSinus(new SinusCap());
    }
}