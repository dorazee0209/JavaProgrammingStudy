import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class P601_CarSortCollections {
    public static void main(String[] args) {
        List<Car> cList = new ArrayList<>();
        cList.add(new Car(1200));
        cList.add(new Car(3000));
        cList.add(new Car(1800));
        Collections.sort(cList);

        for(Iterator<Car> e = cList.iterator(); e.hasNext(); )
            System.out.println(e.next());
        System.out.println();
    }
}

class Car implements Comparable<Car> {
    private int disp;

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