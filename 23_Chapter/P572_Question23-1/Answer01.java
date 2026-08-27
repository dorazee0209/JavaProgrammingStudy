/*
 * [문제 23-1] [hashCode & equals 오버라이딩]
 *
 * • 문제 1
 * 다음 클래스의 인스턴스가 HashSet<Person> 컬렉션 인스턴스에 저장될 때, 이름과 나이가
 * 같으면 동일 인스턴스로 판단이 되도록 hashCode와 equals 메소드를 오버라이딩 해보자.
 *
 *     class Person {
 *         private String name;
 *         private int age;
 *         public Person(String name, int age) {
 *             this.name = name;
 *             this.age = age;
 *         }
 *         public String toString() {
 *             return name + "(" + age + "세)";
 *         }
 *     }
 */

import java.util.HashSet;
import java.util.Iterator;

public class Answer01 {
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
        return name.hashCode() + age;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.name.equals(((Person)obj).name) && this.age == ((Person)obj).age)
            return true;
        else
            return false;
    }
}
