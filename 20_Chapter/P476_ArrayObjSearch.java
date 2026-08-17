import java.util.Arrays;

class Person implements Comparable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " : " + age;
    }

    @Override
    public int compareTo(Object o) {
        return (this.age - ((Person)o).age);
    }
}

public class P476_ArrayObjSearch {
    public static void main(String[] args) {
        Person[] ar = new Person[] {
            new Person("Lee", 29),
            new Person("Goo", 15),
            new Person("Soo", 35)
        };

        Arrays.sort(ar);
        int idx = Arrays.binarySearch(ar, new Person("Who are you", 35));
        System.out.println(ar[idx]);
    }
}