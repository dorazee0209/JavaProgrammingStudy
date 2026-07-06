import com.w.smart.Circle;

public class ImportCircle {
    static void main(String[] args) {
        Circle c1 = new Circle(3.5);
        Circle c2 = new Circle(5.5);

        System.out.println("Area of r = 3.5 is " + c1.getArea());
        System.out.println("Area of r = 5.5 is " + c2.getArea());
    }
}

