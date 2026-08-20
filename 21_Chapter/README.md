# Chapter 21 — 제네릭(Generics) 1

제네릭은 자바 5에서 처음 소개가 되어 버전 8까지 그 내용이 상당히 추가되었다. 그리고 제네릭은 '자바 컬렉션 프레임워크'의 이해를 위한 필수 개념이기도 하다.

## 21-1. 제네릭의 이해

제네릭이 갖는 의미는 **'일반화'**이다. 그리고 자바에서 그 일반화의 대상은 **자료형**이다. 그럼 제네릭이 존재하지 않던 시절의 코드와 제네릭이 존재하는 시절의 코드 비교에서부터 이야기를 시작하겠다.

### 제네릭 이전의 코드 — 예제 `P480_FruitAndBox`

사과와 오렌지를 담는 상자를 각각 생성하여 그 상자에 사과와 오렌지를 담았다가 꺼내는 과정을 보이는 다음 예제를 관찰하자.

```java
class Apple {   // 사과를 단순히 표현한 클래스
    public String toString() {
        return "I am an apple.";
    }
}

class Orange {   // 오렌지를 단순히 표현한 클래스
    public String toString() {
        return "I am an orange.";
    }
}

class AppleBox {   // 사과 담는 상자를 표현한 클래스
    private Apple ap;

    public void set(Apple a) {   // 사과를 담는다.
        ap = a;
    }
    public Apple get() {   // 사과를 꺼낸다.
        return ap;
    }
}

class OrangeBox {   // 오렌지 담는 상자를 표현한 클래스
    private Orange or;

    public void set(Orange o) {   // 오렌지를 담는다.
        or = o;
    }
    public Orange get() {   // 오렌지를 꺼낸다.
        return or;
    }
}

class FruitAndBox {
    public static void main(String[] args) {
        AppleBox aBox = new AppleBox();     // 사과 상자 생성
        OrangeBox oBox = new OrangeBox();   // 오렌지 상자 생성

        aBox.set(new Apple());     // 사과를 사과 상자에 담는다.
        oBox.set(new Orange());    // 오렌지를 오렌지 상자에 담는다.

        Apple ap = aBox.get();     // 상자에서 사과를 꺼낸다.
        Orange og = oBox.get();    // 상자에서 오렌지를 꺼낸다.

        System.out.println(ap);
        System.out.println(og);
    }
}
```

실행 결과

```
I am an apple.
I am an orange.
```

### 상자 클래스를 하나로 합치기 — 예제 `P482_FruitAndBox2`

위 예제에서 `AppleBox`와 `OrangeBox`가 하는 일은 성격이 같고 내용도 같다. 따라서 이 둘은 다음 클래스 하나로 대체할 수 있다.

```java
class Box {
    private Object ob;   // Object를 상속하는 인스턴스면 무엇이든 담는다.
    public void set(Object o) {
        ob = o;
    }
    public Object get() {
        return ob;
    }
}
```

이제 `Box`는 사과와 오렌지뿐 아니라 **무엇이든 담을 수 있는 상자**가 되었다. 그럼 이 클래스를 예제에 적용해 보겠다.

```java
class FruitAndBox2 {
    public static void main(String[] args) {
        Box aBox = new Box();   // 상자 생성
        Box oBox = new Box();   // 상자 생성

        aBox.set(new Apple());     // 상자에 사과를 담는다.
        oBox.set(new Orange());    // 상자에 오렌지를 담는다.

        Apple ap = (Apple)aBox.get();      // 상자에서 사과를 꺼낸다.
        Orange og = (Orange)oBox.get();    // 상자에서 오렌지를 꺼낸다.

        System.out.println(ap);
        System.out.println(og);
    }
}
```

실행 결과

```
I am an apple.
I am an orange.
```

위 예제에서 주목할 사실은 다음과 같다.

> **"Box 인스턴스에서 내용물을 꺼낼 때 형 변환을 해야 한다."**

`Box` 내에서 인스턴스를 저장하는 참조변수가 `Object`형이기 때문에, 저장된 인스턴스를 꺼낼 때에는 인스턴스에 맞는 형 변환을 해야만 한다. 그리고 이러한 번거로운 과정으로 인해 다음과 같은 실수가 발생할 수도 있다.

### 실수가 실행 중에야 드러난다 — 예제 `P483_FruitAndBoxFault`

```java
class FruitAndBoxFault {
    public static void main(String[] args) {
        Box aBox = new Box();
        Box oBox = new Box();

        // 아래 두 문장에서는 사과와 오렌지가 아닌 '문자열'을 담았다.
        aBox.set("Apple");
        oBox.set("Orange");

        // 상자에 과일이 담기지 않았는데 과일을 꺼내려 한다.
        Apple ap = (Apple)aBox.get();
        Orange og = (Orange)oBox.get();

        System.out.println(ap);
        System.out.println(og);
    }
}
```

실행 결과

```
Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to Apple
        at FruitAndBoxFault.main(FruitAndBoxFault.java:35)
```

위 예제의 다음 두 문장은 `Apple` 인스턴스와 `Orange` 인스턴스를 담으려 한 프로그래머의 의도와 달리 실수로 만들어진 문장이다.

```java
aBox.set("Apple");     // 문자열 "Apple" 담았는데 실수다.
oBox.set("Orange");    // 문자열 "Orange" 담았는데 실수다.
```

그런데 문제는 이러한 실수가 **컴파일 과정에서 발견되지 않았다**는데 있다. 대신 다음 문장을 실행하는 순간 예외가 발생하기는 했다.

```java
Apple ap = (Apple)aBox.get();   // 형 변환 과정에서 예외 발생
```

모든 실수는 컴파일 단계에서 드러나는 것이 좋다. 컴파일 오류는 원인을 바로 찾을 수 있기 때문이다. 그러나 실행 중에 발생하는 예외는 다르다. 예외의 원인은 쉽게 발견되지 않는 경우도 많다. 뿐만 아니라 위와 같은 실수는 **드러나지 않을 수도 있다.** 다음 예제에서 보이듯이 말이다.

### 실수가 아예 드러나지 않는다 — 예제 `P485_FruitAndBoxFault2`

```java
class FruitAndBoxFault2 {
    public static void main(String[] args) {
        Box aBox = new Box();
        Box oBox = new Box();

        // 다음 두 문장은 프로그래머의 실수이다!
        aBox.set("Apple");
        oBox.set("Orange");

        System.out.println(aBox.get());
        System.out.println(oBox.get());
    }
}
```

실행 결과

```
Apple
Orange
```

위 예제는 흔히 하는 말로 **'대형 사고'**로 이어질 수 있다. 사고가 발생했는지 조차 모르고 넘어갈 수 있기 때문이다.

지금까지 제네릭 등장 이전의 자바 코드가 갖는 불편함과 문제점을 설명하였다. **불편함**이라 하면 상자에서 물건을 꺼낼 때 형 변환을 해야 한다는 것이고, **문제점**이라 하면 프로그래머가 실수를 해도 그 실수가 드러나지 않을 수 있다는 것이다.

> 💡 **개발 팁 — 오류는 이르게 드러날수록 싸다**
> 같은 버그라도 **컴파일 타임 → 테스트 → 운영** 순으로 갈수록 고치는 비용이 급격히 커진다. 위 예제가 무서운 이유는 예외가 나서가 아니라, `FruitAndBoxFault2`처럼 **아무 일도 없었던 것처럼 지나가기** 때문이다. 타입 시스템을 활용해 컴파일러가 잡게 만드는 것은 단순한 문법 취향이 아니라 **버그를 가장 싼 단계로 끌어당기는 설계 행위**다.

### 제네릭 기반의 클래스 정의하기

제네릭이 등장하면서 **자료형에 의존적이지 않은 클래스**를 정의할 수 있게 되었다. 그리고 위에서 언급한 불편함과 문제점이 해결되었다. 그럼 이에 대한 이해를 위해 먼저 다음 클래스를 제네릭 기반으로 정의하고, 앞서 소개한 예제들을 제네릭 이후의 코드로 수정해보겠다.

```java
class Box {
    private Object ob;
    public void set(Object o) {
        ob = o;
    }
    public Object get() {
        return ob;
    }
}
```

위의 클래스는 `Object`형 인스턴스를 저장하고 반환한다. 따라서 자료형에 의존적이지 않은 형태로 위의 클래스를 정의하기 위해 `Object`를 `T`로 다음과 같이 대체하자.

```java
class Box {
    private T ob;
    public void set(T o) {
        ob = o;
    }
    public T get() {
        return ob;
    }
}
```

이제 `T`는 **인스턴스를 생성할 때 결정하면 된다.** 사과를 저장할 목적이면 `T`를 `Apple`로 결정하면 되고, 오렌지를 저장할 목적이면 `T`를 `Orange`로 결정하면 된다. 이렇듯 인스턴스 생성 시 `T`의 자료형을 결정하는 것이 '제네릭'이다.

그런데 위의 클래스를 컴파일 하면 오류가 발생한다. 컴파일러가 `T`를 클래스의 이름으로 판단하고 `T`라는 이름의 클래스가 없다는 오류 메시지를 전달한다. 따라서 **'`T`는 인스턴스 생성 시 자료형을 결정하기 위한 표식'임을 알려야 한다.** 방법은 다음과 같이 클래스 이름 뒤에 `<T>`를 붙이는 것이다.

```java
class Box<T> {   // 완성된 제네릭 기반의 클래스 정의
    private T ob;
    public void set(T o) {
        ob = o;
    }
    public T get() {
        return ob;
    }
}
```

이로써 제네릭 기반의 클래스 정의가 완성되었다. 따라서 `T`는 인스턴스 생성 순간에 결정할 수 있게 되었다. 그럼 위의 클래스를 대상으로 인스턴스를 생성하는 다음 문장들을 보자.

```java
Box<Apple> aBox = new Box<Apple>();
```

- `T`를 `Apple`로 결정하여 인스턴스 생성
- 따라서 `Apple` 또는 `Apple`을 상속하는 하위 클래스의 인스턴스 저장 가능

```java
Box<Orange> oBox = new Box<Orange>();
```

- `T`를 `Orange`로 결정하여 인스턴스 생성
- 따라서 `Orange` 또는 `Orange`를 상속하는 하위 클래스의 인스턴스 저장 가능

#### 용어 정리

잠시 용어 정리를 하면, `Box<T>` 클래스에서 사용된 `T`를 가리켜 **'타입 매개변수(Type Parameter)'**라 한다. 메소드의 매개변수와 유사하게 자료형 정보를 인자로 전달받는 형태이기 때문이다. 또한 다음 문장에서 사용된 `Apple`을 가리켜 **'타입 인자(Type Argument)'**라 한다. `Apple`을 타입 매개변수 `T`에 전달되는 인자로 바라보고 그렇게 이름을 지어준 것이다.

```java
Box<Apple> aBox = new Box<Apple>();
```

마지막으로 `Box<Apple>`을 가리켜 **'매개변수화 타입(Parameterized Type)'**이라 한다. 자료형 `Apple`이 타입 매개변수 `T`에 전달되어 `Box<Apple>`이라는 새로운 자료형이 완성된 것이기 때문에 '매개변수화 타입'이라 부른다.

사실 이러한 유형의 용어는 혼동하기 쉽다. 그리고 마음에 들지 않을 수도 있다. 그러나 널리 사용되는 표현 방식이니 익숙해지자.

| 용어 | 해당하는 것 |
|---|---|
| 타입 매개변수 (Type Parameter) | `Box<T>`에서 `T` |
| 타입 인자 (Type Argument) | `Box<Apple>`에서 `Apple` |
| 매개변수화 타입 (Parameterized Type) | `Box<Apple>` |

그리고 '매개변수화 타입'은 **'제네릭 타입(Generic Type)'**이라고도 하니, 이러한 사실도 기억해 두기 바란다.

### 제네릭 이후의 코드 — 예제 `P489_FruitAndBox2_Generic`

제네릭 기반으로 클래스를 정의하였고 또 인스턴스의 생성 방법도 소개하였다. 따라서 이를 기반으로 예제를 작성했을 때 앞서 언급한 다음 불편함과 문제점이 사라짐을 확인할 차례이다.

- 필요시 형 변환을 해야 한다.
- 자료형과 관련된 프로그래머의 실수가 컴파일 과정에서 드러나지 않는다.

먼저 다음 예제를 통해서 **형 변환이 불필요해진 부분**에 대해 확인하겠다. 이는 `FruitAndBox2.java`의 제네릭 버전이다.

```java
class Box<T> {
    private T ob;

    public void set(T o) {
        ob = o;
    }
    public T get() {
        return ob;
    }
}

class FruitAndBox2_Generic {
    public static void main(String[] args) {
        Box<Apple> aBox = new Box<Apple>();      // T를 Apple로 결정
        Box<Orange> oBox = new Box<Orange>();    // T를 Orange로 결정

        aBox.set(new Apple());     // 사과를 상자에 담는다.
        oBox.set(new Orange());    // 오렌지를 상자에 담는다.

        Apple ap = aBox.get();     // 사과를 꺼내는데 형 변환 하지 않는다.
        Orange og = oBox.get();    // 오렌지를 꺼내는데 형 변환 하지 않는다.

        System.out.println(ap);
        System.out.println(og);
    }
}
```

실행 결과

```
I am an apple.
I am an orange.
```

위 예제에서, 다음 두 문장을 통해 인스턴스 생성 시 `Box<T>`의 `T`가 `Apple`과 `Orange`로 각각 결정이 되므로,

```java
Box<Apple> aBox = new Box<Apple>();      // T를 Apple로 결정
Box<Orange> oBox = new Box<Orange>();    // T를 Orange로 결정
```

인스턴스 각각의 `get` 메소드의 반환형도 `Apple`과 `Orange`로 결정이 된다. 따라서 `get` 메소드의 호출문에서 **형 변환을 할 필요가 없게 되었다.**

### 실수가 컴파일 과정에서 드러난다 — 예제 `P490_FruitAndBoxFault_Generic`

이어서 다음 예제를 보자. 이는 앞서 보인 예제 `FruitAndBoxFault.java`의 제네릭 버전으로, 프로그래머의 자료형 관련 실수가 컴파일 과정에서 드러나는 것을 확인할 수 있다.

```java
class FruitAndBoxFault_Generic {
    public static void main(String[] args) {
        Box<Apple> aBox = new Box<Apple>();
        Box<Orange> oBox = new Box<Orange>();

        aBox.set("Apple");     // 프로그래머의 실수
        oBox.set("Orange");    // 프로그래머의 실수

        Apple ap = aBox.get();
        Orange og = oBox.get();

        System.out.println(ap);
        System.out.println(og);
    }
}
```

컴파일 결과

```
FruitAndBoxFault_Generic.java:29: error: incompatible types: String cannot be converted to Apple
        aBox.set("Apple");
                 ^
FruitAndBoxFault_Generic.java:30: error: incompatible types: String cannot be converted to Orange
        oBox.set("Orange");
                 ^
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
2 errors
```

이로써 제네릭에 대한 기본적인 이해를 갖추었다. 이후로 세세한 문법적 내용을 알아가야 하지만 지금 설명한 내용이 본 Chapter에서는 가장 중요하다고 할 수 있다.

## 21-2. 제네릭의 기본 문법

제네릭과 관련된 문법은 내용이 비교적 많은 편이다. 따라서 내용을 둘로 나누어 정리하였다. 먼저 '제네릭의 기본 문법' 편에서는 비교적 기본적인 내용들을 설명한다.

### 다중 매개변수 기반 제네릭 클래스의 정의 — 예제 `P492_MultiTypeParam`

앞서 제네릭 클래스의 정의 방법을 설명하였다. 당시에는 매개변수 `T` 하나에 대한 제네릭 클래스를 정의하였으나, **둘 이상의 타입 매개변수**에 대한 제네릭 클래스도 정의할 수 있다. 이와 관련하여 다음 예제를 보자. 이 예제에서는 칸이 둘로 나뉘어 있는 상자를 표현한 제네릭 클래스를 정의하였다.

```java
class DBox<L, R> {
    private L left;    // 왼쪽 수납 공간
    private R right;   // 오른쪽 수납 공간

    public void set(L o, R r) {
        left = o;
        right = r;
    }

    @Override
    public String toString() {
        return left + " & " + right;
    }
}

class MultiTypeParam {
    public static void main(String[] args) {
        DBox<String, Integer> box = new DBox<String, Integer>();
        box.set("Apple", 25);
        System.out.println(box);
    }
}
```

실행 결과

```
Apple & 25
```

#### 타입 매개변수의 이름

타입 매개변수의 이름은 짓기 나름이다. 그러나 일반적으로 다음 두 가지 규칙을 지켜서 이름을 짓는다.

- 한 문자로 이름을 짓는다.
- 대문자로 이름을 짓는다.

이렇게 이름을 지으면 다른 종류의 이름들과 구분이 된다. 그리고 위 예제와 같이 한 글자로 이름을 짓더라도 **가급적 의미를 두어 이름을 짓는 것이 좋다.** 보편적으로 자주 사용하는 타입 매개변수의 이름과 그 의미는 다음과 같으니 이를 주로 사용하는 것도 괜찮은 선택이다.

| 이름 | 의미 |
|---|---|
| `E` | Element |
| `K` | Key |
| `N` | Number |
| `T` | Type |
| `V` | Value |

### 기본 자료형에 대한 제한 그리고 래퍼 클래스 — 예제 `P494_PrimitivesAndGenerics`

제네릭 클래스에 대하여 `Box<Apple>`과 같이 '매개변수화 타입'을 구성할 때 **기본 자료형의 이름은 '타입 인자'로 쓸 수 없다.** 즉 다음과 같은 문장 구성은 불가능하다.

```java
Box<int> box = new Box<int>();
```

- 타입 인자로 기본 자료형이 올 수 없으므로 컴파일 오류 발생

하지만 기본 자료형에 대한 **래퍼 클래스**가 존재하고, 또 필요한 상황에서 **박싱과 언박싱이 자동으로** 이뤄지기 때문에 다음과 같은 수준의 코드를 작성할 수 있다.

```java
class Box<T> {
    private T ob;

    public void set(T o) {
        ob = o;
    }
    public T get() {
        return ob;
    }
}

class PrimitivesAndGeneric {
    public static void main(String[] args) {
        Box<Integer> iBox = new Box<Integer>();
        iBox.set(125);              // 오토 박싱 진행
        int num = iBox.get();       // 오토 언박싱 진행
        System.out.println(num);
    }
}
```

실행 결과

```
125
```

### 타입 인자의 생략: 다이아몬드(Diamond) 기호

컴파일러는 프로그래머가 작성하는 제네릭 관련 문장에서 **자료형의 이름을 추론하는 능력**을 갖고 있다. 따라서 다음 문장을 대신하여,

```java
Box<Apple> aBox = new Box<Apple>();
```

다음과 같이 쓸 수 있다.

```java
Box<Apple> aBox = new Box<>();
```

이 경우 참조변수의 선언을 통해서 `<>` 안에 `Apple`이 생략되었다고 컴파일러는 판단한다. 쉽게 말해서 **왼쪽을 보고 오른쪽의 빈 공간을 채운다.** 그리고 `<>`을 가리켜 **'다이아몬드(Diamond) 기호'** 또는 '다이아몬드 표시'라 부른다. 이는 비공식적인 표현이긴 하지만 공식적인 표현으로 인식될 만큼 널리 사용되는 표현이다.

### '매개변수화 타입'을 '타입 인자'로 전달하기 — 예제 `P495_BoxInBox`

이번에는 상자를 하나 생성하여 그 안에 문자열을 저장한 다음에 이 상자를 다른 상자에 넣고자 한다. 그리고 이 상자를 한번 더 다른 상자에 넣을 생각이다. 결론적으로 이는 **하나의 문자열을 세 개의 상자로 겹겹이 포장**하는 셈이다.

```java
class Box<T> {
    private T ob;

    public void set(T o) {
        ob = o;
    }
    public T get() {
        return ob;
    }
}

class BoxInBox {
    public static void main(String[] args) {
        Box<String> sBox = new Box<>();
        sBox.set("I am so happy.");

        Box<Box<String>> wBox = new Box<>();
        wBox.set(sBox);

        Box<Box<Box<String>>> zBox = new Box<>();
        zBox.set(wBox);

        System.out.println(zBox.get().get().get());
    }
}
```

실행 결과

```
I am so happy.
```

위 예제를 통해서 `Box<String>`과 같은 '매개변수화 타입'이 다음과 같이 **'타입 인자'로 사용이 될 수 있음**을 말하고자 하였다.

```java
Box<Box<String>> wBox = new Box<>();
```

처음에는 복잡해 보이지만 이 정도 수준의 문장은 이해할 수 있어야 한다. 실제로 자주 등장하고 또 자주 만들어야 하기 때문이다. 그리고 이러한 유형의 문장을 만들다 보면, 다이아몬드 기호를 이용한 타입 정보의 생략 가능함이 정말 다행이라는 생각을 하게 된다.

> 💡 **개발 팁 — 타입이 길어지면 이름을 붙일 때가 됐다는 신호**
> `Box<Box<Box<String>>>`처럼 중첩이 깊어지면 다이아몬드 기호로도 감당이 안 되는 순간이 온다. 실무에서 `Map<String, List<Pair<Long, String>>>` 같은 타입을 마주치면, 그건 **그 타입에 도메인 이름을 붙여야 한다는 신호**로 읽는 것이 좋다. 지역 변수라면 `var`로 우변 추론에 맡길 수 있고, 반복해서 쓰인다면 그 구조 자체를 하나의 클래스로 승격시키는 편이 읽기에도 유지보수에도 낫다.

### 제네릭 클래스의 타입 인자 제한하기

앞서 정의한 `Box<T>`에는 무엇이든 담을 수 있다. `String` 인스턴스를 담고 싶으면 다음과 같이 상자를 생성하면 되고,

```java
Box<String> sBox = new Box<>();
```

`Apple` 인스턴스를 담고 싶으면 다음과 같이 상자를 생성하면 된다.

```java
Box<Apple> sBox = new Box<>();
```

그러나 **상자에도 특성과 용도가 있다.** 따라서 담고 싶은 것을 제한할 수 있어야 한다. (얇고 작은 상자에 수박을 넣을 수 없듯이) 그리고 이때 사용하는 것이 **`extends`**이다. 예를 들어서 `Number` 클래스를 상속하는 클래스의 인스턴스만 담고 싶다면 다음과 같이 클래스를 정의하면 된다.

```java
class Box<T extends Number> {...}
```

- 인스턴스 생성 시 타입 인자로 `Number` 또는 이를 상속하는 클래스만 올 수 있음

그럼 이와 관련하여 다음 예제를 보자.

```java
class Box<T extends Number> {
    private T ob;

    public void set(T o) {
        ob = o;
    }
    public T get() {
        return ob;
    }
}

class BoundedBox {
    public static void main(String[] args) {
        Box<Integer> iBox = new Box<>();    // Integer는 Number를 상속
        iBox.set(24);

        Box<Double> dBox = new Box<>();     // Double은 Number를 상속
        dBox.set(5.97);

        System.out.println(iBox.get());
        System.out.println(dBox.get());
    }
}
```

실행 결과

```
24
5.97
```

#### 제한하면 호출할 수 있는 메소드가 늘어난다

위 예제에서는 제네릭 클래스의 타입 인자를 `Number` 또는 이를 상속하는 하위 클래스로 제한을 하였다. 그리고 이렇게 제한을 하면 **또 다른 특성이 생긴다.** `Box<T>` 클래스에는 다음과 같은 코드를 넣을 수 없다.

```java
class Box<T> {
    private T ob;
    ....
    public int toIntValue() {
        return ob.intValue();   // ERROR!
    }
}
```

참조변수 `ob`가 참조하게 될 것은 인스턴스이다. 하지만 **어떠한 클래스의 인스턴스를 참조하게 될지 알 수 없기 때문에** `ob`를 통해서 호출할 수 있는 메소드는 `Object` 클래스의 메소드로 제한이 된다. 반면 다음과 같이 타입 인자를 제한하면 `Number` 클래스의 `intValue` 메소드를 호출할 수 있다. `ob`가 참조하는 인스턴스는 `intValue` 메소드를 가지고 있음을 **100퍼센트 보장**할 수 있기 때문이다.

```java
class Box<T extends Number> {
    private T ob;
    ....
    public int toIntValue() {
        return ob.intValue();   // OK!
    }
}
```

이렇듯 타입 인자를 제한했을 때 얻게 되는 특성 때문에 타입 인자를 제한하는 경우도 많다.

> 💡 **개발 팁 — 제약은 자유를 뺏는 대신 능력을 준다**
> `<T extends Number>`는 담을 수 있는 타입을 좁히지만, 그 대가로 **컴파일러가 `T`에 대해 아는 것이 많아진다.** 아무 제약이 없는 `T`는 `Object`의 메소드밖에 쓸 수 없다. 이 트레이드오프는 API 설계 전반에 나타난다 — 입력을 넓게 받을수록 내부에서 할 수 있는 일이 줄어든다. 그래서 좋은 API는 "받을 수 있는 최대"가 아니라 **"실제로 필요한 최소"**를 요구하도록 설계한다.

### 제네릭 클래스의 타입 인자를 인터페이스로 제한하기

다음과 같이 타입 인자를 제한할 수 있음을 위에서 설명하였다.

```java
class Box<T extends Number> {...}
```

이와 유사하게 **인터페이스로도** 타입 인자를 제한할 수 있다. 이와 관련하여 다음 예제를 보자.

```java
interface Eatable {
    public String eat();
}

class Apple implements Eatable {
    public String toString() {
        return "I am an apple.";
    }

    @Override
    public String eat() {
        return "It tastes so good!";
    }
}

class Box<T extends Eatable> {
    T ob;

    public void set(T o) {
        ob = o;
    }
    public T get() {
        System.out.println(ob.eat());   // Eatable로 제한하였기에 eat 호출 가능
        return ob;
    }
}

class BoundedInterfaceBox {
    public static void main(String[] args) {
        Box<Apple> box = new Box<>();
        box.set(new Apple());   // 사과 저장

        Apple ap = box.get();   // 사과 꺼내기
        System.out.println(ap);
    }
}
```

실행 결과

```
It tastes so good!
I am an apple.
```

예제에서 보이듯이, 제네릭 클래스의 타입 인자를 다음과 같이 인터페이스의 이름으로 제한할 수 있다. 그리고 **제한할 때에는 클래스와 마찬가지로 `extends`를 사용한다.**

```java
class Box<T extends Eatable> {...}
```

그리고 `Eatable` 인터페이스를 구현하는 클래스로 타입 인자를 제한했기 때문에 다음과 같이 인터페이스에 선언되어 있는 메소드 `eat`의 호출이 가능하게 되었다.

```java
class Box<T extends Eatable> {
    ....
    public T get() {
        System.out.println(ob.eat());   // Eatable로 제한하였기에 eat 호출 가능
        return ob;
    }
}
```

#### 클래스와 인터페이스를 동시에 제한하기

그리고 타입 인자를 제한할 때에는 **하나의 클래스와 하나 이상의 인터페이스에 대해 동시에** 제한을 할 수가 있으며 그 방법은 다음과 같다.

```java
class Box<T extends Number & Eatable> {...}
```

이 경우 `Number`를 상속하면서 동시에 `Eatable` 인터페이스를 구현하는 클래스만이 타입 인자로 올 수 있다.

> 💡 **개발 팁 — 여기서는 `implements`가 아니라 `extends`다**
> 클래스를 구현할 때는 인터페이스에 `implements`를 쓰지만, **타입 인자를 제한할 때는 인터페이스에도 `extends`를 쓴다.** 처음 보면 오타처럼 느껴지는 지점이다. 제네릭의 경계(bound)에서 자바가 묻는 것은 "어떻게 구현했는가"가 아니라 **"이 타입이 저 타입의 하위 타입인가"** 하나뿐이라, 클래스든 인터페이스든 구분할 이유가 없어 `extends`로 통일한 것이다. 그리고 여러 경계를 `&`로 이을 때 **클래스는 반드시 맨 앞에 한 번만** 올 수 있는데, 이는 자바가 클래스 다중 상속을 허용하지 않는 규칙이 제네릭 경계에도 그대로 적용되기 때문이다.
