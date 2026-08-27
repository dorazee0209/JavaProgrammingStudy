import java.util.TreeSet;

public class P575_ComparablePerson {
    public static void main(String[] args) {
        TreeSet<Person> tree = new TreeSet<>();
        tree.add(new Person("Yoon", 37));
        tree.add(new Person("Hong", 53));
        tree.add(new Person("Park", 22));

        for(Person e : tree)
            System.out.println(e);
    }
}

class Person implements Comparable<Person> {
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
    public int compareTo(Person person) {
        return this.age - person.age;
    }
}