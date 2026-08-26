/*
 * [문제 23-1] [hashCode & equals 오버라이딩]
 *
 * • 문제 2
 * 위의 문제를 해결 과정에서 Objects.hash 메소드를 사용하지 않았다면, 이 메소드를 호출하
 * 는 방식으로 문제를 한 번 더 해결해보자.
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Answer02 {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<>();
        set.add(new Person("Kim", 22));
        set.add(new Person("Kim", 32));
        set.add(new Person("Kim", 42));
        set.add(new Person("Kim", 22));
        set.add(new Person("Lim", 22));
        System.out.println("# of set: " + set.size());

        for(Iterator<Person> i = set.iterator(); i.hasNext(); )
            System.out.println(i.next().toString() + "\t");
    }
}

class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "(" + age + " years old)";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.name.equals(((Person)obj).name) && this.age == ((Person)obj).age)
            return true;
        else
            return false;
    }
}
