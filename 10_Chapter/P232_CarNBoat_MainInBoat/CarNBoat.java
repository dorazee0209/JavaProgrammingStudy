class Car {
    void myCar() {
        System.out.println("This is my car.");
    }
}

class Boat {
    void myBoat() {
        System.out.println("This is my boat.");
    }

    public static void main(String[] args) {
        Car c = new Car();
        c.myCar();
        Boat b = new Boat();
        b.myBoat();
    }
}