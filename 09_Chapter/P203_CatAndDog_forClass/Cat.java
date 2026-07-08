package zoo;

// default: in same package
class Duck {
    // empty class
}

// public: anywhere
public class Cat {
    public void makeCat() {
        // Duck과 같은 패키지로 묶여 있으니 Duck instance 생성 가능
        Duck quack = new Duck();
    }
}

