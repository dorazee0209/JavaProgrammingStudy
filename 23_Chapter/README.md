# Chapter 23 — 컬렉션 프레임워크 1

제네릭을 공부하는 이유 중 하나가 컬렉션 프레임워크를 활용하기 위한 것이라고 해도 과언이 아닐 정도로 이 Chapter에서 설명하는 내용은 제네릭과 관련이 깊다. 컬렉션 프레임워크를 공부하면서 제네릭에 대한 부족한 이해를 완성하면, 이후에 접하게 될 '람다'와 '스트림'에 대한 부담도 줄일 수 있다.

## 23-1. 컬렉션 프레임워크의 이해

컬렉션 프레임워크의 활용은 생각보다 어렵지 않다. 사실 좋은 프레임워크일수록 제공하는 기능 대비 활용 방법이 간단해야 한다. 그런 측면에서 자바의 컬렉션 프레임워크는 좋은 평가를 받을 만하다.

### '프레임워크'라는 표현의 이해

프레임워크(Framework)라는 표현은 여러 분야에서 상이한 개념으로 사용되기 때문에, 이에 대한 정확한 의미 파악이 쉽지 않을 수 있다. 하지만 기본적으로 다음의 의미를 공통적으로 지닌다.

> "잘 정의된 구조 또는 골격"

따라서 자바에서 말하는 프레임워크는 다음과 같이 이해할 수 있다.

> "잘 정의된 구조의 클래스들"

즉 프레임워크는 프로그래머들이 쓸 수 있도록 잘 정의된 클래스들의 모임이라 할 수 있다. 그런데 이것이 전부라면 이는 '라이브러리'라 불리게 된다. 하지만 '컬렉션 라이브러리'라 하지 않고 '컬렉션 프레임워크'라 한다. 이는 컬렉션 관련된 클래스의 정의에 적용되는 설계 원칙 또는 구조가 존재하기 때문이다.

### 컬렉션의 의미와 자료구조

컴퓨터 분야에는 '자료구조(Data Structures)'와 '알고리즘(Algorithms)'이라는 학문이 있다. 이 중 자료구조는 '데이터의 저장' 관련 학문으로 데이터의 탐색, 삭제 등 다양한 측면을 고려한 데이터의 효율적인 저장 방법을 연구하는 학문이다. 반면 알고리즘은 저장된 데이터의 일부 또는 전체를 대상으로 하는 각종 가공 및 처리의 방법을 연구하는 학문이다. 따라서 이 둘은 서로 다른 학문임에도 불구하고 긴밀히 연관되어 있다. 자료구조에서 정형화하고 있는 데이터의 저장 방식 중 대표적인 몇 가지를 정리하면 다음과 같다.

> 리스트(List), 스택(Stack), 큐(Queue), 트리(Tree), 해쉬(Hash)

그리고 위 자료구조들을 대상으로 하는 비교적 간단한 알고리즘 몇 가지를 소개하면 다음과 같다.

> 버블 정렬(Bubble Sort), 퀵 정렬(Quick Sort), 이진 탐색(Binary Search)

그렇다면 컬렉션 프레임워크는 무엇에 대한 프레임워크일까? 이는 데이터의 저장 방법, 그리고 이와 관련 있는 알고리즘에 대한 프레임워크이다. 더 쉽게 표현하면, 위에서 언급한 자료구조와 알고리즘을 제네릭 기반의 클래스와 메소드로 미리 구현해 놓은 결과물이다. 따라서 컬렉션 프레임워크를 이용하면 자료구조를 몰라도 트리 기반으로 데이터를 저장할 수 있고, 알고리즘을 몰라도 이진 탐색을 수행할 수 있다.

### 컬렉션 프레임워크의 기본 골격

컬렉션 프레임워크를 공부한다고 생각하면 부담이 될 수 있으니, 데이터의 저장과 관련된 클래스를 공부한다고 생각하자. 그리고 다음 그림에서 보이는 인터페이스의 상속 관계를 관찰하자.

```
              Collection<E>
                    ▲
       ┌────────────┼────────────┐
    Set<E>        List<E>      Queue<E>

              Map<K, V>   (Collection<E>과는 별개)
```

**[그림 23-1: 컬렉션 프레임워크의 인터페이스 구조]**

위 그림은 지금부터 소개할 '컬렉션 클래스'들이 구현하는 '인터페이스들의 상속 관계'를 보여준다. 그림에서 `<E>` 그리고 `<K, V>`는 모든 인터페이스가 제네릭으로 정의되었음을 의미한다. 그리고 인스턴스를 저장하는 컬렉션 클래스들은 위의 인터페이스 중 하나를 구현하게 되어 있으며, 구현한 인터페이스에 따라서 컬렉션 클래스의 데이터 저장 방식이 결정된다. 따라서 구현한 인터페이스의 종류를 확인하는 일은 매우 중요하다.

> 💡 **개발 팁 — "인터페이스 기반 설계"의 실전 사례**
> 지금 본 상속 구조(`Set`/`List`/`Queue`가 모두 `Collection`을 상속)가 바로 "프레임워크 vs 라이브러리"를 가르는 그 '구조'다. 실무에서 변수·매개변수 타입을 구현체(`ArrayList`)가 아니라 인터페이스(`List`)로 선언하는 관례("program to an interface, not an implementation")가 여기서 나온다 — `List<String> list = new ArrayList<>();`처럼 선언해두면, 나중에 `ArrayList`를 `LinkedList`로 바꿔도 그 변수를 쓰는 코드는 한 글자도 안 고쳐도 된다. 컬렉션 프레임워크 전체가 이 원칙 위에 설계돼 있다는 걸, 뒤에서 `ArrayList`/`LinkedList`를 배우면서 계속 체감하게 될 것이다.

## 23-2. `List<E>` 인터페이스를 구현하는 컬렉션 클래스들

지금부터 소개하는 '컬렉션 클래스'들을 기반으로 생성되는 '컬렉션 인스턴스'들은 인스턴스의 저장을 목적으로 한다. 그리고 컬렉션 관련 클래스들과 인터페이스들은 java.util 패키지로 대부분 묶여 있다.

### `ArrayList<E>` 클래스 — 예제 `P547_ArrayListCollection`

`List<E>` 인터페이스를 구현하는 대표적인 컬렉션 클래스 둘은 다음과 같다.

- `ArrayList<E>` — 배열 기반 자료구조, 배열을 이용하여 인스턴스 저장
- `LinkedList<E>` — 리스트 기반 자료구조, 리스트를 구성하여 인스턴스 저장

이 둘은 기능적 측면에서 보면 완전히 동일하다. 그러나 인스턴스를 저장하는 방식에 차이가 있어 이로 인한 장단점이 각각 존재한다. 그리고 `List<E>` 인터페이스를 구현하는 컬렉션 클래스들이 갖는 공통적인 특성 두 가지가 있는데 이는 다음과 같다.

- 인스턴스의 저장 순서를 유지한다.
- 동일한 인스턴스의 중복 저장을 허용한다.

```java
import java.util.List;
import java.util.ArrayList;

class ArrayListCollection {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();    // 컬렉션 인스턴스 생성

        // 컬렉션 인스턴스에 문자열 인스턴스 저장
        list.add("Toy");
        list.add("Box");
        list.add("Robot");

        // 저장된 문자열 인스턴스의 참조
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + '\t');
        System.out.println();

        list.remove(0);    // 첫 번째 인스턴스 삭제

        // 첫 번째 인스턴스 삭제 후 나머지 인스턴스들을 참조
        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot
Box    Robot
```

위 예제의 import문은 다음과 같다. 이렇듯 제네릭 클래스라 하더라도 import문 구성시에는 클래스의 이름만 명시해야 한다.

```java
import java.util.List;
import java.util.ArrayList;
```

그리고 예제에서 `ArrayList<E>` 인스턴스의 생성문은 다음과 같다.

```java
List<String> list = new ArrayList<>();
```

이 문장에서 `ArrayList<E>`형 참조변수가 아닌 `List<E>`형 참조변수를 선언한 이유는 코드에 유연성을 제공하기 위함이다. 주로 `List<E>`에 선언된 메소드를 호출하기 때문에 굳이 `ArrayList<E>`형 참조변수를 선언할 필요가 없으며, 이렇듯 `List<E>`형 참조변수로 인스턴스를 참조할 경우 다음과 같이 컬렉션 클래스의 교체가 용이해진다.

```java
List<String> list = new ArrayList<>();
    → List<String> list = new LinkedList<>();
```

그리고 예제에서 보이는 인스턴스의 저장 방법은 다음과 같다.

```java
list.add("Toy");    // 인스턴스의 저장
```

이렇듯 `add` 메소드의 인자를 통해 저장할 인스턴스를 전달하면 된다. 물론 실제 저장되는 것은 인스턴스의 참조 값이다. 이어서 보이는, 인스턴스의 순차적 참조 방식은 다음과 같다.

```java
for(int i = 0; i < list.size(); i++)
    System.out.print(list.get(i) + '\t');
```

메소드 `size`의 호출을 통해서, 저장된 인스턴스의 수를 알 수 있으며, `get` 메소드에 인덱스 값을 전달함으로써 원하는 위치의 인스턴스를 참조할 수 있다. 0을 전달하면 첫 번째 인스턴스의 참조 값이 반환되는데, 첫 번째 인스턴스는 제일 먼저 저장된 인스턴스이다.
그리고 마지막으로 인덱스 값을 인자로 하여 다음과 같이 인스턴스를 삭제할 수 있다. 아래 문장에서는 0을 전달하였으므로 첫 번째로 저장된 인스턴스가 삭제된다. (이 문장을 두 번 실행하면 그때는 두 번째로 저장된 인스턴스까지 삭제된다.)

```java
list.remove(0);    // 맨 앞에 위치한(첫 번째로 저장된) 인스턴스 삭제
```

지금까지 인스턴스의 저장, 참조, 삭제의 방법을 설명했는데, 실제로 컬렉션 프레임워크의 핵심은 이 세 가지이다. 그리고 예제에서 보였듯이 컬렉션 인스턴스를 사용하면 배열처럼 길이를 신경 쓰지 않아도 된다. `ArrayList<E>` 인스턴스는 내부적으로 배열을 생성해서 인스턴스를 저장하는데, 필요하면 그 배열의 길이를 스스로 늘리기 때문이다. 단 배열의 길이를 늘린다는 것은 더 긴 배열로의 교체를 의미한다. (한번 생성된 배열은 길이를 늘릴 수 없으므로) 따라서 성능에 신경을 써야 한다면 `ArrayList<E>`의 다음 생성자 정도는 알아 둘 필요가 있다.

```java
public ArrayList(int initialCapacity)
    → 인자로 전달된 수의 인스턴스를 저장할 수 있는 공간을 미리 확보
```

저장해야 할 인스턴스의 수가 대략 계산이 된다면 위의 생성자를 통해서 적당한 길이의 배열을 미리 만들어 두는 것이 성능 향상에 도움이 된다. 참고로 앞서 예제에서 호출한 생성자는 다음과 같다.

```java
public ArrayList()
    → 10개의 인스턴스를 저장할 수 있는 공간을 미리 확보
```

그리고 `List<E>` 인터페이스를 구현한 컬렉션 클래스들은 '저장 순서를 유지한다.'고 했는데, 위 예제에서 그것을 보여주고 있다. 저장 순서대로 출력이 이뤄진 부분이 바로 그것이다.

### `LinkedList<E>` 클래스 — 예제 `P549_LinkedListCollection`

이어서 `LinkedList<E>` 인스턴스의 사용 예를 보일 텐데, 이 예제와 앞서 소개한 예제와의 차이점은 다음 문장의 변화가 전부이다.

```java
List<String> list = new ArrayList<>();
    → List<String> list = new LinkedList<>();
```

```java
import java.util.List;
import java.util.LinkedList;

class LinkedListCollection {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();    // 유일한 변화
        list.add("Toy");
        list.add("Box");
        list.add("Robot");

        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + '\t');
        System.out.println();

        list.remove(0);

        for(int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot
Box    Robot
```

`LinkedList<E>`는 '연결 리스트(Linked List)'라는 자료구조를 기반으로 디자인된 클래스이다. 그런데 연결 리스트라는 것은 '칸칸이 연결된 화물 열차'를 생각하면 된다. 따라서 인스턴스의 저장 및 삭제는 다음과 같은 방식으로 진행된다.

- 인스턴스 저장 — 열차 칸 하나 추가로 연결하고, 그 열차 칸에 인스턴스를 저장한다.
- 인스턴스 삭제 — 해당 인스턴스를 저장하고 있는 열차 칸을 삭제한다.

이렇듯 저장 공간을 열차 칸 추가하듯이 늘릴 수 있기 때문에 `ArrayList<E>`와 달리 인스턴스의 저장 공간을 미리 마련해 둘 필요가 없다.

### `ArrayList<E>` vs. `LinkedList<E>`

위에서 설명한 내용을 근거로 `ArrayList<E>`의 단점을 먼저 언급하면 다음과 같다.

- `ArrayList<E>`의 단점
    - 저장 공간을 늘리는 과정에서 시간이 비교적 많이 소요된다.
    - 인스턴스의 삭제 과정에서 많은 연산이 필요할 수 있다. 따라서 느릴 수 있다.

배열 중간에 위치한 인스턴스를 삭제할 경우, 삭제된 위치를 비워 두지 않기 위해서 그 뒤에 저장되어 있는 인스턴스들을 한 칸씩 앞으로 이동하는 과정을 진행하게 된다. (배열은 중간을 비워 두지 않는 것이 좋으며, `ArrayList<E>` 역시 배열의 중간을 비워 두지 않는다.) 때문에 삭제 과정에서 많은 연산이 필요할 수 있다. 물론 배열이 갖는 장점도 있다. 즉 `ArrayList<E>`가 갖는 장점도 있으며 이는 다음과 같다.

- `ArrayList<E>`의 장점
    - 저장된 인스턴스의 참조가 빠르다.

배열에 저장된 요소에 접근할 땐 인덱스 값을 통해 원하는 위치에 바로 접근할 수 있다. 따라서 어느 위치에 있는 인스턴스이건 접근에 소요되는 시간은 동일하다. 반면 `LinkedList<E>`는 이러한 접근이 불가능하다. 즉 다음의 단점을 지닌다.

- `LinkedList<E>`의 단점
    - 저장된 인스턴스의 참조 과정이 배열에 비해 복잡하다. 따라서 느릴 수 있다.

연결 리스트라는 자료구조는 중간에 위치한 열차 칸에 바로 접근이 안된다. 열차 중간 칸에 저장된 인스턴스를 참조하려면 열차 맨 앞 칸, 또는 맨 뒤 칸에서부터 한 칸씩 건너가야 하는 구조이다. 따라서 인스턴스의 참조 속도가 느릴 수밖에 없다. 반면 다음과 같은 장점을 지닌다.

- `LinkedList<E>`의 장점
    - 저장 공간을 늘리는 과정이 간단하다.
    - 저장된 인스턴스의 삭제 과정이 단순하다.

화물 열차의 중간 칸을 없앨 때에는 해당 칸을 빼고서 그 칸의 앞과 뒤를 연결하면 된다. 그리고 실제 연결 리스트의 삭제 과정은 이와 동일하다. 때문에 많은 연산이 필요하지 않다.
이렇게 해서 `List<E>` 인터페이스를 구현하는 대표적인 클래스 `ArrayList<E>`, `LinkedList<E>` 각각의 장점과 단점을 설명하였는데, 이러한 특성이 두 클래스 중 하나를 선택하는 기준이 된다.

> 💡 **개발 팁 — 삽입/삭제 O(1) vs 탐색 O(1), 동시에 가질 수 없다**
> `ArrayList`와 `LinkedList`의 트레이드오프는 자료구조 전반에서 반복되는 패턴이다: 배열 기반 구조는 인덱스 접근이 O(1)이지만 중간 삽입/삭제가 O(N)이고, 연결 기반 구조는 그 반대다. 그래서 "무조건 좋은 자료구조"는 없고, **어떤 연산을 더 자주 하느냐**로 골라야 한다 — 인덱스로 자주 조회한다면 `ArrayList`, 중간 삽입/삭제가 잦다면 `LinkedList`. 실무에서 자료구조를 고를 때 가장 먼저 던져야 할 질문이 "이 데이터에 대해 어떤 연산이 제일 빈번한가"인 이유가 여기 있다.

### 저장된 인스턴스의 순차적 접근 방법 1: enhanced for문의 사용

컬렉션 클래스를 활용하는데 있어서 보편적이고 중요한 작업 중 하나는 다음과 같다.

> "저장된 모든 인스턴스들에 순차적으로 접근"

예를 들어서 특정 인스턴스를 찾아야 할 때, 저장된 인스턴스 전부를 대상으로 탐색을 진행해야 한다. 쉽게 말해서 하나씩 꺼내 보아야 한다. 이 상황에서 물론 for문을 이용할 수 있다. 그러나 보다 나은 방법을 컬렉션 프레임워크에서 제공하고 있는데, 그중 하나는 다음 예제에서 보이듯이 우리에게 익숙한 for-each문(enhanced for문)을 사용하는 것이다.

```java
import java.util.List;
import java.util.LinkedList;

class EnhancedForCollection {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();

        // 인스턴스 저장
        list.add("Toy");
        list.add("Box");
        list.add("Robot");

        // 전체 인스턴스 참조
        for(String s : list)
            System.out.print(s + '\t');
        System.out.println();

        list.remove(0);    // 첫 번째 인스턴스 삭제

        // 전체 인스턴스 참조
        for(String s : list)
            System.out.print(s + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot
Box    Robot
```

저장된 모든 인스턴스들을 대상으로 하는 연산이 필요한 경우, 다음과 같이 for-each문을 사용할 수 있다.

```java
for(String s : list)
    System.out.print(s + '\t');
```

위의 문장은 배열을 대상으로 하는 for-each문과 사실상 차이가 없다. 반복의 대상만 다를 뿐이다. 단, 위와 같이 for-each문을 통한 순차적 접근의 대상이 되려면, 해당 컬렉션 클래스는 다음 인터페이스를 구현해야 한다.

```java
public interface Iterable<T>
```

그런데 앞서 소개한 `ArrayList<E>`, `LinkedList<E>` 클래스는 위의 인터페이스를 구현하고 있다. 정확히는 다음과 같이 `Collection<E>`가 `Iterable<T>`를 상속하는데, `ArrayList<E>`, `LinkedList<E>` 클래스는 `Collection<E>` 인터페이스를 구현하고 있다.

```java
public interface Collection<E> extends Iterable<E>
```

이렇듯 `Iterable<T>`를 직접 혹은 간접적으로 구현하는 클래스의 인스턴스를 대상으로 for-each문을 구성할 수 있다.

### 저장된 인스턴스의 순차적 접근 방법 2

앞서 `Collection<E>`가 `Iterable<T>`를 상속한다고 하였다. 따라서 `Collection<E>`를 구현하는 자바의 제네릭 클래스는 `Iterable<T>`의 다음 추상 메소드를 모두 구현한다.

```java
Iterator<T> iterator()
```

이 메소드는 '반복자(Iterator)'라는 것을 반환한다. 반복자는 저장된 인스턴스들을 순차적으로 참조할 때 사용하는 인스턴스로, 일종의 '지팡이'에 비유할 수 있다. 그리고 이 지팡이를 얻는 방법은 다음과 같다. (물론 이 지팡이의 역할은 저장된 인스턴스들을 가리키는 것이다.)

```java
public static void main(String[] args) {
    List<String> list = new LinkedList<>();
    ....
    Iterator<String> itr = list.iterator();    // 반복자 획득, itr이 지팡이를 참조한다.
    ....
}
```

위에서 얻은 지팡이를(반복자를) 통해 호출할 수 있는, `Iterator<E>`의 메소드들은 다음과 같다.

| 메소드 | 설명 |
|---|---|
| `E next()` | 다음 인스턴스의 참조 값을 반환 |
| `boolean hasNext()` | next 메소드 호출 시 참조 값 반환 가능 여부 확인 |
| `void remove()` | next 메소드 호출을 통해 반환했던 인스턴스 삭제 |

반복자는 next를 호출할 때마다 첫 번째 인스턴스를 시작으로 다음 인스턴스의 참조 값을 차례로 반환한다. 그리고 더 이상 반환할 대상이 없을 때 `NoSuchElementException` 예외를 발생시킨다. 따라서 저장된 인스턴스에 차례로 접근할 때에는 다음과 같은 반복문을 구성해야 한다.

```java
// 반복자를 이용한 순차적 참조
while(itr.hasNext()) {    // next 메소드가 반환할 대상이 있다면,
    str = itr.next();    // next 메소드를 호출한다.
    ....
}
```

`hasNext`는 반환할 대상이 있는지 미리 확인하는 메소드이다. 즉 이 메소드는 반환할 인스턴스가 있으면 true, 그렇지 않으면 false를 반환한다. 따라서 위와 같이 next 호출 이전에 hasNext를 호출하여 next 호출의 성공 가능성을 미리 확인해야 한다. 그리고 앞서 소개한 for-each문을 통한 순차적 접근과 달리 반복자를 이용하면 반복 중간에 특정 인스턴스를 삭제하는 것이 가능하다. (이는 for-each문을 통해서는 불가능한 일이다.) 그 예로 다음 코드를 실행하면, 저장된 문자열 중 "Box"를 모두 지울 수 있다.

```java
// 반복자를 이용한 참조 과정 중 인스턴스의 삭제
while(itr.hasNext()) {
    str = itr.next();
    if(str.equals("Box"))
        itr.remove();    // 위에서 next 메소드가 반환한 인스턴스 삭제
}
```

이러한 반복자는 생성과 동시에 첫 번째 인스턴스를 가리키고, next가 호출될 때마다 가리키는 대상이 다음 인스턴스로 옮겨진다. 그렇다면 이 반복자를 원하는 때에 다시 첫 번째 인스턴스를 가리키게 하려면 어떻게 해야 할까? 가리키던 위치를 되돌리는 방법은 없으니 다음과 같이 반복자를 다시 얻어야 한다.

```java
Iterator<String> itr = list.iterator();
```

그럼 다음 예제를 통해서 지금까지 설명한 내용을 정리해보겠다.

```java
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

class IteratorCollection {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("Toy");
        list.add("Box");
        list.add("Robot");
        list.add("Box");

        Iterator<String> itr = list.iterator();    // 반복자 처음 획득

        // 반복자를 이용한 순차적 참조
        while(itr.hasNext())
            System.out.print(itr.next() + '\t');
        System.out.println();

        itr = list.iterator();    // 반복자 다시 획득

        // 모든 "Box" 삭제
        String str;
        while(itr.hasNext()) {
            str = itr.next();
            if(str.equals("Box"))
                itr.remove();
        }

        itr = list.iterator();    // 반복자 다시 획득

        // 삭제 후 결과 확인
        while(itr.hasNext())
            System.out.print(itr.next() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot    Box
Toy    Robot
```

참고로 한 가지만 더 언급하면, 앞서 소개한 다음과 같은 for-each문도,

```java
for(String s : list)
    System.out.print(s + '\t');
```

컴파일 과정에서 다음과 같이 반복자를 이용하는 코드로 수정된다. 즉 for-each문 역시 반복자에 의한 순차적 접근으로 진행이 된다.

```java
for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
    System.out.print(itr.next() + '\t');
```

### 배열보다는 컬렉션 인스턴스가 좋다: 컬렉션 변환

배열과 `ArrayList<E>`는 특성이 유사하다. (`ArrayList<E>`가 배열을 기반으로 인스턴스를 저장하므로) 그런데 대부분의 경우 배열보다 `ArrayList<E>`가 더 좋다. 첫 번째 이유로 인스턴스의 저장과 삭제가 편하다. 그리고 두 번째 이유로 '반복자'를 쓸 수 있다. 단 배열처럼 '선언과 동시에 초기화'를 할 수 없어서 초기에 무엇인가를 채워 넣는 일이 조금 번거롭다. 하지만 다음과 같이 컬렉션 인스턴스를 생성할 수 있어서 이것도 문제가 되지 않는다.

```java
List<String> list = Arrays.asList("Toy", "Robot", "Box");
    → 인자로 전달된 인스턴스들을 저장한 컬렉션 인스턴스의 생성 및 반환
```

그런데 이렇게 생성된 컬렉션 인스턴스는 새로운 인스턴스의 추가나 삭제가 불가능하다. 물론 반복자의 생성은 가능하나 이를 통해서도 참조만 가능할 뿐이다. 따라서 새로운 인스턴스의 추가나 삭제가 필요한 상황이라면 다음 생성자를 기반으로 `ArrayList<E>` 인스턴스를 생성해야 한다.

```java
class ArrayList<E> {
    public ArrayList(Collection<? extends E> c) {...}    // 생성자
    ....
}
```

이 생성자의 매개변수 선언에 `<? extends E>`가 등장한다. (Chapter 22에서 힘들게 공부한 보람을 여기서 처음 찾는다.) 처음 등장한 것이니 함께 이 의미를 해석해보겠다. 먼저 매개변수 선언을 다음과 같이 줄여 놓고 그 의미를 판단하자.

```java
public ArrayList(Collection<E> c)
    → Collection<E>를 구현한 컬렉션 인스턴스를 인자로 전달받는다.
    → 그리고 E는 인스턴스 생성 과정에서 결정되므로 무엇이든 될 수 있다.
```

사실 여기까지는 어렵지 않다. 제네릭의 기본에 해당하기 때문이다. 따라서 이렇게 이해한 후에 다음 내용을 덧붙이자.

```java
public ArrayList(Collection<? extends E> c)
    → 덧붙여서 매개변수 c로 전달된 컬렉션 인스턴스에서는 참조만(꺼내기만) 가능하다.
```

결국 위의 두 내용을 정리하면 다음과 같다.

```java
public ArrayList(Collection<? extends E> c)
    → Collection<E>를 구현한 컬렉션 인스턴스를 인자로 전달받는다.
    → 그리고 E는 인스턴스 생성 과정에서 결정되므로 무엇이든 될 수 있다.
    → 덧붙여서 매개변수 c로 전달된 컬렉션 인스턴스에서는 참조만(꺼내기만) 가능하다.
```

지금 보인 이 두 단계를 거치면 `<? extends E>`의 의미를 언제든지 쉽게 이해할 수 있다. 그럼 다시 본론으로 돌아와서 이 생성자의 사용의 예를 보이겠다.

```java
public static void main(String[] args) {
    // List<E>는 Collection<E>를 상속한다.
    List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");

    // 생성자 public ArrayList(Collection<? extends E> c)를 통한 인스턴스 생성
    list = new ArrayList<>(list);
    ....
}
```

위와 같이 `ArrayList<E>` 인스턴스를 생성하면, 생성자로 전달된 컬렉션 인스턴스에 저장된 모든 데이터가, 새로 생성되는 `ArrayList<E>` 인스턴스에 복사된다. 따라서 위와 같은 코드의 구성은 배열을 대신하는 컬렉션 인스턴스의 생성에 주로 사용된다. 그럼 지금까지 설명한 내용을 다음 예제를 통해 확인해보자.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

class AsListCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        list = new ArrayList<>(list);

        // for문 기반의 반복자 획득과 순차적 참조
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();

        // "Box"를 모두 삭제하기 위한 반복문
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); ) {
            if(itr.next().equals("Box"))
                itr.remove();
        }

        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot    Box
Toy    Robot
```

참고로 대다수 컬렉션 클래스들은 다른 컬렉션 인스턴스를 인자로 전달받는 생성자를 가지고 있어서, 다른 컬렉션 인스턴스에 저장된 데이터를 복사해서 새로운 컬렉션 인스턴스를 생성할 수 있다.

```java
public ArrayList(Collection<? extends E> c)     // ArrayList<E> 생성자 중 하나
    → 인자로 전달된 컬렉션 인스턴스로부터 ArrayList<E> 인스턴스 생성

public LinkedList(Collection<? extends E> c)     // LinkedList<E> 생성자 중 하나
    → 인자로 전달된 인스턴스로부터 LinkedList<E> 인스턴스 생성

public HashSet(Collection<? extends E> c)     // HashSet<E> 생성자 중 하나
    → 인자로 전달된 인스턴스로부터 HashSet<E> 인스턴스 생성
```

따라서 `ArrayList<E>` 인스턴스를 사용하다가 연결 리스트 자료구조의 특성이 필요하면 다음 예제에서 보이는 바와 같이 이를 기반으로 `LinkedList<E>` 인스턴스를 생성하면 된다.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Arrays;

class ConversionCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        list = new ArrayList<>(list);

        // ArrayList<E> 인스턴스의 순환
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();

        // ArrayList<E> 인스턴스 기반으로 LinkedList<E> 인스턴스 생성
        list = new LinkedList<>(list);

        // LinkedList<E> 인스턴스의 순환
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot    Box
Toy    Box    Robot    Box
```

> 💡 **개발 팁 — `Arrays.asList`는 "고정 크기 리스트"라는 함정**
> `Arrays.asList(...)`가 반환하는 리스트는 겉보기엔 평범한 `List`지만 내부적으로 원본 배열을 감싸고 있을 뿐이라, `add`/`remove`를 호출하면 `UnsupportedOperationException`이 던져진다. 실무에서 자주 나오는 실수가 "리스트를 받았으니 당연히 수정 가능하겠지"라고 가정하고 바로 `remove`를 호출했다가 런타임에 이 예외를 만나는 것이다. 지금 배운 것처럼 `new ArrayList<>(Arrays.asList(...))`로 한 번 감싸서 "진짜 수정 가능한" 컬렉션으로 변환하는 습관을 들이면 이런 함정을 피할 수 있다.

### 기본 자료형 데이터의 저장과 참조

컬렉션 인스턴스도 기본 자료형의 값은 저장하지 못한다. 그러나 래퍼 클래스의 도움으로 이들 값의 저장 및 참조가 가능하며, 이 과정에서 오토 박싱과 오토 언박싱으로 인해 자연스러운 코드의 구성이 가능하다. 다음 예제에서 보여주듯이 말이다.

```java
import java.util.Iterator;
import java.util.LinkedList;

class PrimitiveCollection {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10); list.add(20); list.add(30);    // 저장 과정에서 오토 박싱 진행

        int n;
        for(Iterator<Integer> itr = list.iterator(); itr.hasNext(); ) {
            n = itr.next();    // 오토 언박싱 진행
            System.out.print(n + "\t");
        }
        System.out.println();
    }
}
```

실행 결과

```
10    20    30
```

### 연결 리스트만 갖는 양방향 반복자

`Collection<E>`를 구현하는 클래스의 인스턴스는 `iterator` 메소드의 호출을 통해서 '반복자'를 얻을 수 있다. 그런데 `List<E>`를 구현하는 클래스의 인스턴스들만 얻을 수 있는 '양방향 반복자'라는 것이 있는데, 이는 `List<E>`의 다음 메소드 호출을 통해서 얻을 수 있다.

```java
public ListIterator<E> listIterator()
    → ListIterator<E>는 Iterator<E>을 상속한다.
```

위의 메소드가 반환하는 반복자는 양쪽 방향으로 이동이 가능하다는 특징이 있는데, 이는 배열이나 연결 리스트와 같은 자료구조의 특성상 가능한 일이다. 그리고 위 메소드가 반환하는 반복자를 대상으로 호출할 수 있는 대표 메소드들은 다음과 같다.

| 메소드 | 설명 |
|---|---|
| `E next()` | 다음 인스턴스의 참조 값을 반환 |
| `boolean hasNext()` | next 메소드 호출 시 참조 값 반환 가능 여부 확인 |
| `void remove()` | next 메소드 호출을 통해 반환했던 인스턴스를 삭제 |
| `E previous()` | next 메소드와 기능은 같고 방향만 반대 |
| `boolean hasPrevious()` | hasNext 메소드와 기능은 같고 방향만 반대 |
| `void add(E e)` | 인스턴스의 추가 |
| `void set(E e)` | 인스턴스의 변경 |

`ListIterator<E>`는 `Iterator<E>`를 상속하기 때문에 next, hasNext, remove는 이미 설명한 그 메소드들과 같다. 그럼 다음 예제를 통해서 양방향 반복자의 사용의 예를 보이겠다. 이 예제에서는 왼쪽에서 오른쪽으로, 다시 오른쪽에서 왼쪽으로 이동하면서 중간에 add 메소드를 호출하여 인스턴스를 추가로 저장한다.

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Arrays;

class ListIteratorCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        list = new ArrayList<>(list);

        ListIterator<String> litr = list.listIterator();    // 양방향 반복자 획득

        String str;
        while(litr.hasNext()) {    // 왼쪽에서 오른쪽으로 이동을 위한 반복문
            str = litr.next();
            System.out.print(str + '\t');
            if(str.equals("Toy"))    // "Toy" 만나면 "Toy2" 저장
                litr.add("Toy2");
        }
        System.out.println();

        while(litr.hasPrevious()) {    // 오른쪽에서 왼쪽으로 이동을 위한 반복문
            str = litr.previous();
            System.out.print(str + '\t');
            if(str.equals("Robot"))    // "Robot" 만나면 "Robot2" 저장
                litr.add("Robot2");
        }
        System.out.println();

        // 다시 왼쪽에서 오른쪽으로
        for(Iterator<String> itr = list.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Toy    Box    Robot    Box
Box    Robot    Robot2    Box    Toy2    Toy
Toy    Toy2    Box    Robot2    Robot    Box
```

실행 결과를 통해서 add 메소드가 어느 위치에 인스턴스를 추가하는지 확인할 수 있다. 즉 이를 통해 다음 사실을 알 수 있다.

> "next 호출 후에 add 호출하면, 앞서 반환된 인스턴스 뒤에 새 인스턴스 삽입된다."
> "previous 호출 후에 add 호출하면, 앞서 반환된 인스턴스 앞에 새 인스턴스 삽입된다."

## 23-3. `Set<E>` 인터페이스를 구현하는 컬렉션 클래스들

`List<E>`를 구현하는 컬렉션 클래스들을 접하면서 컬렉션에 대한 큰 그림이 머릿속에 그려졌을 것이다. 따라서 이제부터는 보다 쉽게 다양한 컬렉션 클래스들을 접하고 이해할 수 있다.

### `Set<E>`을 구현하는 클래스의 특성과 `HashSet<E>` 클래스

`Set<E>` 인터페이스를 구현하는 제네릭 클래스의 특성 두 가지를 정리하면 다음과 같다.

- 저장 순서가 유지되지 않는다.
- 데이터의 중복 저장을 허용하지 않는다.

`List<E>`를 구현하는 컬렉션 인스턴스에 저장된 데이터를 반복자를 통해 출력해보면 저장된 순서대로 출력됨을 확인할 수 있다. 그리고 앞서 예제에서 "Box"를 두 번 저장하였는데, 두 번 모두 저장됨을 출력 결과에서 확인할 수 있었다. 하지만 `Set<E>`를 구현하는 클래스는 다르다. 순서도 유지되지 않고 중복도 허용하지 않는다. 그리고 이는 Set이라는 이름처럼 수학에서 말하는 '집합'의 특성이다. 그럼 이와 관련하여 다음 예제를 보자. 이 예제에서는 `Set<E>`를 구현하는 대표 클래스 `HashSet<E>`의 사용 예를 보여준다.

```java
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

class SetCollectionFeature {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Toy");
        set.add("Box");
        set.add("Robot");
        set.add("Box");
        System.out.println("인스턴스 수: " + set.size());

        // 반복자를 이용한 전체 출력
        for(Iterator<String> itr = set.iterator(); itr.hasNext(); )
            System.out.print(itr.next() + '\t');
        System.out.println();

        // for-each문을 이용한 전체 출력
        for(String s : set)
            System.out.print(s + '\t');
        System.out.println();
    }
}
```

실행 결과

```
인스턴스 수: 3
Box    Robot    Toy
Box    Robot    Toy
```

위 예제의 출력 결과를 통해서, 저장 순서가 유지되지 않고 데이터의 중복 저장이 허용되지 않는다는 사실을 알 수 있다. 그런데 동일한 데이터로(인스턴스로) 판단하는 기준은 무엇일까? 다음 예제는 이 질문에 대해서 폭넓은 생각을 하게 한다.

```java
import java.util.HashSet;

class Num {
    private int num;
    public Num(int n) { num = n; }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}

class HashSetEqualityOne {
    public static void main(String[] args) {
        HashSet<Num> set = new HashSet<>();
        set.add(new Num(7799));
        set.add(new Num(9955));
        set.add(new Num(7799));
        System.out.println("인스턴스 수: " + set.size());

        for(Num n : set)
            System.out.print(n.toString() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
인스턴스 수: 3
7799    7799    9955
```

우리 관점에서 보면 다음과 같이 저장한 두 개의 `Num` 인스턴스는 동일한 인스턴스로 생각할 수 있다. 지니고 있는 값이 같으니 말이다.

```java
public static void main(String[] args) {
    HashSet<Num> set = new HashSet<>();
    set.add(new Num(7799));
    set.add(new Num(7799));
    ....
}
```

그러나 실행 결과는 이 둘이 다른 인스턴스로 간주됨을 보이고 있는데, 이는 `HashSet<E>`이 판단하는 동일 인스턴스의 기준은, `Object` 클래스에 정의되어 있는 다음 두 메소드의 호출 결과를 근거로 하기 때문이다.

```java
public boolean equals(Object obj)
public int hashCode()
```

위의 두 메소드가 어떻게 사용이 되는지 이해하기 위해서는 해쉬 알고리즘에 대한 간단한 이해가 필요하다. 따라서 이에 대한 설명을 먼저 가볍게 진행하고자 한다.

### 해쉬 알고리즘과 `hashCode` 메소드

`HashSet<E>` 클래스를 잘 활용하기 위해서는 간단하게나마 해쉬 알고리즘을 이해하고 있어야 한다. 그럼 먼저 다음 코드를 보자.

```
num % 3
```

별것 아니지만 이것도 멋진 해쉬 알고리즘으로 사용될 수 있다. 그럼 위의 알고리즘을 적용하여 얻게 되는 연산 결과에 따라 다음 수들을 분류해보자.

```
3,  5,  7,  12,  25,  31
```

위의 수들이 하나의 집합을 구성한다고 가정할 때, 나머지 연산의 결과 0과 1 그리고 2를 기준으로 다음과 같이 세 부류로 나눌 수 있다.

```
연산결과0: 3, 12
연산결과1: 7, 25, 31
연산결과2: 5
```

**[그림 23-2: 나머지 연산 결과에 따른 분류]**

이렇게 세 개의 부류로 나뉜 상태에서, 정수 5의 존재 여부를 확인하는 가장 효율적인 방법을 생각해보자. 모든 정수들이 3으로 나눈 나머지를 기준으로 나뉘어 있으니, 우선 존재 여부의 확인 대상인 정수 5를 3으로 나머지 연산을 하여, 속하는 부류를 찾는 것이 우선이다.

```
5 % 3 = 2
```

이로써 % 연산의 결과가 0과 1인 부류는 탐색 대상에서 제외되었다. 즉 탐색 대상이 줄어버린 것이다. 그리고 이것이 해쉬 알고리즘을 사용하는 이유이다. 참고로 해쉬 알고리즘은 데이터의 종류 및 성격에 따라서 다양하게 설계되어야 한다. 따라서 위에서 보인 % 연산 하나만으로 해쉬 알고리즘을 다 이해했다고 생각하면 곤란하다. 그러나 이 정도의 이해만으로도 `HashSet<E>`을 활용하기에는 충분하다.
그럼 다시 본론으로 돌아와서, 정수 5의 존재 여부를 확인하는 과정을 정리하면 다음과 같다. 다음과 같이 두 단계를 거쳐서 탐색을 진행하기 때문에 탐색 속도는 빠를 수밖에 없다.

- 탐색 1단계 — 정수 5의 해쉬 값을 계산하여 탐색 부류를 결정
- 탐색 2단계 — 선택된 부류 내에 정수 5가 존재하는지 확인

그리고 위의 두 단계를 거쳐서 동일 인스턴스의 존재 여부를 확인하는 클래스가 `HashSet<E>`이다. 즉 이 클래스의 탐색 과정은 다음과 같다.

- 탐색 1단계 — `Object` 클래스에 정의된 `hashCode` 메소드의 반환 값을 기반으로 부류 결정
- 탐색 2단계 — 선택된 부류 내에서 `equals` 메소드를 호출하여 동등 비교

그럼 이제 앞서 보인 다음 코드에서 7799를 담고 있는 두 인스턴스가 서로 다른 인스턴스로 간주된 이유를 설명하겠다.

```java
public static void main(String[] args) {
    HashSet<Num> set = new HashSet<>();
    set.add(new Num(7799));
    set.add(new Num(7799));
    ....
}
```

`Object` 클래스에 정의되어 있는 `hashCode`와 `equals` 메소드는 다음과 같이 정의되어 있다. (참고로 `Object` 클래스의 `hashCode` 메소드는 인스턴스가 저장된 주솟값을 기반으로 반환 값이 만들어지도록 정의되어 있다.)

> "인스턴스가 다르면 Object 클래스의 hashCode 메소드는 다른 값을 반환한다."
> "인스턴스가 다르면 Object 클래스의 equals 메소드는 false를 반환한다."

즉 `Object` 클래스의 `hashCode`와 `equals`는 저장하고 있는 값을 기준으로 인스턴스의 동등 여부를 따지지 않는다. 그래서 위의 코드에서 7799를 담고 있는 두 인스턴스는 서로 다른 인스턴스로 간주가 되었다. 따라서 값을 기준으로 동등 여부를 따지도록 하려면 다음 예제에서 보이듯이 이 두 메소드를 오버라이딩 해야 한다.

```java
import java.util.HashSet;

class Num {
    private int num;
    public Num(int n) { num = n; }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public int hashCode() {
        return num % 3;    // num의 값이 같으면 부류도 같다.
    }

    @Override
    public boolean equals(Object obj) {    // num의 값이 같으면 true 반환
        if(num == ((Num)obj).num)
            return true;
        else
            return false;
    }
}

class HashSetEqualityTwo {
    public static void main(String[] args) {
        HashSet<Num> set = new HashSet<>();
        set.add(new Num(7799));
        set.add(new Num(9955));
        set.add(new Num(7799));
        System.out.println("인스턴스 수: " + set.size());

        for(Num n : set)
            System.out.print(n.toString() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
인스턴스 수: 2
9955    7799
```

참고로 `String` 클래스는 문자열의 내용 비교가 이뤄지도록 `hashCode`와 `equals`를 적절히 오버라이딩 하고 있다. 따라서 `HashSet<E>` 인스턴스에는 동일한 문자열을 지니는 `String` 인스턴스가 둘 이상 저장되지 않는다.

### `hashCode` 메소드의 다양한 정의

다음과 같이 둘 이상의 값을 지니는 클래스의 경우 내용 비교를 위한 `hashCode`와 `equals` 메소드는 어떻게 정의하는 것이 좋겠는가? 인스턴스가 지니는 모든 값이 동일할 때 동일 인스턴스로 간주하도록 정의하려면 말이다.

```java
class Car {
    private String model;
    private String color;
    public Car(String m, String c) {
        model = m;
        color = c;
    }
    @Override
    public String toString() { return model + " : " + color; }
}
```

이 클래스는 두 개의 참조변수를 가지고 있으니, 다음과 같은 `hashCode` 메소드의 정의를 생각해볼 수 있다.

```java
@Override
public int hashCode() {
    return (model.hashCode() + color.hashCode()) / 2;
}
```

두 참조변수는 `String` 인스턴스를 참조한다. 그런데 `String` 클래스의 `hashCode`와 `equals` 메소드는 내용 비교를 하도록 적절히 오버라이딩이 되어 있다. 따라서 위에서 보이는 방법을 고려해볼 수 있다. 그럼 예제를 통해서 위 메소드의 정의 결과를 확인해보겠다.

```java
import java.util.HashSet;

class Car {
    private String model;
    private String color;

    public Car(String m, String c) {
        model = m;
        color = c;
    }
    @Override
    public String toString() {
        return model + " : " + color;
    }

    @Override
    public int hashCode() {
        return (model.hashCode() + color.hashCode()) / 2;
    }

    @Override
    public boolean equals(Object obj) {
        String m = ((Car)obj).model;
        String c = ((Car)obj).color;

        if(model.equals(m) && color.equals(c))
            return true;
        else
            return false;
    }
}

class HowHashCode {
    public static void main(String[] args) {
        HashSet<Car> set = new HashSet<>();
        set.add(new Car("HY_MD_301", "RED"));
        set.add(new Car("HY_MD_301", "BLACK"));
        set.add(new Car("HY_MD_302", "RED"));
        set.add(new Car("HY_MD_302", "WHITE"));
        set.add(new Car("HY_MD_301", "BLACK"));
        System.out.println("인스턴스 수: " + set.size());

        for(Car car : set)
            System.out.println(car.toString() + '\t');
    }
}
```

실행 결과

```
인스턴스 수: 4
HY_MD_301 : RED
HY_MD_302 : RED
HY_MD_301 : BLACK
HY_MD_302 : WHITE
```

그런데 클래스를 정의할 때마다 이렇듯 `hashCode` 메소드를 정의하는 것은 번거로운 일이다. 특히 해쉬 알고리즘의 성능적 측면까지 고려하면서 모든 클래스를 정의하기란 쉬운 일이 아니다. 그래서 자바에서는 다음 메소드를 제공하고 있다.

```java
public static int hash(Object...values)
    → java.util.Objects에 정의된 메소드, 전달된 인자 기반의 해쉬 값 반환
```

위 메소드의 매개변수 선언에는 '가변 인자 선언'이 포함되어 있는데, 이는 전달되는 인자의 수를 메소드 호출 시마다 달리할 수 있는 선언이다. (가변 인자에 대한 자세한 설명은 Chapter 25에서 이뤄진다.) 그리고 이 `hash` 메소드를 이용하여 위 예제의 `hashCode` 메소드를 다음과 같이 오버라이딩 할 수 있다.

```java
@Override
public int hashCode() {
    return Objects.hash(model, color);    // 전달인자 model, color 기반 해쉬 값 반환
}
```

이렇듯 `hash` 메소드는 하나 이상의 인자를 조합하여 하나의 해쉬 값을 만들어 반환한다. 따라서 특별한 경우가 아니라면 직접 해쉬 알고리즘을 만들지 않고 이 메소드에 의존해도 된다.

> 💡 **개발 팁 — `equals`를 오버라이딩하면 `hashCode`도 반드시 같이**
> "`equals`가 true를 반환하는 두 인스턴스는 `hashCode`도 반드시 같아야 한다"는 건 자바 명세가 요구하는 계약(contract)이다. 이 계약을 어기면(예: `equals`만 오버라이딩하고 `hashCode`는 그대로 두면), `HashSet`/`HashMap`에서 "값은 같은데 다른 인스턴스로 취급되는" 미묘한 버그가 생긴다 — 앞서 본 `Num` 클래스의 `HashSetEqualityOne` 예제가 정확히 그 상황이었다. IDE의 "equals와 hashCode 자동 생성" 기능이 항상 두 메소드를 세트로 만들어주는 이유가 여기 있다.

### `TreeSet<E>` 클래스의 이해와 활용

이어서 `Set<E>` 인터페이스를 구현하는 `TreeSet<E>` 클래스를 소개하고자 한다. `TreeSet<E>` 클래스는 '트리(Tree)'라는 자료구조를 기반으로 인스턴스를 저장한다. 그리고 이는 정렬된 상태가 유지되면서 인스턴스가 저장됨을 의미한다. (트리라는 자료구조의 특성이 그러하다는 뜻이다.) 그렇다면 정렬의 기준은 무엇일까? 이 질문에 대한 힌트를 얻기 위해 다음 예제를 살펴보자.

```java
import java.util.TreeSet;
import java.util.Iterator;

class SortedTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> tree = new TreeSet<Integer>();
        tree.add(3);  tree.add(1);
        tree.add(2);  tree.add(4);
        System.out.println("인스턴스 수: " + tree.size());

        // for-each문에 의한 반복
        for(Integer n : tree)
            System.out.print(n.toString() + '\t');
        System.out.println();

        // Iterator 반복자에 의한 반복
        for(Iterator<Integer> itr = tree.iterator(); itr.hasNext(); )
            System.out.print(itr.next().toString() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
인스턴스 수: 4
1    2    3    4
1    2    3    4
```

`TreeSet<E>` 인스턴스가 정렬 상태를 유지하면서 인스턴스를 저장하기 때문에 `TreeSet<E>`의 반복자는 다음의 특징을 갖는다.

> "인스턴스들의 참조 순서는 오름차순을 기준으로 한다."

그리고 이러한 특징은 위의 실행 결과를 통해서 확인할 수 있다. 그런데 오름차순이란, 순서상 작은 것에서부터 큰 것으로의 나열을 의미한다. 그렇다면 다음 클래스의 인스턴스는 무엇이 작은 것이며 무엇이 큰 것이겠는가?

```java
class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() { return name + " : " + age; }
}
```

수의 경우 일반적으로 통용되는 작은 것과 큰 것에 대한 비교 기준이 있지만, 위 클래스의 경우 기준을 어떻게 정하느냐에 따라서 오름차순으로의 나열 결과는 달라지게 된다. 예를 들어서 크고 작음에 대한 기준을 나이로 둘 수 있다. 또는 이름의 가나다 순이 기준이 될 수도 있다. 즉 크고 작음에 대한 기준은 프로그래머가 결정할 일이다. 그래서 위와 같이 클래스를 정의할 때에는 다음 인터페이스의 구현을 통해서 크고 작음에 대한 기준을 정해주어야 한다.

```java
public interface Comparable<T>
    → 이 인터페이스에 위치한 유일한 추상 메소드 int compareTo(T o)
```

> **참고 — `Comparable` & `Comparable<T>` 인터페이스**
> 앞서 Chapter 20에서 `Comparable` 인터페이스에 대해 설명한 바 있다. 그리고 이를 제네릭 기반으로 정의한 `Comparable<T>` 인터페이스를 이어서 소개하려는데, 이 두 인터페이스에 위치한 추상 메소드의 정의 방법에는 차이가 없다. 즉 이어서 소개하는 내용은 Chapter 20에서 공부한 내용과 차이가 없다.

### 인스턴스의 비교 기준을 정의하는 `Comparable<T>` 인터페이스의 구현 기준

`Comparable<T>` 인터페이스를 구현할 때 정의해야 할 추상 메소드는 다음과 같다.

```java
int compareTo(T o)
```

그리고 이 메소드의 정의 방법은 다음과 같으며, 이는 자바에서 결정한 일종의 약속이다.

- 인자로 전달된 o가 작다면 양의 정수 반환
- 인자로 전달된 o가 크다면 음의 정수 반환
- 인자로 전달된 o와 같다면 0을 반환

예를 들어서 다음과 같이 `compareTo` 메소드가 호출되었을 때

```java
my.compareTo(your);
```

인스턴스 `your`가 `my`보다 작다면 양의 정수를, 반대로 `your`가 `my`보다 크다면 음의 정수를 반환하도록 메소드를 구현해야 한다. 그러면 `TreeSet<E>` 인스턴스는 `compareTo` 메소드의 호출 결과를 바탕으로, 저장된 인스턴스들이 정렬된 상태를 유지하게 한다. 그럼 다음 예제를 통해서 앞서 정의한 `Person` 클래스가 나이를 기준으로 정렬되도록 `compareTo` 메소드의 구현 결과를 보이겠다. 정렬 기준은 이렇다. 나이가 적으면 작은 것이고 나이가 많으면 큰 것이다.

```java
import java.util.TreeSet;
import java.util.Iterator;

class Person implements Comparable<Person> {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() { return name + " : " + age; }

    @Override
    public int compareTo(Person p) {
        return this.age - p.age;
    }
}

class ComparablePerson {
    public static void main(String[] args) {
        TreeSet<Person> tree = new TreeSet<>();
        tree.add(new Person("YOON", 37));
        tree.add(new Person("HONG", 53));
        tree.add(new Person("PARK", 22));

        for(Person p : tree)
            System.out.println(p);
    }
}
```

실행 결과

```
PARK : 22
YOON : 37
HONG : 53
```

예제에서 `Person` 클래스의 `compareTo` 메소드를 다음과 같이 구현하였다. 이로 인해 인자로 전달된 인스턴스의 나이가 더 많으면 음수가 반환된다. 즉 나이가 많으면 오름차순 정렬 순서상 뒤쪽에 위치하게 된다.

```java
@Override
public int compareTo(Person p) {
    return this.age - p.age;
}
```

이제 원하는 대로 크고 작음에 대한 기준, 다시 말해서 오름차순 정렬 순서상 앞서고 뒤섬에 대한 기준을 직접 결정하고 이를 반영할 수 있게 되었다. 줄을 세울 때 나이 많으신 분들을 우대하여 줄 앞쪽에 위치하도록 하려면 위의 메소드를 다음과 같이 수정하면 된다.

```java
@Override
public int compareTo(Person p) {
    return p.age - this.age;
}
```

### `Comparator<T>` 인터페이스를 기반으로 `TreeSet<E>`의 정렬 기준 제시하기

우리는 `Person` 클래스를 정의하였다. 이때 나이가 적은 사람이 앞쪽에 위치하도록 `compareTo` 메소드도 구현해 보았다. 그런데 나이가 많은 사람이 앞쪽에 위치하도록 기준을 바꿔야 한다면? 물론 메소드의 구현 내용을 수정하면 된다. 그러나 일시적인 기준 변경이라면 메소드를 수정하는 일은 적절치 않다. 그리고 다행히 이러한 상황을 고려하여 다음 인터페이스가 제공되고 있다.

```java
public interface Comparator<T>
    → int compare(T o1, T o2) 의 구현을 통해 정렬 기준을 결정할 수 있다.
```

이 인터페이스를 구현한 클래스의 인스턴스는 `TreeSet<E>`의 다음 생성자를 통해 전달할 수 있다.

```java
public TreeSet(Comparator<? super E> comparator)
```

그러면 이렇게 생성된 `TreeSet<E>` 인스턴스는 생성자로 전달된 인스턴스의 `compare` 메소드 호출 결과를 기준으로 정렬을 진행한다. 그리고 `compare` 메소드의 정의 기준은 다음과 같다.

```java
int compare(T o1, T o2)
```

- o1이 o2보다 크면 양의 정수 반환
- o1이 o2보다 작으면 음의 정수 반환
- o1과 o2가 같다면 0 반환

그럼 다음 예제를 통해서 위 메소드의 구현 결과를 확인하자.

```java
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() { return name + " : " + age; }

    @Override
    public int compareTo(Person p) {
        return this.age - p.age;
    }
}

class PersonComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p2.age - p1.age;
    }
}

class ComparatorPerson {
    public static void main(String[] args) {
        TreeSet<Person> tree = new TreeSet<>(new PersonComparator());
        tree.add(new Person("YOON", 37));
        tree.add(new Person("HONG", 53));
        tree.add(new Person("PARK", 22));

        for(Person p : tree)
            System.out.println(p);
    }
}
```

실행 결과

```
HONG : 53
YOON : 37
PARK : 22
```

위 예제에서는 다음과 같이 `Comparator<T>`를 구현하였다.

```java
class PersonComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p2.age - p1.age;    // 나이가 많으신 분을 앞에 세우는 연산
    }
}
```

그리고 다음과 같이 위의 인스턴스를 인자로 하여 `TreeSet<Person>` 인스턴스를 생성하였다. 따라서 이렇게 생성된 컬렉션 인스턴스는, 인자로 전달된 인스턴스의 `compare` 메소드 호출 결과를 바탕으로 정렬 상태를 유지하게 된다.

```java
TreeSet<Person> tree = new TreeSet<>(new PersonComparator());
```

`String` 클래스의 경우 사전 편찬 순으로 정렬이 되도록 이미 `Comparable<String>` 인터페이스를 구현하고 있다. 그런데 이 기준을 '문자열의 길이 순'으로 수정하고 싶다면? 지금 설명한 내용을 바탕으로 다음과 같이 코드를 작성하면 된다.

```java
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;

class StringComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

class ComparatorString {
    public static void main(String[] args) {
        TreeSet<String> tree = new TreeSet<>(new StringComparator());
        tree.add("Box");
        tree.add("Rabbit");
        tree.add("Robot");

        for(String s : tree)
            System.out.print(s.toString() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Box    Robot    Rabbit
```

위의 예제에서 보이듯이, 자바에서 제공하는 기본 클래스를 대상으로 정렬 기준을 바꿔야 하는 상황에서는 `Comparator<T>`의 구현이 좋은 해결책이 된다.

### 중복된 인스턴스를 삭제하려면

`List<E>`를 구현하는 컬렉션 클래스는 인스턴스의 중복 삽입을 허용한다. 그런데 저장된 인스턴스들 중에서 중복 삽입된 인스턴스들을 하나만 남기고 모두 지워야 한다고 가정해보자. 어떻게 이 일을 처리할 수 있겠는가? 이러한 작업을 위한 코드를 별도로 만들 수는 있지만 번거로운 일이다. 따라서 다음 예제에서 보이는 방법을 기억해 두는 것이 좋다.

```java
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

class ConvertCollection {
    public static void main(String[] args) {
        List<String> lst = Arrays.asList("Box", "Toy", "Box", "Toy");
        ArrayList<String> list = new ArrayList<>(lst);

        for(String s : list)
            System.out.print(s.toString() + '\t');
        System.out.println();

        // 중복된 인스턴스를 걸러 내기 위한 작업
        HashSet<String> set = new HashSet<>(list);

        // 원래대로 ArrayList<String> 인스턴스로 저장물을 옮긴다.
        list = new ArrayList<>(set);

        for(String s : list)
            System.out.print(s.toString() + '\t');
        System.out.println();
    }
}
```

실행 결과

```
Box    Toy    Box    Toy
Box    Toy
```

위 예제의 핵심은 다음 문장에 있다.

```java
HashSet<String> set = new HashSet<>(list);
```

그리고 이는 다음 생성자를 통해서 컬렉션 인스턴스를 생성하는 문장이다.

```java
public HashSet(Collection<? extends E> c)
    → 다른 컬렉션 인스턴스로부터 HashSet<E> 인스턴스 생성
```

위와 같이 인스턴스를 생성하면 `HashSet<String>` 인스턴스에 저장 대상을 복사하는 연산이 내부적으로 진행된다. 그리고 그 과정에서 `Set<E>` 인터페이스의 성격에 맞게 중복된 인스턴스는 걸러지게 된다.

## 23-4. `Queue<E>` 인터페이스를 구현하는 컬렉션 클래스들

이번에는 '스택'과 '큐'라는 자료구조를 소개하고자 한다. 이 둘은 응용 프로그램의 구현뿐 아니라 알고리즘의 구현에도 많이 사용되는 자료구조이다.

### 스택(Stack)과 큐(Queue)의 이해

스택은 '가장 먼저 저장된 데이터'가 가장 마지막에 빠져나오는 자료구조이다.

```
LIFO(last-in-first-out)
    → 먼저 저장된 데이터가 마지막에 빠져나간다.
```

즉 스택은 '아래가 막힌 긴 통'에 비유할 수 있다. 이러한 통에 물건을 넣으면, 가장 마지막에 들어간 물건이 먼저 나오고, 가장 먼저 들어간 물건이 마지막에 나온다.

**[그림 23-3: 스택의 구조]**

반면 큐는 들어간 순으로 빠져나오는 자료구조이다.

```
FIFO(first-in-first-out)
    → 먼저 저장된 데이터가 먼저 빠져나간다.
```

즉 큐는 앞과 뒤가 다 뚫려서 한쪽 방향으로는 넣고 다른 한쪽 방향으로는 꺼내는 통에 비유할 수 있다. 따라서 이 통에 물건을 넣으면 들어간 순으로 물건이 빠져나온다.

**[그림 23-4: 큐의 구조]**

### `Queue<E>` 인터페이스와 큐(Queue)의 구현

큐 자료구조를 위한 `Queue<E>` 인터페이스를 대표하는 세 가지 메소드는 다음과 같다.

| 메소드 | 설명 |
|---|---|
| `boolean add(E e)` | 넣기 |
| `E remove()` | 꺼내기 |
| `E element()` | 확인하기 |

이 중에서 `remove`는 인스턴스의 참조 값을 반환하면서 해당 인스턴스를 저장소에서 삭제하는 메소드이다. 반면 `element`는 인스턴스의 참조 값을 반환하지만 삭제하지 않는다. 그래서 이 메소드는 무엇이 들어 있는지 확인하는 메소드라 한다.
그런데 위의 세 메소드는 꺼낼 인스턴스가 없을 때 혹은 저장 공간이 부족할 때 예외를 발생시킨다. 반면에 `Queue<E>` 인터페이스의 다음 세 메소드는 동일한 상황에서 예외를 발생시키지 않고 해당 상황을 알리기 위한 특정 값(null 또는 false)을 반환한다.

| 메소드 | 설명 |
|---|---|
| `boolean offer(E e)` | 넣기, 넣을 공간이 부족하면 false 반환 |
| `E poll()` | 꺼내기, 꺼낼 대상 없으면 null 반환 |
| `E peek()` | 확인하기, 확인할 대상이 없으면 null 반환 |

일반적인 선택은 `offer`, `poll`, `peek`이다. 이유는 비어 있는 상황까지도 예외가 아닌 프로그램의 정상적인 흐름으로 간주하는 경우가 대부분이기 때문이다. 그럼 `Queue<E>`를 구현하는 대표적인 컬렉션 클래스를 다음 예제를 통해 소개하겠다.

```java
import java.util.Queue;
import java.util.LinkedList;

class LinkedListQueue {
    public static void main(String[] args) {
        Queue<String> que = new LinkedList<>();    // LinkedList<E> 인스턴스 생성!
        que.offer("Box");
        que.offer("Toy");
        que.offer("Robot");

        // 무엇이 다음에 나올지 확인
        System.out.println("next: " + que.peek());

        // 첫 번째, 두 번째 인스턴스 꺼내기
        System.out.println(que.poll());
        System.out.println(que.poll());

        // 무엇이 다음에 나올지 확인
        System.out.println("next: " + que.peek());

        // 마지막 인스턴스 꺼내기
        System.out.println(que.poll());
    }
}
```

실행 결과

```
next: Box
Box
Toy
next: Robot
Robot
```

위 예제에서 보이듯이 `LinkedList<E>`는 `List<E>`를 구현하면서 동시에 `Queue<E>`를 구현하는 컬렉션 클래스이다. 따라서 어떠한 타입의 참조변수로 참조하느냐에 따라서 '리스트'로도 동작하고 '큐'로도 동작한다.

### 스택(Stack)의 구현

자바는 기본 자료구조 대부분을 지원한다. 스택 자료구조도 컬렉션 클래스 `Stack<E>`를 통해 지원하고 있다.

```java
public class Stack<E> extends Vector<E>
```

그러나 `Stack<E>`는 (그리고 이 클래스가 상속하는 `Vector<E>`도) 자바 초기에 정의된 클래스로써 지금은 이전 코드와의 호환성 유지를 위해 존재하는 클래스일 뿐이다. `Stack<E>`는 동기화된 클래스로 멀티 쓰레드에 안전하지만, 그만큼 성능의 저하가 발생한다. ('동기화된 클래스'의 의미는 쓰레드를 소개하면서 설명한다.) 때문에 이 클래스의 사용은 권할 만한 일이 아니다. 대신에 자바 6에서 스택을 대신할 수 있는 '덱(Deque)'이라는 자료구조가 포함되었다. 그리고 이를 위해 다음 인터페이스를 정의하였다.

```java
public interface Deque<E> extends Queue<E>
```

덱은 외형 구조가 큐와 유사하다. 그러나 한쪽 방향으로만 넣고 꺼내는 큐와 달리 덱은 양쪽 끝에서 넣고 빼는 것이 가능한 자료구조이다. 따라서 덱을 스택처럼 사용하는 것이 가능하다. (뿐만 아니라 덱은 큐처럼 사용하는 것도 가능하다.)

**[그림 23-5: 덱의 구조]**

`Deque<E>`의 대표 메소드들은 다음과 같다.

- 앞으로 넣고, 꺼내고, 확인하기

| 메소드 | 설명 |
|---|---|
| `void addFirst(E e)` | 넣기 |
| `E removeFirst()` | 꺼내기 |
| `E getFirst()` | 확인하기 |

- 뒤로 넣고, 꺼내고, 확인하기

| 메소드 | 설명 |
|---|---|
| `void addLast(E e)` | 넣기 |
| `E removeLast()` | 꺼내기 |
| `E getLast()` | 확인하기 |

그런데 이들은 꺼낼 대상이 없을 때, 그리고 공간이 부족해서 넣지 못할 때 예외를 발생시킨다. 반면 `Deque<E>`의 다음 메소드들은 그러한 상황에서 예외를 발생시키지 않고 특정 값을 반환한다.

- 앞으로 넣고, 꺼내고, 확인하기

| 메소드 | 설명 |
|---|---|
| `boolean offerFirst(E e)` | 넣기, 공간 부족하면 false 반환 |
| `E pollFirst()` | 꺼내기, 꺼낼 대상 없으면 null 반환 |
| `E peekFirst()` | 확인하기, 확인할 대상 없으면 null 반환 |

- 뒤로 넣고, 꺼내고, 확인하기

| 메소드 | 설명 |
|---|---|
| `boolean offerLast(E e)` | 넣기, 공간이 부족하면 false 반환 |
| `E pollLast()` | 꺼내기, 꺼낼 대상 없으면 null 반환 |
| `E peekLast()` | 확인하기, 확인할 대상 없으면 null 반환 |

따라서 스택이 필요하면 `Deque<E>`을 구현한 컬렉션 클래스의 인스턴스를 대상으로 다음과 같이 쌍을 이루어 메소드를 호출하면 된다.

| 메소드 쌍 | 의미 |
|---|---|
| `offerFirst` & `pollFirst` | 앞으로 넣고 앞에서 꺼내기 |
| `offerLast` & `pollLast` | 뒤로 넣고 뒤에서 꺼내기 |

그럼 다음 예제를 통해서 `Deque<E>`을 구현하는 `ArrayDeque<E>` 클래스의 인스턴스를 스택처럼 활용하는 예를 보이겠다.

```java
import java.util.Deque;
import java.util.ArrayDeque;

class ArrayDequeCollection {
    public static void main(String[] args) {
        Deque<String> deq = new ArrayDeque<>();

        // 앞으로 넣고
        deq.offerFirst("1.Box");
        deq.offerFirst("2.Toy");
        deq.offerFirst("3.Robot");

        // 앞에서 꺼내기
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());
    }
}
```

실행 결과

```
3.Robot
2.Toy
1.Box
```

위 예제에서는 넣은 순서의 역순으로 `String` 인스턴스를 꺼냈다. 즉 스택으로 동작하게끔 메소드를 호출하였다. 그런데 위 예제의 다음 문장은,

```java
Deque<String> deq = new ArrayDeque<>();
    → 배열을 기반으로 하는 덱의 구성
```

다음 문장으로 대신할 수 있다.

```java
Deque<String> deq = new LinkedList<>();
    → 리스트를 기반으로 하는 덱의 구성
```

이렇듯 `LinkedList<E>`로 대신할 수 있는 이유는 이 클래스가 다음 세 가지 인터페이스를 모두 구현하기 때문이다.

```java
Deque<E>, List<E>, Queue<E>
```

따라서 어느 타입의 참조변수로 참조하느냐에 따라서 `LinkedList<E>`는 그 성격이 결정된다. 그리고 스택에 대한 이야기를 조금 더하면, 스택의 두 기능인 넣고 꺼내기 연산에 대해 전통적으로 다음과 같이 이름을 붙인다.

- 스택에 넣기 — push
- 스택에서 꺼내기 — pop

그런데 앞서 보인 예제에서는 덱을 스택처럼 사용했기 때문에 코드상에서 이것이 덱인지 스택인지 구분하기 어렵다. 뿐만 아니라, 스택으로 사용하려 했는데 앞으로 넣고 뒤로 꺼내는 실수를 할 수도 있는 상황이다. 따라서 스택이 필요한 경우에는 다음과 같이 별도의 클래스를 정의하여 사용할 것을 권한다.

```java
import java.util.Deque;
import java.util.ArrayDeque;

interface DIStack<E> {
    public boolean push(E item);
    public E pop();
}

class DCStack<E> implements DIStack<E> {
    private Deque<E> deq;

    public DCStack(Deque<E> d) {
        deq = d;
    }
    public boolean push(E item) {
        return deq.offerFirst(item);
    }
    public E pop() {
        return deq.pollFirst();
    }
}

class DefinedStack {
    public static void main(String[] args) {
        DIStack<String> stk = new DCStack<>(new ArrayDeque<String>());

        // PUSH 연산
        stk.push("1.Box");
        stk.push("2.Toy");
        stk.push("3.Robot");

        // POP 연산
        System.out.println(stk.pop());
        System.out.println(stk.pop());
        System.out.println(stk.pop());
    }
}
```

실행 결과

```
3.Robot
2.Toy
1.Box
```

위 예제에서 필자가 정의한 인터페이스와 클래스는 다음과 같다.

```java
interface DIStack<E>
class DCStack<E> implements DIStack<E>
```

이 인터페이스와 클래스를 기반으로 다음과 같이 문장을 구성하면 배열 기반의 스택이 생성된다.

```java
DIStack<String> stk = new DCStack<>(new ArrayDeque<String>());
```

그리고 다음과 같이 문장을 구성하면 리스트 기반의 스택이 생성된다.

```java
DIStack<String> stk = new DCStack<>(new LinkedList<String>());
```

비록 필자가 정의한 인터페이스와 클래스지만 이 둘을 분석하고 이해하는 것은 많은 도움이 되리라 생각한다.

> 💡 **개발 팁 — 오래된 클래스를 계속 남겨두는 이유**
> `Stack<E>`가 `Vector<E>`를 상속하는 구조는 사실 설계상 좋은 예가 아니다(스택은 "넣고 꺼내기"만 허용해야 하는데, `Vector`를 상속하다 보니 중간 인덱스에 마음대로 삽입하는 것까지 다 허용돼버린다). 그런데도 JDK는 이 클래스를 지금까지 삭제하지 않고 남겨뒀다 — 오래전에 이 클래스로 작성된 수많은 코드가 갑자기 컴파일이 안 되면 안 되기 때문이다. `Deque`라는 더 나은 대안을 새로 추가하고, 기존 `Stack`은 "권장하지 않지만 지우지도 않는" 상태로 공존시키는 것 — 이것도 하위 호환성을 지키는 실무적인 방식 중 하나다.

## 23-5. `Map<K, V>` 인터페이스를 구현하는 컬렉션 클래스들

`Map<K, V>`를 구현하는 컬렉션 클래스의 인스턴스들은 Key와 Value가 한 쌍을 이루는 형태로 데이터를 저장한다.

### Key-Value 방식의 데이터 저장과 `HashMap<K, V>` 클래스

캐비닛에 서류철을 보관할 때 해당 서류철을 쉽게 찾을 수 있도록 서류철의 특정 위치에 서류의 정보나 이름을 써넣는다. 그리고 이것이 Key와 Value가 하나의 쌍을 이루는 데이터 저장 방식이다. Key는 실질적 데이터가 아니다. 대신 데이터 Value를 찾는 지표가 된다.
`Collection<E>`를 구현하는 클래스가 Value를 저장하는 구조였다면, `Map<K, V>`를 구현하는 클래스는 Value를 저장할 때, 이를 찾을 때 사용하는 Key를 함께 저장하는 구조이다. 때문에 Key는 중복될 수 없다. 반면 Key만 다르다면 Value는 중복이 되어도 상관없다.

> "Key는 지표이므로 중복될 수 없다. 반면 Key만 다르면 Value는 중복되어도 상관없다."

`Map<K, V>`를 구현하는 대표 클래스로 `HashMap<K, V>`와 `TreeMap<K, V>`가 있다. 둘의 가장 큰 차이점은, 트리 자료구조를 기반으로 구현된 `TreeMap<K, V>`은 정렬 상태를 유지한다는데 있다. 물론 정렬의 대상은 Value가 아니라 Key이다. 그럼 먼저 `HashMap<K, V>`의 사용의 예를 보이겠다.

```java
import java.util.HashMap;

class HashMapCollection {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        // Key-Value 기반 데이터 저장
        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        // 데이터 탐색
        System.out.println("23번: " + map.get(23));
        System.out.println("37번: " + map.get(37));
        System.out.println("45번: " + map.get(45));
        System.out.println();

        // 데이터 삭제
        map.remove(37);

        // 데이터 삭제 확인
        System.out.println("37번: " + map.get(37));
    }
}
```

실행 결과

```
23번: Martin
37번: James
45번: Brown

37번: null
```

예제의 다음 문장에서 보이듯이 Key도 Value도 인스턴스이어야 한다.

```java
HashMap<Integer, String> map = new HashMap<>();
```

다만 예제에서는 Key가 `Integer`이므로 저장, 참조 그리고 삭제의 과정에서 Key에 대한 오토 박싱과 오토 언박싱이 진행되어서 int형 정수가 key인 것처럼 보였을 뿐이다.

### `HashMap<K, V>`의 순차적 접근 방법

`HashMap<K, V>` 클래스는 `Iterable<T>` 인터페이스를 구현하지 않으니 for-each문을 통해서, 혹은 '반복자'를 얻어서 순차적 접근을 진행할 수 없다. 대신에 `Map<K, V>`에는 다음 메소드가 존재한다.

```java
public Set<K> keySet()
```

이 메소드는 `Set<E>`을 구현하는 컬렉션 인스턴스를 생성하고, 여기에 모든 Key를 담아서 반환한다. 따라서 이 메소드를 통해서 다음 예제와 같이 모든 Key를 따로 모으고, 이를 통한 순차적 접근을 진행할 수 있다.

```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class HashMapIteration {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        // Key만 담고 있는 컬렉션 인스턴스 생성
        Set<Integer> ks = map.keySet();

        // 전체 Key 출력 (for-each문 기반)
        for(Integer n : ks)
            System.out.print(n.toString() + '\t');
        System.out.println();

        // 전체 Value 출력 (for-each문 기반)
        for(Integer n : ks)
            System.out.print(map.get(n).toString() + '\t');
        System.out.println();

        // 전체 Value 출력 (반복자 기반)
        for(Iterator<Integer> itr = ks.iterator(); itr.hasNext(); )
            System.out.print(map.get(itr.next()) + '\t');
        System.out.println();
    }
}
```

실행 결과

```
37    23    45
James    Martin    Brown
James    Martin    Brown
```

위 예제의 핵심은 다음 문장에 있다.

```java
Set<Integer> ks = map.keySet();
```

`Set<E>`은 `Iterable<E>`을 상속하므로 예제에서 보이듯이 위의 문장 실행 이후에 for-each문을 통해서, 또는 반복자를 얻어서 순차적 접근을 진행할 수 있다.

### `TreeMap<K, V>`의 순차적 접근 방법

`HashSet<E>`이 해쉬 알고리즘을 기반으로 구현되어 있듯이, `HashMap<K, V>` 역시 해쉬 알고리즘을 기반으로 구현되어 있다. 그리고 `TreeSet<E>`이 트리 자료구조를 기반으로 구현되어 있어서 정렬 상태를 유지하듯이 `TreeMap<K, V>` 역시 트리 자료구조를 기반으로 구현되어 있어서 정렬 상태를 유지한다. 그럼 조금 전에 보인 예제에서 컬렉션 클래스만 `TreeMap<K, V>`으로 바꿔서 실행해보자. 그랬을 때 실행 결과가 어떻게 차이가 나는지 확인해보자.

```java
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;

class TreeMapIteration {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        // Key만 담고 있는 컬렉션 인스턴스 생성
        Set<Integer> ks = map.keySet();

        // 전체 Key 출력 (for-each문 기반)
        for(Integer n : ks)
            System.out.print(n.toString() + '\t');
        System.out.println();

        // 전체 Value 출력 (for-each문 기반)
        for(Integer n : ks)
            System.out.print(map.get(n).toString() + '\t');
        System.out.println();

        // 전체 Value 출력 (반복자 기반)
        for(Iterator<Integer> itr = ks.iterator(); itr.hasNext(); )
            System.out.print(map.get(itr.next()) + '\t');
        System.out.println();
    }
}
```

실행 결과

```
23    37    45
Martin    James    Brown
Martin    James    Brown
```

위의 실행 결과에서는 Key에 해당하는 나이 정보가 오름차순으로 출력되었다. 이렇듯 대상 컬렉션 인스턴스에 따라서 반환되는 반복자의 성격은 달라진다. `TreeMap<K, V>` 인스턴스에서 반환된 반복자는 오름차순으로 Key에 접근한다.
이번에는 내림차순으로 나이 정보가 출력되도록 예제를 수정해보자. `Comparator<T>` 인터페이스를 기반으로 `TreeSet<E>`의 정렬 기준을 결정했던 예제를 떠올리면 방법을 쉽게 찾을 수 있다.

```java
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Comparator;

class AgeComparator implements Comparator<Integer> {
    public int compare(Integer n1, Integer n2) {
        return n2.intValue() - n1.intValue();
    }
}

class ComparatorTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>(new AgeComparator());
        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        // Key만 담고 있는 컬렉션 인스턴스 생성
        Set<Integer> ks = map.keySet();

        // 전체 Key 출력 (for-each문 기반)
        for(Integer n : ks)
            System.out.print(n.toString() + '\t');
        System.out.println();

        // 전체 Value 출력 (for-each문 기반)
        for(Integer n : ks)
            System.out.print(map.get(n).toString() + '\t');
        System.out.println();

        // 전체 Value 출력 (반복자 기반)
        for(Iterator<Integer> itr = ks.iterator(); itr.hasNext(); )
            System.out.print(map.get(itr.next()) + '\t');
        System.out.println();
    }
}
```

실행 결과

```
45    37    23
Brown    James    Martin
Brown    James    Martin
```

`Comparator<T>`를 구현하는 `AgeComparator` 클래스를 정의하고, 다음과 같이 `TreeMap<K, V>` 인스턴스를 생성한 것이 수정 내용 전부이다.

```java
TreeMap<Integer, String> map = new TreeMap<>(new AgeComparator());
```

그리고 `AgeComparator` 클래스가 `Comparator<T>`을 구현하면서 T를 `Integer`로 결정한 이유는 정렬 대상인 Key가 `Integer`이기 때문이다.

> 💡 **개발 팁 — `keySet()` + `get()` 조합의 숨은 비용**
> 지금까지 본 것처럼 `keySet()`으로 Key를 뽑은 뒤 `map.get(key)`로 Value를 다시 찾는 패턴은 코드가 직관적이지만, 내부적으로는 **매 반복마다 해쉬 탐색(`get`)이 한 번씩 더 일어난다.** Key-Value 쌍 자체를 한 번에 순회하고 싶다면 `Map.entrySet()`(이 Key와 Value를 쌍으로 묶은 `Map.Entry<K,V>`를 반환)을 쓰는 방법도 있다 — 지금 당장 다룬 내용은 아니지만, 나중에 `HashMap`을 많이 순회하는 코드를 짤 때 "매번 다시 찾지 않고 한 번에 쌍으로 순회하는 방법이 있을까?"라는 질문을 스스로 던져보면 좋다.
