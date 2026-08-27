import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class P578_ComparatorPerson {
    public static void main(String[] args) {
        TreeSet<Person> tree = new TreeSet<>(new PersonComparator());
        tree.add(new Person("Yoon", 37));
        tree.add(new Person("Hong", 53));
        tree.add(new Person("Park", 22));

        for(Iterator<Person> e = tree.iterator(); e.hasNext(); ) {
            System.out.println(e.next());
        }
    }
}

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter for age
    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.age;
    }

    @Override
    public int compareTo(Person p) {
        return this.age - p.age;
    }
}

class PersonComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p2.getAge() - p1.getAge();
    }
}