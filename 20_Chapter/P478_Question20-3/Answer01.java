import java.util.Arrays;

/*
 * [문제 20-3] 탐색의 기준 변경
 *
 * 앞서 예제 ArrayObjSearch.java에서 탐색의 기준은 나이였다. 그런데 이 탐색의 기준이 이
 * 름이 되도록 예제를 수정해보자.
 */
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
        return -(this.name.compareTo(((Person)o).name));
        /*
        return (int)this.name.charAt(0) - (int)((Person)o).name.charAt(0);
        */
    }
}

public class Answer01 {
    public static void main(String[] args) {
        Person[] ar = new Person[] {
            new Person("Lee", 15),
            new Person("Goo", 17),
            new Person("Soo", 32)
        };

        Arrays.sort(ar);
        int idx = Arrays.binarySearch(ar, new Person("Soo", 10000));
        System.out.println(ar[idx]);
    }
}
