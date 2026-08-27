# Chapter 24 — 컬렉션 프레임워크 2

본 Chapter의 내용을 무리 없이 이해하기 위해서는 제네릭 관련 내용을 잘 이해하고 있어야 한다. 따라서 본 Chapter를 진행하기에 앞서 제네릭을 한 번 더 복습하는 것도 좋을 듯하다. 참고로 제목과 달리 실제 공부하는 내용은 제네릭에 더 가깝다.

## 24-1. 컬렉션 기반 알고리즘

`Collections` 클래스에는 다양한 알고리즘을 구현한 메소드들이 존재한다. 따라서 이 중 일부를 소개하고자 한다.

### 정렬 — 예제 `P599_SortCollections`

`List<E>`를 구현한 컬렉션 클래스들은 저장된 인스턴스를 정렬된 상태로 유지하지 않는다. 대신에 정렬을 해야 한다면 다음 메소드를 사용할 수 있다.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
```

위의 메소드는 `Collections` 클래스에 정의되어 있는 제네릭 메소드이다. 처음 보면 복잡해 보이지만 이어서 보이는 분석의 과정을 거치면 쉽게 이해할 수 있다. 그럼 먼저 다음과 같이 위의 메소드를 줄여 놓고 시작하자.

```java
public static <T> void sort(List<T> list)
    → 메소드 호출 시점에 T가 결정되므로 List<T>의 인스턴스는 모두 전달 가능
```

그리고 위의 내용에 다음 내용을 추가한다.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
    → 그런데 그 T는 Comparable<T> 인터페이스를 구현한 상태이어야 한다.
```

마지막으로 이렇게 두 단계를 거쳐서 이해한 내용을 다음과 같이 하나로 정리하자.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
    → 인자로 List<T>의 인스턴스는 모두 전달 가능
    → 단, T는 Comparable<T> 인터페이스를 구현한 상태이어야 한다.
```

이렇게 이해하고 나면 다음과 같이 `sort` 메소드의 호출이 가능함을 쉽게 이해할 수 있다.

```java
public static void main(String[] args) {
    List<String> list = ....;
    Collections.sort(list);    // List<T>의 인스턴스가 인자로 전달
    ....
}
```

`String`은 다음과 같이 `Comparable<String>`을 구현한다. 따라서 위에서 보이듯이 `List<String>` 인스턴스는 `sort` 메소드의 인자로 전달이 될 수 있다.

```java
public final class String extends Object implements Comparable<String>
```

그럼 `sort` 메소드의 호출 결과를 다음 예제를 통해서 확인해보자.

```java
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

class SortCollections {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Weapon");
        list = new ArrayList<>(list);

        // 정렬 이전 출력
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();

        // 정렬
        Collections.sort(list);

        // 정렬 이후 출력
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot    Weapon
Box    Robot    Toy    Weapon
```

`String` 클래스의 `compareTo` 메소드는 사전 편찬 순으로(lexicographically) 정렬되도록 구현되어 있다. 따라서 위의 실행 결과에서는 사전 편찬 순을 기준으로 오름차순 정렬된 결과를 확인할 수 있다.

### `<T extends Comparable<T>>` 아니고 `<T extends Comparable<? super T>>`

위에서 다음과 같이 `Collections` 클래스의 `sort` 메소드를 소개하였다.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
    → 인자로 List<T>의 인스턴스는 모두 전달 가능
    → 단, T는 Comparable<T> 인터페이스를 구현한 상태이어야 한다.
```

그런데 이 메소드의 실제 모습은 다음과 같다. 처음부터 이렇게 생겼음을 보였다면 부담을 크게 느낄 수 있어서 한 단계 줄여서 메소드를 소개하였다.

```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

지금껏 본서에서는 이 위치에 `<? super T>`를 넣은 사례를 소개하지 않았다. 그리고 이는 매개변수 선언에 등장하는 `<? super T>`와 문법적인 해석은 같지만 목적에서 차이가 있다. 따라서 이에 대한 설명을 하려 하는데, 간단히 설명할 수 있는 내용이 아니기에 조금 길게 설명을 하려 한다.

#### 예제 `P601_CarSortCollections`

일단 `sort` 메소드가 다음과 같다고 가정하고 아래 예제를 분석하자.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
```

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

class Car implements Comparable<Car> {
    private int disp;    // 배기량

    public Car(int d) { disp = d; }

    @Override
    public String toString() {
        return "cc: " + disp;
    }
    @Override
    public int compareTo(Car o) {
        return disp - o.disp;
    }
}

class CarSortCollections {
    public static void main(String[] args) {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1200));
        list.add(new Car(3000));
        list.add(new Car(1800));
        Collections.sort(list);    // 정렬

        for(Iterator<Car> itr = list.iterator(); itr.hasNext(); )    // 출력
            System.out.println(itr.next().toString() + '\t');
    }
}
```

실행 결과

```
cc: 1200
cc: 1800
cc: 3000
```

여전히 `sort` 메소드가 다음과 같이 정의되어 있다고 가정하고 이야기를 이어 나가겠다.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
```

그러면 예제에서 `List<Car>` 인스턴스를 인자로 전달하며 `sort` 메소드를 호출할 때, T는 `Car`로 결정되어 다음 형태의 메소드 호출이 진행된다.

```java
public static void sort(List<Car> list)
```

단 `Car`는 다음 조건을 만족해야 하는데, 예제에서 정의한 `Car`는 이 조건을 만족한다. 따라서 위 예제는 정상적으로 컴파일 및 실행을 완료할 수 있다.

```java
Car는 Comparable<Car>를 구현해야 한다.
```

#### `ECar`를 정의하면 무슨 일이 벌어지는가

그런데 다음과 같이 `Car`를 상속하는 `ECar`를 정의했다고 가정해보자. (`ECar`는 전기 자동차를 표현한 클래스이다.)

```java
class Car implements Comparable<Car> {...}
class ECar extends Car {...}    // ECar는 Comparable<Car>를 간접 구현
```

그러면 `ECar`는 `Comparable<Car>`을 구현하는(간접 구현하는) 상태가 되는데, 이를 대상으로 다음과 같은 코드를 작성하면 컴파일이 되겠는가?

```java
public static void main(String[] args) {
    List<ECar> list = new ArrayList<>();
    ....
    Collections.sort(list);    // 이 메소드 호출이 성공할 수 있을까?
    ....
}
```

위와 같이 `sort` 메소드를 호출하면 'T는 `ECar`로 결정되어' 다음 형태의 `sort` 메소드 호출이 진행된다.

```java
public static void sort(List<ECar> list)
```

그리고 `sort` 메소드가 다음과 같다고 가정하였으니, `ECar`는 `Comparable<ECar>`를 구현하고 있어야 위의 `sort` 메소드 호출에 문제가 없다.

```java
public static <T extends Comparable<T>> void sort(List<T> list)
    → T가 ECar인 경우 ECar는 Comparable<ECar>를 구현해야 함
```

그러나 클래스의 구현 및 상속의 구조가 다음과 같으므로 `ECar`는 `Comparable<Car>`는 구현하는 상태이지만 `Comparable<ECar>`는 구현하지 않는 상태이다.

```java
class Car implements Comparable<Car> {...}
class ECar extends Car {...}    // Comparable<Car>를 간접 구현한다.
```

따라서 위에서 보인 `sort` 메소드의 호출은 성공하지 못한다. 그러나 `Collections` 클래스의 `sort` 메소드는 이러한 상황을 고려하여 다음과 같이 정의되어 있다.

```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
    → T가 ECar인 경우 ECar는 Comparable<? super ECar>를 구현해야 함
```

따라서 `List<ECar>` 인스턴스를 전달하면서 `sort` 메소드를 호출하는 순간 T는 `ECar`가 되어 위의 메소드는 다음 형태로 호출이 되고,

```java
public static void sort(List<ECar> list)
```

메소드의 선언에서 T가 구현해야 할 인터페이스를 `Comparable<? super T>`로 명시했으므로 `ECar` 클래스는 다음 인터페이스 중 하나만 구현해도 위의 `sort` 메소드 호출은 성공한다.

```java
Comparable<Object>, Comparable<Car>, Comparable<ECar>
```

조금 어렵지만 중요한 설명을 진행했으니, 이 설명의 흐름과 내용을 완전히 이해하고 외우는 수준에 이르기를 바란다.

#### 예제 `P603_ECarSortCollections`

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

class Car implements Comparable<Car> {
    protected int disp;    // 배기량

    public Car(int d) { disp = d; }

    @Override
    public String toString() {
        return "cc: " + disp;
    }
    @Override
    public int compareTo(Car o) {
        return disp - o.disp;
    }
}

class ECar extends Car {    // 전기 자동차를 표현한 클래스
    private int battery;    // 배터리

    public ECar(int d, int b) {
        super(d);
        battery = b;
    }

    @Override
    public String toString() {
        return "cc: " + disp + ", ba: " + battery;
    }
}

class ECarSortCollections {
    public static void main(String[] args) {
        List<ECar> list = new ArrayList<>();
        list.add(new ECar(1200, 99));
        list.add(new ECar(3000, 55));
        list.add(new ECar(1800, 87));
        Collections.sort(list);    // 정렬

        for(Iterator<ECar> itr = list.iterator(); itr.hasNext(); )    // 출력
            System.out.println(itr.next().toString() + '\t');
    }
}
```

실행 결과

```
cc: 1200, ba: 99
cc: 1800, ba: 87
cc: 3000, ba: 55
```

이제 이후로 다음과 같은 유형의 메소드 선언을 본다면,

```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

그리고 위 메소드에 대한 다음 질문의 답을 타인에게 혹은 본인 스스로에게 해야 한다면,

> "`Comparable<T>`가 아닌 `Comparable<? super T>`인 이유는?"

필자가 언급한 다음 클래스 구조를 바탕으로 설명을 하고 이해를 하자.

```java
class Car implements Comparable<Car> {...}
class ECar extends Car {...}
```

> 💡 **개발 팁 — 와일드카드 한 글자가 API의 수명을 바꾼다**
> `Comparable<T>`와 `Comparable<? super T>`의 차이는 타이핑 8글자지만, "이 라이브러리를 쓰는 사람이 상속 계층을 만들어도 되는가"를 가르는 결정적 차이다. 만약 자바 설계자들이 `<T extends Comparable<T>>`로 못 박아 뒀다면, 정렬 가능한 클래스를 상속한 모든 하위 클래스가 `compareTo`를 다시 구현해야 했을 것이다. API를 설계할 때 "지금 당장 동작하는 가장 좁은 타입"이 아니라 "호출자가 미래에 하고 싶어 할 일까지 허용하는 타입"을 고르는 습관 — 그게 라이브러리와 그냥 코드의 차이다.

### 정렬: `Comparator<T>` 기반 — 예제 `P606_CarComparator`

`Collections` 클래스에는 다음 `sort` 메소드도 정의되어 있다. 이는 호출 시 정렬의 기준을 결정할 수 있는 형태로 정의된 메소드이다.

```java
public static <T> void sort(List<T> list, Comparator<? super T> c)
```

그리고 이번에는 매개변수 선언에 `<? super T>`가 있으므로 다음과 같이 판단할 수 있다. (이는 제네릭을 설명하면서 강조한 내용이다.)

> "매개변수 c를 대상으로는 T형 인스턴스를 넣는(전달하는) 메소드 호출만 OK"

실제로 위 메소드의 두 번째 인자로 전달되는 컬렉션 인스턴스를 통해서는 인스턴스를 전달하는 행위만 하는 것이 정상이다. 그런데 이 매개변수 선언의 의미에는 앞서 다음 클래스 구조를 기반으로 설명한 내용도 함께 포함된다. (이후로는 이를 `<? super T>` 선언이 주는 두 번째 의미라 하자.)

```java
class Car implements Comparable<Car> {...}
class ECar extends Car {...}
```

그럼 다음 예제를 통해서 `<? super T>` 선언이 주는 두 번째 의미를 보이도록 하겠다.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;

class Car {
    protected int disp;
    public Car(int d) { disp = d; }

    @Override
    public String toString() { return "cc: " + disp; }
}

// Car의 정렬을 위한 클래스
class CarComp implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) { return o1.disp - o2.disp; }
}

class ECar extends Car {
    private int battery;

    public ECar(int d, int b) {
        super(d);
        battery = b;
    }

    @Override
    public String toString() { return "cc: " + disp + ", ba: " + battery; }
}

class CarComparator {
    public static void main(String[] args) {
        List<Car> clist = new ArrayList<>();
        clist.add(new Car(1800));
        clist.add(new Car(1200));
        clist.add(new Car(3000));

        List<ECar> elist = new ArrayList<>();
        elist.add(new ECar(3000, 55));
        elist.add(new ECar(1800, 87));
        elist.add(new ECar(1200, 99));

        CarComp comp = new CarComp();

        // 각각 정렬
        Collections.sort(clist, comp);
        Collections.sort(elist, comp);    // 이 문장이 이 예제의 핵심!

        for(Iterator<Car> itr = clist.iterator(); itr.hasNext(); )
            System.out.println(itr.next().toString() + '\t');
        System.out.println();

        for(Iterator<ECar> itr = elist.iterator(); itr.hasNext(); )
            System.out.println(itr.next().toString() + '\t');
    }
}
```

실행 결과

```
cc: 1200
cc: 1800
cc: 3000

cc: 1200, ba: 99
cc: 1800, ba: 87
cc: 3000, ba: 55
```

예제에서는 `Car`의 정렬을 위해 정의한 다음 클래스의 인스턴스를 대상으로 `ECar`도 정렬할 수 있음을 보였다.

```java
class CarComp implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) { return o1.disp - o2.disp; }
}
```

즉 다음 문장이 실행될 수 있음을 보였다.

```java
Collections.sort(elist, comp);
```

이는 `sort` 메소드의 두 번째 매개변수 타입이 `Comparator<T>`가 아닌 `Comparator<? super T>`이기에 가능한 일이다.

> 💡 **개발 팁 — `Comparable`은 "타고난 순서", `Comparator`는 "그때그때의 기준"**
> 같은 정렬인데 왜 인터페이스가 둘일까? `Comparable`은 클래스 자신이 자기 순서를 아는 것이라 한 클래스당 딱 하나뿐이고(natural ordering), `Comparator`는 정렬 기준을 클래스 바깥으로 빼낸 것이라 얼마든지 여러 개를 만들어 상황에 따라 갈아 끼울 수 있다. 이렇게 "알고리즘의 한 부분(비교 규칙)을 객체로 분리해서 주입하는" 구조를 디자인 패턴에서는 **전략 패턴(Strategy Pattern)** 이라 부르며, `Comparator`는 자바 표준 라이브러리에 박혀 있는 가장 유명한 사례다. 실무에서 "정렬 기준이 하나 더 늘어날 것 같다" 싶으면 `compareTo`를 고치지 말고 `Comparator`를 하나 더 만드는 쪽이 옳다.

### 찾기 — 예제 `P609_StringBinarySearch`

리스트 자료구조를 기반으로 특정 인스턴스를 찾을 때 사용할 수 있는 메소드가 `Collections` 클래스에 다음과 같이 정의되어 있다.

```java
public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
    → list에서 key를 찾아 그 인덱스 값 반환, 못 찾으면 음의 정수 반환
```

마지막으로 한 번 더 위의 매개변수 선언이 의미하는 바를 천천히 풀어서 설명을 하겠다. 먼저 위의 메소드를 다음과 같이 단순화하자. 골치 아픈 부분을 통째로 지우고 시작하자.

```java
public static <T> int binarySearch(List<?> list, T key)
```

그리고 위의 메소드 정의를 보면서 다음 내용을 파악한다.

> "첫 번째 인자로 `List<E>` 인스턴스는 무엇이든 올 수 있다."

이어서 골치 아픈 부분을 붙이되 조금 단순화해서 붙이자.

```java
public static <T> int binarySearch(List<? extends Comparable<T>> list, T key)
```

그리고 단순화해서 붙인 부분에 대한 해석을 다음과 같이 포함시킨다.

> "첫 번째 인자로 `List<E>` 인스턴스는 무엇이든 올 수 있다."
> "단, 이때 E는 `Comparable<T>`를 구현해야 한다."

여기까지 이해가 되었다면 마지막으로 메소드를 원래 모습으로 되돌리고,

```java
public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
```

이때 `Comparable<T>`를 대신해서 `Comparable<? super T>`이 온 이유를 앞서 소개한 다음 클래스의 관계를 통해서 이해하고 끝낸다.

```java
class Car implements Comparable<Car> {...}
class ECar extends Car {...}
```

하나의 매개변수 선언에 포함된 내용이 너무 많기 때문에 이렇듯 나누어서 그 의미를 이해해야 한다.

> **참고 — 해석의 과정도 중요합니다**
> 본 Chapter에서 다음 메소드들의 와일드카드와 제네릭 선언이 갖는 의미를 단계를 나누어 설명하였다.
>
> ```java
> public static <T extends Comparable<? super T>> void sort(List<T> list)
> public static <T> void sort(List<T> list, Comparator<? super T> c)
> public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
> ```
>
> 이 정도 수준의 선언을 이해하고 있다는 것은 자바의 제네릭을 누구보다 잘 이해하고 있다는 뜻이며, 이 정도 수준의 선언을 이해하고 있다면 제네릭은 더 이상 걸림돌이 되지 않을 것이다.

그럼 이어서 `binarySearch` 메소드의 기능과 활용에 대한 설명을 이어가겠다. 이 메소드는 이진 탐색이라는 알고리즘을 기반으로 탐색을 진행한다. 그런데 이 알고리즘을 적용하기 위해서는 해당 컬렉션 인스턴스가 정렬된 상태이어야 한다. 이진 탐색은 정렬된 리스트 자료구조를 대상으로 적용하는 알고리즘이기 때문이다. 따라서 다음 예제에서 보이듯이 `binarySearch`의 호출에 앞서 정렬의 과정이 선행되어야 한다.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class StringBinarySearch {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Box");
        list.add("Robot");
        list.add("Apple");

        Collections.sort(list);    // 정렬
        int idx = Collections.binarySearch(list, "Robot");    // 탐색
        System.out.println(list.get(idx));    // 탐색의 결과 출력
    }
}
```

실행 결과

```
Robot
```

`List<String>` 인스턴스는 정렬된 상태를 유지하지 않으므로 위와 같이 정렬을 먼저 진행해야 한다. 만약에 정렬되지 않은 상태에서 `binarySearch` 메소드를 호출하면 정상적인 결과를 얻지 못한다. 혹 정상적인 결과를 얻는다 해도 이는 우연의 일치일 뿐이다.

> 💡 **개발 팁 — 컴파일러가 못 잡아주는 계약: 사전조건(precondition)**
> "정렬돼 있어야 한다"는 `binarySearch`의 요구는 타입 시그니처 어디에도 안 적혀 있다. 어겨도 컴파일 에러가 안 나고, 예외도 안 던져지고, 그냥 **조용히 틀린 답**을 준다 — 심지어 운 좋게 맞는 경우도 있어서 테스트를 통과해버리기도 한다. 실무에서 가장 잡기 어려운 버그가 정확히 이런 종류다. 그래서 이런 암묵적 요구사항은 반드시 문서(Javadoc)로 남기고, 중요한 코드라면 `assert`나 방어 코드로 명시적으로 검사한다. 반대로 내가 메소드를 만들 때도 마찬가지 — "호출자가 이걸 지켜줘야 한다"는 게 있다면 그건 주석에 적어야 할 1순위 정보다.

### 찾기: `Comparator<T>` 기반 — 예제 `P611_StringComparator`

`Collections` 클래스에는 `Comparator<T>`를 구현하는 클래스를 정의하여 탐색의 기준을 마련할 수 있는 다음 메소드도 존재한다.

```java
public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
    → list에서 key를 찾는데 c의 기준을 적용하여 찾는다.
```

이 메소드도 한눈에 들어오지 않는다면 다음과 같이 줄여 놓고 이해하자.

```java
public static <T> int binarySearch(List<T> list, T key, Comparator<T> c)
```

그리고 나서 다음과 같이 `<? extends T>`와 `<? super T>`의 의미를 덧붙이자.

> "`List<T>` 아니고 `List<? extends T>`인 이유는 list에서 T형 인스턴스를 꺼내는 것만 허용하기 위해"
> "`Comparator<T>` 아니고 `Comparator<? super T>`인 이유는 `ECar` 클래스를 통해 설명한 그것"

그러면 다음 메소드 전체가 눈에 들어온다.

```java
public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
```

그럼 다음 예제를 통해서 위 메소드의 사용의 예를 보이겠다. 이 예제에서는 동일 문자열을 찾을 때 대소 구분 없이 찾도록 `Comparator<T>`를 구현하였다.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class StrComp implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);    // 대문자, 소문자 구분 없이 비교
    }
}

class StringComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ROBOT");
        list.add("APPLE");
        list.add("BOX");

        StrComp cmp = new StrComp();    // 정렬과 탐색의 기준
        Collections.sort(list, cmp);    // 정렬
        int idx = Collections.binarySearch(list, "Robot", cmp);    // 탐색
        System.out.println(list.get(idx));    // 탐색 결과 출력
    }
}
```

실행 결과

```
ROBOT
```

위 예제에서 호출한 `String` 클래스의 다음 메소드는 문자열을 비교하되 대문자와 소문자를 구분하지 않고 비교를 진행한다.

```java
public int compareToIgnoreCase(String str)
    → 두 문자열이 같을 때 0을 반환한다.
```

그리고 실제로 대문자와 소문자의 구분 없이 탐색이 진행되었음을 실행 결과를 통해서 확인할 수 있다.

### 복사하기 — 예제 `P612_CopyList`

다음은 리스트 구조의 컬렉션 인스턴스에 저장된 내용을 복사하는 기능의 메소드이다. 물론 이 메소드도 `Collections` 클래스에 정의되어 있다.

```java
public static <T> void copy(List<? super T> dest, List<? extends T> src)
    → src의 내용을 dest로 복사
```

위 메소드의 매개변수 선언이 갖는 의미는 다음과 같다. (여러 차례 설명한 내용이니 이번에는 이 정도로 마무리하겠다.)

```java
List<T> dest 아닌 List<? super T> dest 인 이유는?
    → dest에 T형 인스턴스를 넣는 것만 허용하겠다. 꺼내면 컴파일 에러!

List<T> src 아닌 List<? extends T> src 인 이유는?
    → src로부터 T형 인스턴스 꺼내는 것만 허용하겠다. 넣으면 컴파일 에러!
```

위 메소드 호출 시 한가지 주의할 점은 매개변수 `dest`에 전달되는 컬렉션 인스턴스의 저장 공간이 `src`에 전달되는 컬렉션 인스턴스의 저장 공간보다 크거나 최소한 같아야 한다는 점이다. 만약에 `dest`로 전달된 인스턴스의 저장 공간이 작다면 복사의 과정에서 공간이 자동으로 늘지 않고 예외가 발생한다. 그럼 다음 예제를 통해 `copy` 메소드의 사용의 예를 보이겠다.

```java
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class CopyList {
    public static void main(String[] args) {
        List<String> src = Arrays.asList("Box", "Apple", "Toy", "Robot");

        // 복사본을 만든다.
        List<String> dest = new ArrayList<>(src);

        // 정렬하여 그 결과를 출력
        Collections.sort(dest);
        System.out.println(dest);

        // dest에 저장된 내용을 src에 저장된 내용으로 덮어씀
        Collections.copy(dest, src);

        // 되돌림 확인
        System.out.println(dest);    // 컬렉션 인스턴스에 저장된 내용 전부 출력
    }
}
```

실행 결과

```
[Apple, Box, Robot, Toy]
[Box, Apple, Toy, Robot]
```

위 예제에서 보이듯이 컬렉션 인스턴스를 생성하지 않은 상태에서 복사본을 만들려면 다음 방법을 사용하면 된다.

```java
List<String> dest = new ArrayList<>(src);
```

즉 `copy` 메소드는 위의 문장을 대신하지 않는다. 그러나 이미 생성된 컬렉션 인스턴스의 내용을 통째로 바꾸려는 경우에 `copy` 메소드는 유용하게 사용된다. 위 예제에서 보이듯이 말이다.
그리고 지금까지는 저장된 데이터의 순차적 접근 방법을 보이느라 다음과 같이 출력할 수 있음을 보이지 않았는데, 이와 같이 `System.out.println` 메소드 호출을 통해서 컬렉션 인스턴스에 저장된 데이터의 내용 전부를 출력할 수도 있다.

```java
System.out.println(dest);    // 컬렉션 인스턴스에 저장된 내용 전부 출력
```

> 💡 **개발 팁 — PECS: Producer Extends, Consumer Super**
> `copy(List<? super T> dest, List<? extends T> src)` 한 줄이 이 Chapter 전체의 결론이다. 데이터를 **꺼내 주는 쪽**(producer)인 `src`에는 `extends`가, 데이터를 **받아 넣는 쪽**(consumer)인 `dest`에는 `super`가 붙었다. 이 규칙을 자바 커뮤니티에서는 앞글자를 따 **PECS**(Producer Extends, Consumer Super)라 부르며, 제네릭 API를 설계할 때 와일드카드 방향을 고르는 표준 지침으로 쓰인다. 앞으로 낯선 제네릭 시그니처를 만나면 "이 매개변수는 값을 주는 쪽인가, 받는 쪽인가"를 먼저 물어보자 — 대부분 그 자리에서 `extends`/`super`의 이유가 풀린다.
