import java.util.HashSet;

public class P570_HowHashCode {
    public static void main(String[] args) {
        HashSet<Car> set = new HashSet<>();
        set.add(new Car("HY_MD_301", "RED"));
        set.add(new Car("HY_MD_301", "BLACK"));
        set.add(new Car("HY_MD_302", "RED"));
        set.add(new Car("HY_MD_302", "WHITE"));
        set.add(new Car("HY_MD_301", "BLACK"));
        System.out.println("인스턴스 수: " + set.size());

        for(Car car : set) {
            System.out.println(car.toString());
        }
    }
}

class Car {
    private String model;
    private String color;

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.model + " : " + this.color;
    }

    @Override
    public int hashCode() {
        return (model.hashCode() + color.hashCode()) / 2;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.model.equals(((Car)obj).model) && this.color.equals(((Car)obj).color))
            return true;
        else
            return false;
    }
}