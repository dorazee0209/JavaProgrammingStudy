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

### 와일드카드(Wildcard) — 예제 `P515_WildcardBoxer`, `P516_wildcardUnboxer2`

드디어 제네릭에서 어렵다고 알려진 와일드카드에 대한 설명을 진행할 차례이다. 최대한 쉽게 그리고 정리해 가며 설명을 진행하겠다. 앞서 Chapter 21에서 다음 클래스를 정의한 바 있다. 이 클래스의 핵심은 제네릭 메소드의 정의에 있다.

```java
class Unboxer {
    public static <T> T openBox(Box<T> box) {
        return box.get();    // 상자 안의 내용물 반환
    }
}
```

위 클래스에 상자의 내용물을 반환하지 않고 그저 '무엇이 들었나' 정도만 확인하는 기능의 제네릭 메소드를 하나 추가하였다.

```java
// 상자 안의 내용물을 확인하는(출력하는) 기능의 제네릭 메소드
public static <T> void peekBox(Box<T> box) {
    System.out.println(box);
}
```

실행 결과

```
So Simple String
```

그런데 이 메소드를 제네릭으로 정의한 이유가 `Box<Integer>`, `Box<String>`의 인스턴스를 인자로 전달받도록 하기 위함이니, 다음과 같이 정의해도 되지 않겠는가?

```java
public static void peekBox(Box<Object> box) {
    System.out.println(box);
}
```

안된다! 이에 대해서는 앞서 *제네릭 클래스와 상속*에서 언급하였는데, 그 내용을 바탕으로 안되는 이유를 정리하면 다음과 같다.

> "`Box<Object>`와 `Box<String>`은 상속 관계를 형성하지 않는다."
>
> "`Box<Object>`와 `Box<Integer>`는 상속 관계를 형성하지 않는다."

즉 `Object`와 `String`이 상속 관계에 있더라도 `Box<Object>`와 `Box<String>`은 상속 관계를 형성하지 않는 별개의 자료형이다. 대신 **'와일드카드'** 라는 것을 사용하면 원하는 바를 이룰 수 있다.

```java
public static void peekBox(Box<?> box) {    // 와일드카드 사용
    System.out.println(box);
}
```

물음표 기호로 표시되는 와일드카드를 이용해서 메소드의 매개변수를 위와 같이 선언하면, `Box<T>`를 기반으로 생성된 `Box<Integer>` 인스턴스나 `Box<String>` 인스턴스들을 인자로 받을 수 있다.

#### 제네릭 메소드와 와일드카드 기반 메소드

그렇다면 다음 두 메소드에는 어떠한 차이가 있을까? 위에서 제시한 두 예제에서 보인 결과를 보면 아무런 차이가 없는데 말이다.

```java
public static <T> void peekBox(Box<T> box) {
    System.out.println(box);
}    // 제네릭 메소드의 정의

public static void peekBox(Box<?> box) {
    System.out.println(box);
}    // 와일드카드 기반 메소드 정의
```

사실 기능적인 측면에서 보면 위의 두 메소드는 완전히 동일하다. 즉 제네릭 메소드와 와일드카드 기반 메소드는 상호 대체 가능한 측면이 있다. 그러나 **코드가 조금 더 간결하다는 이유로 와일드카드 기반 메소드의 정의를 선호한다.**

앞서 제시한 두 메소드를 보면 제네릭 메소드 정의에는 `<T>`가 두 번 등장한다.

```java
public static <T> void peekBox(Box<T> box)
```

반면 와일드카드 기반 메소드 정의에는 `<?>`가 매개변수 선언에서 한 번만 등장한다.

```java
public static void peekBox(Box<?> box)
```

지금은 이 차이가 별것 아닌 것 같지만 `<T>` 또는 `<?>`에 추가적인 선언이 들어가면 이러한 차이는 더 커진다. 그리고 개인적인 취향과 상관 없이 이러한 보편적인 선호도를 따라서 코드를 작성하는 것도 중요하다.

### 와일드카드의 상한과 하한의 제한: Bounded Wildcards — 예제 `P519_UpperBoundedWildcard`, `P521_LowerBoundedWildcard`

와일드카드의 '상한 제한'과 '하한 제한'을 문법적 측면에서 일단 설명하겠다. 그리고 나서 와일드카드에 제한을 거는 이유에 대해 설명하겠다. 먼저 다음 메소드를 보자.

```java
public static void peekBox(Box<?> box) {
    System.out.println(box);
}
```

#### 상한 제한된 와일드카드 (Upper-Bounded Wildcards)

위 메소드의 인자로, `Box<T>`에서 `T`가 `Number` 또는 `Number`의 하위 클래스인 제네릭 타입의 인스턴스만 전달되도록 제한할 때 다음과 같이 '상한 제한된 와일드카드'라는 것을 사용한다.

```
Box<? extends Number> box
    → box는 Box<T> 인스턴스를 참조하는 참조변수이다.
    → 단 이때 Box<T> 인스턴스의 T는 Number 또는 이를 상속하는 하위 클래스이어야 함
```

따라서 메소드 `peekBox`의 매개변수에 다음과 같이 제한을 걸어서 `Box<Integer>`, `Box<Double>`과 같은 제네릭 타입의 인스턴스만 인자로 전달되도록 할 수 있다.

```java
public static void peekBox(Box<? extends Number> box) {
    System.out.println(box);
}
```

실행 결과

```
1234
10.009
```

#### 하한 제한된 와일드카드 (Lower-Bounded Wildcards)

그리고 다음과 같이 참조변수에 '하한 제한된 와일드카드' 선언을 할 수도 있다.

```
Box<? super Integer> box
    → box는 Box<T> 인스턴스를 참조하는 참조변수이다.
    → 단 이때 Box<T> 인스턴스의 T는 Integer 또는 Integer가 상속하는 클래스이어야 함
```

예를 들어서 메소드의 매개변수를 다음과 같이 선언하면,

```java
public static void peekBox(Box<? super Integer> box) {
    System.out.println(box);
}
```

위 메소드의 인자로 전달될 수 있는 인스턴스의 타입 종류는 다음과 같이 제한된다.

```
Box<Integer>, Box<Number>, Box<Object>
```

실행 결과

```
5577
9955
My Simple Instance
```

### 언제 와일드카드에 제한을 걸어야 하는가? : 도입

다음 메소드의 매개변수 선언에 대해서 설명하라고 하면,

```java
public static void peekBox(Box<? extends Number> box) {...}
```

인자로 전달할 수 있는 인스턴스의 형과 관련하여 다음 내용으로 설명하고 마무리하는 경우가 대부분이다.

> "`Box<T>`의 `T`를 `Number` 또는 `Number`를 직간접적으로 상속하는 클래스로 제한하기 위한 것"

물론 정확한 설명이다. 그리고 인자로 전달되는 대상을 제한하는 것은 그 자체로 프로그램에 안정성을 높여 의미가 있다. 그러나 **다른 관점에서** '상한 제한된 와일드카드'의 의미를 설명할 수 있어야 한다. 마찬가지로 다음 메소드의 매개변수 선언에 대해서 설명하라고 하면,

```java
public static void peekBox(Box<? super Integer> box) {...}
```

인자로 전달할 수 있는 인스턴스의 형과 관련하여 다음 내용으로 설명하고 마무리하는 경우가 대부분이다.

> "`Box<T>`의 `T`를 `Integer` 또는 `Integer`가 직간접적으로 상속하는 클래스로 제한하기 위한 것"

그러나 이 경우에도 다른 관점에서 하한 제한된 와일드카드의 의미를 설명할 수 있어야 한다. 그렇지 않으면 자바에서 제공하는 다음과 같은 메소드의 사용은 부담스러울 수밖에 없다.

```java
public static <T> void copy(List<? super T> dest, List<? extends T> src)
    → Collections 클래스의 복사 메소드
```

그러나 '컬렉션 프레임워크'를 공부하면서 우리는 이 메소드를 사용해야 한다. 그래서 이의 이해에 필요한 내용을 지금부터 설명하려고 한다. 참고로 지금부터 설명하는 내용은 본서의 내용 중에 어려운 편에 속한다. 정확히는 자바 문법 중에서 어려운 내용에 속한다.

### 언제 와일드카드에 제한을 걸어야 하는가? : 상한 제한의 목적 — 예제 `P523_BoundedWildcardBase`, `P526_BoundedWildcardUsage`

와일드카드의 상한 제한이 어떻게 활용되는지 설명하기에 앞서 다음 예제를 관찰하자. 이는 지금까지 공부한 내용을 바탕으로 쉽게 분석할 수 있는 수준의 예제이다.

```java
class BoxHandler {
    public static void outBox(Box<Toy> box) {
        Toy t = box.get();    // 상자에서 꺼내기
        System.out.println(t);
    }
    public static void inBox(Box<Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```

실행 결과

```
I am a Toy
```

첫 번째 메소드 `outBox`는 상자에서 물건을 꺼낼 때 사용하는 메소드이다. 반면 `inBox`는 상자에 물건을 넣을 때 사용하는 메소드이다. 둘 다 잘 정의되었고 잘 동작한다. 그러나 잘 만들어진 코드는 다음의 조건을 추가로 만족해야 하는데, 위의 두 메소드는 이 조건까지 만족하는 형태로 정의되지 않았다.

> "필요한 만큼만 기능을 허용하여, 코드의 오류가 컴파일 과정에서 최대한 발견되도록 한다."

#### 꺼내는 메소드인데 넣는 것도 가능하다

먼저 다음 메소드를 보자.

```java
public static void outBox(Box<Toy> box) {...}
    → 매개변수 box가 참조하는 상자에서 인스턴스를 꺼내는 기능
```

이 메소드를 정의할 당시 프로그래머의 생각은 다음과 같다.

> "상자에서 내용물을 꺼내는 기능의 메소드를 정의하자."

그런데 매개변수 `box`를 대상으로는 다음과 같이 `get`은 물론 `set`의 호출도 가능하다.

```java
public static void outBox(Box<Toy> box) {
    box.get();             // 꺼내는 것! OK!
    box.set(new Toy());    // 넣는 것! 이것도 OK!
}
```

따라서 다음과 같은 유형의 오류를 범할 수 있는 상황이다.

> "`outBox` 메소드 내에서 실수로 `set` 메소드를 호출하여 임의의 인스턴스를 넣었다."

이러한 실수는 누구나 할 수 있다. 그러나 이러한 오류는 컴파일 과정에서 발견되지 않는다. 때문에 `outBox` 메소드를 정의할 때에는 매개변수 `box`를 대상으로 `get`은 가능하지만 `set`은 불가능하도록 제한을 거는 것이 좋다. 그리고 이러한 일이 '필요한 만큼만 기능을 허용하여, 코드의 오류가 컴파일 과정에서 최대한 발견되도록 하는 일'이다.

#### 상한 제한을 걸면 넣는 것이 막힌다

그렇다면 어떻게 `outBox` 메소드를 정의해야 할까? 다음과 같이 매개변수 선언을 하면 상자에서 꺼내는 것은 가능하지만 넣는 것은 불가능하게 된다. 넣으려고 하면 컴파일 오류가 발생한다.

```java
public static void outBox(Box<? extends Toy> box) {
    box.get();             // 꺼내는 것! OK!
    box.set(new Toy());    // 넣는 것! ERROR!
}
```

위의 상황에서 `set` 메소드의 호출이 불가능한 이유는 무엇일까? 바로 결론을 말하면, 위 메소드의 매개변수로 **`Toy` 인스턴스를 저장할 수 있는 상자만(`Box<T>` 인스턴스만) 전달된다는 사실을 보장할 수 없기 때문이다.**

이에 대해 보충 설명을 하면, `Toy` 클래스는 다음과 같이 다른 클래스들에 의해 얼마든지 상속이 될 수 있다.

```java
class Car extends Toy {...}      // 자동차 장난감
class Robot extends Toy {...}    // 로봇 장난감
```

그리고 이렇게 상속 관계를 맺으면 위의 `outBox` 메소드에 `Box<Car>` 또는 `Box<Robot>` 인스턴스가 인자로 전달될 수 있다. 이러한 상황에서 다음과 같이 `Toy` 인스턴스를 상자에 담을 수 있겠는가?

```java
public static void outBox(Box<? extends Toy> box) {
    // box로 Box<Car> 또는 Box<Robot> 인스턴스가 전달된다면?
    box.set(new Toy());    // 넣는 것! ERROR!
}
```

바로 이러한 문제점 때문에 `Box<? extends Toy> box`와 같이 선언된 매개변수를 대상으로는 저장하는(전달하는) 메소드의 호출이 불가능하다. 지금까지 설명한 내용을 정리하면, 다음과 같은 매개변수 선언을 보았을 때,

```java
public static void outBox(Box<? extends Toy> box) {
    /* 이 안에서는 box가 참조하는 인스턴스에
             Toy 인스턴스를 저장하는(전달하는) 메소드 호출은 불가능하다. */
}
```

다음과 같은 판단을 할 수 있어야 한다.

> "`box`가 참조하는 인스턴스를 대상으로 저장하는 기능의 메소드 호출은 불가능하다."

그리고 지금 설명한 내용을 바탕으로 앞서 제시한 예제의 수준을 높일 수 있게 되었다.

### 언제 와일드카드에 제한을 걸어야 하는가? : 하한 제한의 목적 — 예제 `P529_BoundedWildcardUsage2`

이번에는 다음 클래스의 두 번째 메소드에 주목하자.

```java
class BoxHandler {
    ...
    public static void inBox(Box<Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```

위의 두 번째 메소드 `inBox`도 좋은 코드가 되기 위한 다음 조건을 만족하지 못한다.

> "필요한 만큼만 기능을 허용하여, 코드의 오류가 컴파일 과정에서 최대한 발견되도록 한다."

이 메소드는 상자에 인스턴스를 저장하는 것이 목적이니, 다음과 같이 `get` 메소드를 호출하는 코드가 삽입된다면 이는 분명 프로그래머의 실수이다.

```java
public static void inBox(Box<Toy> box, Toy n) {
    box.set(n);                // 넣는 것! OK!
    Toy myToy = box.get();     // 꺼내는 것! 이것도 OK!
}
```

#### 하한 제한을 걸면 꺼내는 것이 막힌다

그러나 이러한 실수는 컴파일 과정에서 발견되지 않는다. 따라서 이러한 실수가 컴파일 과정에서 발견될 수 있도록 매개변수를 다음과 같이 선언해야 한다.

```java
public static void inBox(Box<? super Toy> box, Toy n) {
    box.set(n);                // 넣는 것! OK!
    Toy myToy = box.get();     // 꺼내는 것! Error!
}
```

위와 같이 매개변수를 선언하면 `get` 메소드의 호출문에서 컴파일 오류가 발생한다. 이유는 **반환형을 `Toy`로 결정할 수 없기 때문이다.** 즉 `get` 메소드 호출 자체는 문제 되지 않으나, 반환되는 값을 저장하기 위해 선언한 참조변수의 형을 `Toy`로 결정했다는 사실에서 문제가 발생한다.

이와 관련하여 보충 설명을 하기 위해 `Toy` 클래스의 상속 관계가 다음과 같다고 가정하자.

```java
class Plastic {...}
class Toy extends Plastic {...}
```

그러면 `inBox` 메소드의 첫 번째 인자로 전달 가능한 두 가지 유형의 `Box<T>` 인스턴스는 다음과 같다.

```java
Box<Toy> tBox = new Box<Toy>();
Box<Plastic> pBox = new Box<Plastic>();
```

그리고 위의 `inBox` 메소드에 인자로 `tBox`가 전달되면 메소드 내에서 다음 문장을 실행하는데 문제가 없지만,

```java
Toy myToy = box.get();    // get이 반환하는 것이 Toy 인스턴스이므로 문제가 없지만,
```

`pBox`가 전달되면, 메소드 내에서 다음 문장을 실행하는데 있어서 문제가 된다. 그래서 컴파일러는 이 문장 자체를 허용하지 않는다.

```java
Toy myToy = box.get();    // get이 반환하는 것이 Plastic 인스턴스이므로 문제가 된다.
```

자! 그럼 지금 설명한 내용을 이렇게 정리하자. 다음과 같은 매개변수 선언을 보았을 때,

```java
public static void outBox(Box<? super Toy> box) {
    /* 이 안에서는 box가 참조하는 인스턴스에서
             Toy 인스턴스를 꺼내는(반환하는) 메소드 호출은 불가능하다. */
}
```

다음과 같은 판단을 할 수 있어야 한다.

> "`box`가 참조하는 인스턴스를 대상으로 꺼내는 기능의 메소드 호출은 불가능하다."

실제 문제를 일으키는 부분은 메소드 호출 자체가 아닌, 매개변수의 반환형 선언이지만 이렇게 정리해 두는 것이 여러모로 도움이 된다.

#### 참조변수를 Object형으로 선언한다면?

앞서 설명한 내용과 관련하여 다음과 같이 참조변수 `myToy`를 `Object`형으로 선언하면 컴파일이 되지 않으냐고 질문할 수 있다.

```java
public static void inBox(Box<? super Toy> box, Toy n) {
    Object myToy = box.get();
}
```

위의 상황에서 `get` 메소드의 반환형을 결정할 수 없기 때문에 컴파일러는 `get`의 반환형을 `Object`로 결정해버린다. 그래서 위의 메소드 정의는 컴파일 된다. 그러나 자바는 `Object`형 참조변수의 선언이나 `Object`형으로의 형 변환이 불필요하도록 문법을 개선시켜왔다. **`Object`라는 이름이 코드에 직접 등장하는 것은 컴파일러를 통한 오류의 발견 가능성을 낮추는 행위이기 때문이다.** 그러니 지금 설명하는 부분에서 참조변수를 `Object`형으로 선언하는 것은 논외로 해야 한다. 동시에 당연히 피해야 할 일이기도 하다.

#### 상한과 하한 제한을 모두 적용한 결과

그럼 마지막으로 앞서 보인 예제를 '필요한 만큼만 기능을 허용하여, 코드의 오류가 컴파일 과정에서 최대한 발견되도록' 수준을 높인 결과를 보이겠다.

```java
class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        Toy t = box.get();    // 상자에서 꺼내기
        System.out.println(t);
    }
    public static void inBox(Box<? super Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```

실행 결과

```
I am a Toy
```

### 언제 와일드카드에 제한을 걸어야 하는가? : 정리하기

와일드카드의 상한과 하한 제한이 필요한 이유의 본질은 그 자체로 이해하기 난해한 부분이 있다. 그러니 한번 이해하고 나면 다음과 같이 `Box<T>`를 대상으로 정리를 해 두는 것이 좋다.

```
매개변수 선언: Box<? extends Toy> box
    → box가 참조하는 인스턴스를 대상으로 꺼내는 작업만 허용하겠다는 의미

매개변수 선언: Box<? super Toy> box
    → box가 참조하는 인스턴스를 대상으로 넣는 작업만 허용하겠다는 의미
```

### 상자의 내용물 옮기기 — 예제 `P531_MoveBoxContents`

예제를 하나 더 제시하겠다. 이 예제를 통해서 와일드카드의 상한과 하한 제한을 더 확실히 이해할 수 있기를 바라겠다. 다음은 상자에 담긴 내용물을 다른 상자로 옮기는 기능의 메소드를 정의하고 활용하는 예제이다. 단순히 문법 관점에서 본다면 상자의 내용물을 복사하는 예제로도 볼 수 있다.

```java
class BoxContentsMover {
    // from에 저장된 내용물을 to로 이동
    public static void moveBox(Box<? super Toy> to, Box<? extends Toy> from) {
        to.set(from.get());
    }
}
```

실행 결과

```
I am a Toy
```

그런데 위와 같은 유형의 메소드를 정의하는 경우 프로그래머도 실수할 수 있다. `from`에서 `to`로 옮겨야 하는데, 다음과 같이 `to`에서 `from`으로 옮기는 코드를 작성할 수 있는 일이다.

```java
from.set(to.get());    // 프로그래머의 실수! 그러나 컴파일 오류로 이어진다.
```

그러나 매개변수의 선언에서 와일드카드에 적절히 상한과 하한 제한을 걸어 두었기 때문에 위와 같은 실수는 컴파일 과정에서 드러난다.

> 💡 **개발 팁 — PECS: Producer-Extends, Consumer-Super**
> 지금까지의 상한·하한 제한에는 이름이 붙어 있다. 매개변수를 **역할**로 먼저 나눈 뒤 와일드카드를 고르는 것이다. 값을 꺼내 쓰기만 하는 쪽은 **생산자(Producer)** 이므로 `? extends T`, 값을 담기만 하는 쪽은 **소비자(Consumer)** 이므로 `? super T`를 쓴다. `moveBox`의 `from`이 생산자, `to`가 소비자인 것이 정확히 이 패턴이다. 이것이 이론에 그치지 않는다는 증거는 표준 라이브러리에 있다 — 앞서 본 `Collections.copy(List<? super T> dest, List<? extends T> src)`가 같은 규칙으로 선언되어 있다. 와일드카드를 "쓸 수 있으면 쓰는 것"이 아니라 **"매개변수마다 읽기 전용인지 쓰기 전용인지를 선언하는 수단"** 으로 보면, 인자 순서를 헷갈리는 것 같은 실수를 실행 시점이 아닌 컴파일 시점에 잡을 수 있다.

### 제한된 와일드카드 선언을 갖는 제네릭 메소드 — 예제 `P535_BoundedWildcardGenericMethod`

앞서 `Toy` 클래스를 담은 상자를 기준으로 다음과 같이 `inBox`와 `outBox` 메소드를 정의하였다.

```java
class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        Toy t = box.get();    // 상자에서 꺼내기
        System.out.println(t);
    }
    public static void inBox(Box<? super Toy> box, Toy n) {
        box.set(n);    // 상자에 넣기
    }
}
```

위의 두 메소드는 `Box<Toy>` 인스턴스를 대상으로 정의된 메소드이다. 이 상황에서 다음 클래스를 정의했다고 가정해보자.

```java
class Robot {
    @Override
    public String toString() { return "I am a Robot"; }
}
```

그리고 `Box<Robot>`의 인스턴스를 대상으로 `outBox`와 `inBox` 메소드를 호출하고 싶다고 가정하자. 그렇다면 다음과 같이 오버로딩을 하여 메소드를 정의하는 방법을 고려할 수 있다.

```java
class BoxHandler {
    // 다음 두 메소드는 오버로딩 인정 안됨.
    public static void outBox(Box<? extends Toy> box) {...}
    public static void outBox(Box<? extends Robot> box) {...}

    // 다음 두 메소드는 두 번째 매개변수로 인해 오버로딩 인정 됨.
    public static void inBox(Box<? super Toy> box, Toy n) {...}
    public static void inBox(Box<? super Robot> box, Robot n) {...}
}
```

그런데 위 클래스의 다음 두 메소드 정의는 오버로딩이 성립하지 않는다.

```java
public static void outBox(Box<? extends Toy> box) {...}
public static void outBox(Box<? extends Robot> box) {...}
    → 컴파일러는 두 메소드의 오버로딩을 인정하지 않는다.
```

그 이유는 기술적인 문제에 기인하는데 조금만 설명하면, 자바는 제네릭 등장 이전에 정의된 클래스들과의 상호 호환성 유지를 위해 컴파일 시 제네릭과 와일드카드 관련 정보를 지우는 과정을 거친다. 즉 위의 두 매개변수 선언은 컴파일 과정에서 다음과 같이 수정이 되고, 이로 인해 메소드의 오버로딩이 성립 불가능한 상태가 된다.

```
Box<? extends Toy> box    →    Box box
Box<? extends Robot> box  →    Box box
```

위와 같이 컴파일러가 제네릭 정보를 지우는 행위를 가리켜 'Type Erasure'라 한다. 따라서 위와 같이 오버로딩을 하고 컴파일 하면 다음 메시지가 포함된 에러 메시지가 출력된다.

```
name clash:
outBox(Box<? extends Robot>) and outBox(Box<? extends Toy>) have the same erasure
```

위의 내용을 조금 과장해서(생략 및 축소된 내용을 포함해서) 의역하면 이렇다.

```
이름 충돌:
outBox(Box<? extends Robot>)와 outBox(Box<? extends Toy>)은 'Type Erasure'에 의해 매개변수 정보가 같아집니다.
```

반면 `BoxHandler` 클래스에 정의된 다음 두 메소드는 오버로딩이 인정된다. 이유는 제네릭과 관련 없는 두 번째 매개변수의 자료형이 다르기 때문이다.

```java
public static void inBox(Box<? super Toy> box, Toy n) {...}
public static void inBox(Box<? super Robot> box, Robot n) {...}
    → 두 번째 매개변수의 자료형이 다르므로 오버로딩이 인정된다.
```

다시 본론으로 돌아와서 `Box<Toy>` 인스턴스와 `Box<Robot>` 인스턴스를 동시에 허용할 수 있도록 `inBox`와 `outBox` 메소드를 정의하려면 어떻게 해야 할까? 답은 다음 예제에서 보이듯이 '제네릭 메소드'에 있다.

```java
class BoxHandler {
    public static <T> void outBox(Box<? extends T> box) {
        T t = box.get();
        System.out.println(t);
    }

    public static <T> void inBox(Box<? super T> box, T n) {
        box.set(n);
    }
}
```

실행 결과

```
I am a Toy
I am a Robot
```

위 예제의 결론은 이렇다. 다음과 같이 메소드를 오버로딩 해야 하는 상황에서는 'Type Erasure'라는 것 때문에 오버로딩으로 인정이 되지 않으니,

```java
public static void outBox(Box<? extends Toy> box) {...}
public static void outBox(Box<? extends Robot> box) {...}
```

다음과 같은 제네릭 메소드의 정의로 이를 대신하자는 것이다.

```java
public static <T> void outBox(Box<? extends T> box) {...}
```

그리고 이후에 `<? extends T>` 선언을 볼 일이 있을 텐데, 그때는 지금 설명한 위 예제의 상황을 떠올려 이 선언이 의미하는 바를 이해하길 바란다.

> 💡 **개발 팁 — Type Erasure는 왜 존재하나**
> 제네릭은 자바 5부터 도입됐지만, 그 이전(자바 4 이하)에 컴파일된 `.class` 파일들과 계속 호환돼야 했다. 그래서 컴파일러는 제네릭 타입 정보를 소스 레벨에서만 검사하고, 바이트코드에는 지운(erase) 채로 남긴다 — 이게 **하위 호환성(backward compatibility)** 을 위한 설계 선택이다. 대가도 있다: 런타임에는 제네릭 타입 정보가 없어서 `new T[]`나 `instanceof List<String>` 같은 코드가 막히고, 지금 본 것처럼 `Box<? extends Toy>`와 `Box<? extends Robot>`도 바이트코드 레벨에선 똑같은 `Box`로 취급돼 오버로딩 충돌이 난다. "왜 제네릭은 런타임에 타입을 모르지?"라는 의문이 들 때마다 이 Type Erasure를 떠올리면 된다.

### 제네릭 인터페이스의 정의와 구현 — 예제 `P538_GetableGenericInterface`, `P539_GetableGenericInterface2`

지금까지 클래스 또는 메소드만 제네릭으로 정의하였지만 인터페이스 역시 클래스와 마찬가지로 제네릭으로 정의할 수 있다. 즉 다음과 같은 형태의 제네릭 인터페이스를 정의할 수 있다.

```java
interface Getable<T> {
    public T get();
}

// 인터페이스 Getable<T>를 구현하는 Box<T> 클래스
class Box<T> implements Getable<T> {
    private T ob;
    public void set(T o) { ob = o; }

    @Override
    public T get() {
        return ob;
    }
}
```

실행 결과

```
I am a Toy
```

위 예제의 `Box<T>` 클래스는 다음과 같이 `Getable<T>` 인터페이스를 구현하는 형태로 정의되었다.

```java
class Box<T> implements Getable<T> {...}
```

따라서 `Getable<T>`형 참조변수로 `Box<T>`의 인스턴스를 참조할 수 있다. 단 T를 대신할 자료형이 다음 문장과 같이 동일해야 참조가 가능하다.

```java
public static void main(String[] args) {
    Box<Toy> box = new Box<>();
    ....
    Getable<Toy> gt = box;
    ....
}
```

그리고 제네릭 인터페이스를 구현할 때에는 다음과 같이 T를 결정한 상태로 구현할 수도 있다.

```java
class Box<T> implements Getable<String> {...}
```

단 이렇듯 제네릭 인터페이스의 T를 `String`으로 결정하면 `Getable<T>`의 메소드를 구현할 때에도 다음과 같이 T가 아닌 `String`으로 명시하고 구현해야 한다.

```java
@Override
public String get() {....}
```

그럼 이와 관련하여 다음 예제를 보자.

```java
class Box<T> implements Getable<String> {
    private T ob;
    public void set(T o) { ob = o; }

    @Override
    public String get() {    // 반환형은 T가 아닌 String이어야 한다.
        return ob.toString();
    }
}
```

실행 결과

```
I am a Toy
```

따라서 `Getable<String>`형 참조변수는 다음과 같이 `Box<T>` 인스턴스를 T의 자료형에 상관없이 참조할 수 있다.

```java
public static void main(String[] args) {
    Box<Toy> box = new Box<>();
    ....
    Getable<String> gt = box;
    ....
}
```

> 💡 **개발 팁 — 제네릭 인터페이스는 JDK 곳곳에 있다**
> `Getable<T>`처럼 "타입을 인자로 받는 인터페이스"는 낯설어 보여도 사실 표준 라이브러리 전반에 깔려 있는 패턴이다. 정렬 기준을 정의하는 `Comparable<T>`, `for-each`를 가능하게 하는 `Iterable<T>`, 스레드 작업 결과를 반환하는 `Callable<T>`가 전부 제네릭 인터페이스다. `Box<T> implements Getable<T>`처럼 구현체가 T를 그대로 넘기면 어떤 타입이든 유연하게 담을 수 있고, `implements Getable<String>`처럼 T를 고정하면 그 구현체는 오직 한 가지 용도로만 특화된다 — 이 둘 중 어느 쪽을 고를지가 곧 클래스 설계의 유연성과 특화 사이의 트레이드오프다.

---

이렇게 해서 제네릭에 대한 다소 길었던 설명을 일단 마쳤는데 내용이 비교적 많았다. 그러나 중간에 끊을 수 있는 부분도 없었고 그냥 넘어가도 될 만한 내용도 없었다. 따라서 다음 Chapter로 넘어가기 전에 충분히 복습할 것을 권하고 싶다.
