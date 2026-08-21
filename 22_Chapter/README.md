# Chapter 22 — 제네릭(Generics) 2

제네릭의 두 번째 시간이다. 이번에 설명하는 내용은 이전 내용보다 조금 더 생각을 요한다. 특히 '와일드카드'를 설명하는 부분부터는 많은 집중을 요한다.

## 22-1. 제네릭의 심화 문법

'제네릭 기본 문법' 편에 이어지는 내용이다. 제목에 '심화 문법'이라는 표현을 썼지만, 와일드카드를 설명하기 전까지는 그냥 이어지는 내용으로 생각하자.

### 제네릭 클래스와 상속 — 예제 `P510_GenericInheritance`

제네릭 클래스도 상속이 가능하다. 이와 관련하여 다음 예제를 보자. 참고로 이 예제에서 처음으로 제네릭 클래스의 생성자를 보이고 있다. 물론 일반적인 생성자와 특별히 다른 것은 없다.

```java
class Box<T> {
    protected T ob;
    public void set(T o) { ob = o; }
    public T get() { return ob; }
}

class SteelBox<T> extends Box<T> {
    public SteelBox(T o) {    // 제네릭 클래스의 생성자
        ob = o;
    }
}

class GenericInheritance {
    public static void main(String[] args) {
        Box<Integer> iBox = new SteelBox<>(7959);
        Box<String> sBox = new SteelBox<>("Simple");

        System.out.println(iBox.get());
        System.out.println(sBox.get());
    }
}
```

실행 결과

```
7959
Simple
```

제네릭 클래스의 상속을 설명하기 위해서, 예제에서는 `Box<T>`를 상속하는 하위 클래스를 다음과 같이 간단히 정의하였다.

```java
class SteelBox<T> extends Box<T> {
    public SteelBox(T o) {    // 생성자
        ob = o;
    }
}
```

그리고 이로 인하여 다음과 같이 `Box<T>`의 참조변수로 `SteelBox<T>` 인스턴스를 참조하는 문장을 구성할 수 있게 되었다.

```java
Box<Integer> iBox = new SteelBox<>(7959);
    ↔ Box<Integer> iBox = new SteelBox<Integer>(7959);

Box<String> sBox = new SteelBox<>("Simple");
    ↔ Box<String> sBox = new SteelBox<String>("Simple");
```

즉, 두 제네릭 클래스가 다음의 상속 관계를 구성하면,

**[그림 22-1: 제네릭 클래스의 상속]** — `Box<T>` ← `SteelBox<T>`

다음 관계도 성립한다. 때문에 예제에서 `SteelBox<Integer>` 인스턴스를 `Box<Integer>`형 참조변수로 참조할 수 있었다.

**[그림 22-2: 제네릭 클래스의 상속으로 인해 형성되는 관계]** — `Box<Integer>` ← `SteelBox<Integer>`, `Box<String>` ← `SteelBox<String>`

앞 Chapter에서 `Box<Integer>`와 같은 것을 '매개변수화 타입' 또는 '제네릭 타입'이라 함을 설명하였는데, 이렇듯 '타입(Type)'이라는 단어가 포함된 것은 `Box<Integer>`를 일종의 자료형, 정확히는 클래스의 이름으로 간주함을 뜻한다. 따라서 위와 같은 상속의 관계가 형성될 수 있고, 이를 다음과 같이 표현할 수 있다.

> "`SteelBox<Integer>` 클래스는 `Box<Integer>` 클래스를 상속한다."

물론 다음과 같이 표현하는 것이 보편적이긴 하다.

> "`SteelBox<Integer>` 제네릭 타입은 `Box<Integer>` 제네릭 타입을 상속한다."

#### 매개변수화 타입 사이에는 상속 관계가 형성되지 않는다

그렇다면 다음 문장도 컴파일이 가능할까? `Number`를 `Integer`가 상속하니 컴파일이 되지 않을까?

```java
Box<Number> box = new Box<Integer>();    // 컴파일 가능할까?
```

`Number`를 `Integer`가 상속하지만 `Box<Number>`와 `Box<Integer>`는 **상속 관계를 형성하지 않는다.** 따라서 컴파일 되지 않는다. 참고로 지금 설명한 이 내용은 단순한 지식에 그치는 문법이 아니라, 잠시 후에 설명할 내용의 사전 지식이 되므로 잘 이해하고 기억하기 바란다.

`Box<Number>`와 `Box<Integer>`가 상속 관계를 형성하지 않는 것은 언어를 디자인 한 설계자의 결정이므로 이해보다는 인식이 우선인 부분이다. 그러나 조금만 생각해 보면 이러한 결정이 합리적임을 알 수 있다. 예를 들어서 `SteelBox<Integer>`와 `Box<Integer>`가 상속 관계를 형성하는데, 여기에 더해 `Box<Integer>`와 `Box<Number>`가 상속 관계를 형성한다면? 매우 혼란스러운 상속의 구조가 만들어진다. 그에 따른 이점은 별로 보이지 않는데 말이다.

> 💡 **개발 팁 — 상속 관계가 '안 생기는' 것이 안전장치다**
> 상속 관계를 막은 데에는 혼란 방지 외에 타입 안전성이라는 실질적인 이유가 하나 더 있다. 만약 `Box<Integer>`가 `Box<Number>`의 하위 타입이라면, `Box<Number>` 참조변수를 통해 `Double` 값을 넣는 코드가 컴파일을 통과하게 된다. 실제로 정수를 담기로 한 상자에 실수가 들어가는 것이다. 자바의 **배열은 이런 상속 관계를 허용**해서 `Number[] arr = new Integer[3];`이 컴파일 되지만, 대신 잘못된 값을 넣는 순간 실행 중에 `ArrayStoreException`이 발생한다. 제네릭은 같은 실수를 아예 컴파일 단계에서 막는 쪽을 택했다. 오류를 늦게 만나는 대신 이르게 만나도록 한 설계다.

### 타겟 타입 (Target Types) — 예제 `P513_TargetTypes`

앞서 Chapter 21에서 자바 컴파일러는 생략된 자료형 정보에 대해 유추하는 능력이 있음을 설명하였다. 그런데 컴파일러가 자료형 유추를 진행하는 상황이 생각보다 다양하다. 그럼 이와 관련하여 다음 예제를 보자.

```java
class Box<T> {
    private T ob;
    public void set(T o) { ob = o; }
    public T get() { return ob; }
}

class EmptyBoxFactory {
    public static <T> Box<T> makeBox() {    // 제네릭 메소드
        Box<T> box = new Box<T>();    // 상자 생성
        return box;    // 생성한 상자 반환
    }
}

class TargetTypes {
    public static void main(String[] args) {
        Box<Integer> iBox = EmptyBoxFactory.<Integer>makeBox();
        iBox.set(25);
        System.out.println(iBox.get());
    }
}
```

실행 결과

```
25
```

위의 예제에서는 다음과 같이 상자를 생성해서 반환하는 '제네릭 메소드'를 정의하였다.

```java
public static <T> Box<T> makeBox() {
    Box<T> box = new Box<T>();
    return box;
}
```

그런데 이전에 구현했던 `BoxFactory` 클래스의 `makeBox` 메소드와 달리 **인자를 전달받지 않는다.** 당시에는 인자를 전달받았기 때문에 컴파일러가 이 인자를 통해서 `T`를 유추할 수 있었다. 그러나 위의 메소드는 인자를 전달받지 않으므로 다음과 같이 `T`에 대한 타입 인자를 전달해야 한다.

```java
Box<Integer> iBox = EmptyBoxFactory.<Integer>makeBox();
```

#### 자바 7부터 넓어진 유추의 범위

그런데 자바 7부터 다음과 같이 호출하는 것이 가능하게 되었다. 자바 7부터 컴파일러의 자료형 유추 범위가 넓어졌기 때문이다.

```java
Box<Integer> iBox = EmptyBoxFactory.makeBox();    // 자바 7부터 컴파일 되는 문장
```

어떻게 가능한 것일까? 우리는 위의 문장을 보면서 `makeBox` 메소드는 `Box<Integer>` 인스턴스의 참조 값을 반환해야 한다고 판단할 수 있다. **왼편에 선언된 매개변수의 형을 보고** 이러한 판단을 할 수 있다. 따라서 `makeBox` 메소드 호출 시 `T`는 `Integer`가 되어야 함을 알 수 있다. 그런데 이러한 판단을 자바 7부터 컴파일러도 할 수 있게 되었다.

지금 설명한 상황에서 `T`의 유추에 사용된 정보 `Box<Integer>`를 가리켜 **'타겟 타입'**이라 한다. 그리고 이러한 유추는 당연한 듯 보이지만, **대입 연산자의 왼편에 있는 정보를 가지고** 컴파일러가 이러한 유추를 진행한다는 것은 주목할 만한 일이다.

> 💡 **개발 팁 — 추론은 '편의'지 '생략'이 아니다**
> 타겟 타입 덕분에 `EmptyBoxFactory.<Integer>makeBox()`가 `EmptyBoxFactory.makeBox()`로 짧아졌다. 하지만 짧아진 만큼 **읽는 사람이 타입을 눈으로 좇을 단서도 줄어든다.** 자바 10에 추가된 `var`가 같은 트레이드오프를 갖는다. 실무의 관례는 대체로 이렇다 — 대입문의 한쪽에 타입이 분명히 드러나는 지역 변수에서는 추론에 맡기고, **public 메소드의 반환형처럼 API의 경계가 되는 자리에는 타입을 명시**한다. 경계에 적힌 타입은 컴파일러를 위한 정보이기 이전에 그 API를 쓸 사람을 위한 문서이기 때문이다.
