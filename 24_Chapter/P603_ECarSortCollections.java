import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class P603_ECarSortCollections {
    public static void main(String[] args) {
        List<ECar> lst = new ArrayList<>();
        lst.add(new ECar(1200, 99));
        lst.add(new ECar(3000, 55));
        lst.add(new ECar(1800, 87));
        Collections.sort(lst);

        for(Iterator<ECar> e = lst.iterator(); e.hasNext(); )
            System.out.println(e.next());
        System.out.println();
    }
}

class Car implements Comparable<Car> {
    private int disp; // 배기량

    public Car(int disp) {
        this.disp = disp;
    }

    @Override
    public String toString() {
        return "cc: " + this.disp;
    }

    @Override
    public int compareTo(Car car) {
        return this.disp - car.disp;
    }
}

class ECar extends Car {
    private int batt; // 배터리

    public ECar(int disp, int batt) {
        super(disp);
        this.batt = batt;
    }

    @Override
    public String toString() {
        return super.toString() + ", ba: " + this.batt;
    }
}