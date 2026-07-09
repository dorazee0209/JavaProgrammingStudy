public class CircleUsing {
    public static void main(String[] args) {
        com.w.smart.Circle c1 = new com.w.smart.Circle(3.5);
        com.f.simple.Circle c2 = new com.f.simple.Circle(3.5);

        System.out.println("Area of r = 3.5 is " + c1.getArea());
        System.out.println("Perimeter of r = 3.5 is " + c2.getPerimeter());
    }
}

