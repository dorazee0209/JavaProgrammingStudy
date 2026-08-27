import java.util.*;

public class P606_CarComparator {
    public static void main(String[] args) {
        List<Car> cList = new ArrayList<>();
        cList.add(new Car(1800));
        cList.add(new Car(1200));
        cList.add(new Car(3000));

        List<ECar> ecList = new ArrayList<>();
        ecList.add(new ECar(3000, 55));
        ecList.add(new ECar(1800, 37));
        ecList.add(new ECar(1200, 99));

        CarComparator comp = new CarComparator();

        // 각각 정렬
        Collections.sort(cList, comp);
        Collections.sort(ecList, comp);

        for(Iterator<Car> e = cList.iterator(); e.hasNext(); )
            System.out.println(e.next() + "\t");
        System.out.println();

        for(Iterator<ECar> e = ecList.iterator(); e.hasNext(); )
            System.out.println(e.next() + "\t");
        System.out.println();
    }
}

class Car {
    protected int disp;

    public Car(int disp) {
        this.disp = disp;
    }

    @Override
    public String toString() {
        return "cc: " + this.disp;
    }
}

// Car의 정렬을 윈한 클래스
class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car car2) {
        return car1.disp - car2.disp;
    }
}

class ECar extends Car {
    private int battery;

    public ECar(int disp, int battery) {
        super(disp);
        this.battery = battery;
    }

    @Override
    public String toString() {
        return super.toString() + ", batt: " + this.battery;
    }
}