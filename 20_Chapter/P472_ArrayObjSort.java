import java.util.Arrays;

class Person implements Comparable {
    String name;
    int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }

    @Override
    public int compareTo(Object o) {
        if(this.age > ((Person)o).age)
            return 1;
        else if(this.age < ((Person)o).age)
            return -1;
        else
            return -1;
    }

}

public class P472_ArrayObjSort {
    public static void main(String[] args) {
        Person[] ar ={
            new Person("Lee", 17),
            new Person("Goo", 35),
            new Person("Soo", 5)
        };
        System.out.println("Before sorting: ");
        for (Person p : ar) {
            System.out.println(p);
        }

        Arrays.sort(ar);
        System.out.println();

        System.out.println("After sorting: ");
        for (Person p : ar) {
            System.out.println(p);
        }
    }
}

