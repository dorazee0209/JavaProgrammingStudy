import java.util.Arrays;

/*
 * [문제 20-2] 정렬의 기준 수정하기
 *
 * • 문제 1
 * 앞서 제시한 예제 ArrayObjSort.java에서는 Person의 인스턴스들을 나이순으로 정렬하였
 * 는데, 이를 수정하여 나이의 역순으로 정렬이 되도록 해보자. 다시 말해서, 많은 나이의 인스턴
 * 스일수록 배열의 앞쪽에 위치하도록 예제를 수정해보자.
 *
 * • 문제 2
 * 앞서 제시한 예제 ArrayObjSort.java에서는 Person의 인스턴스들을 나이순으로 정렬하였
 * 는데, 이를 이름의 길이 순으로 정렬이 되도록 수정해보자. 즉 이름이 길이가 짧은 인스턴스일수
 * 록 배열의 앞쪽에 위치하도록 예제를 수정해야 한다.
 */
class Person implements Comparable {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " : " + age;
    }

    @Override
    public int compareTo(Object obj) {
        if(this.age < ((Person)obj).age)
            return 1;
        else if(this.age > ((Person)obj).age)
            return -1;
        else
            return 0;
    }
}

public class Answer01 {
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
