class SnivelCap {
    void take() {
        System.out.println("No snivel anymore");
    }
}

class SneezeCap {
    void take() {
        System.out.println("No sneeze anymore");
    }
}

class SnuffleCap {
    void take() {
        System.out.println("No snuffle anymore");
    }
}

class ColdPatient {
    void takeSnivelCap(SnivelCap cap) {
        cap.take();
    }

    void takeSneezeCap(SneezeCap cap) {
        cap.take();
    }

    void takeSnuffleCap(SnuffleCap cap) {
        cap.take();
    }
}

public class P211_BadEncapsulation {
    public static void main(String[] args) {
        ColdPatient suf = new ColdPatient();

        suf.takeSnivelCap(new SnivelCap());
        suf.takeSneezeCap(new SneezeCap());
        suf.takeSnuffleCap(new SnuffleCap());
    }
}

// 한계점: 코감기 증상 완화를 위해 세 인스턴스를 생성해야 함을 개발자가 인지하고 있어야 한다.