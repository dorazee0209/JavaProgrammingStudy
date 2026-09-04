# Chapter 27 — 람다 표현식

자바로 작성된 코드에서 람다식을 흔히 접할 수 있는 시대가 되었다. 따라서 람다에 익숙해져야 한다. 좋던 싫던 자바에 있어서 람다는 선택이 아닌 필수가 되었다.

## 27-1. 람다와 함수형 인터페이스

Chapter 26의 마지막 부분에서 람다를 이미 소개하였다. 따라서 그 뒤를 이어 람다에 대한 설명을 계속 이어가겠다.

### 인스턴스보다 기능 하나가 필요한 상황을 위한 람다 — 예제 `P664_SLenComparator`

자바는 객체지향 언어이다. 그리고 코드 흐름의 대부분에 클래스와 인스턴스가 존재한다. 그런데 프로그램을 작성하다 보면 다음의 상황을 자주 접하게 된다.

> "기능 하나를 정의해서 전달해야 하는 상황"

`Comparator<T>` 인터페이스의 구현이 필요한 상황을 그 예로 들 수 있다.

```java
class SLenComp implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

class SLenComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Robot");
        list.add("Lambda");
        list.add("Box");

        Collections.sort(list, new SLenComp());   // 정렬

        for(String s : list)
            System.out.println(s);
    }
}
```

실행 결과

```
Box
Robot
Lambda
```

위 예제에서 `Collections.sort` 메소드를 호출하면서 두 번째 인자로 정렬의 기준을 갖고 있는 인스턴스를 생성해서 전달하고 있다. 인스턴스를 전달하는 형태이지만 내용을 보면 메소드, 즉 기능을 전달하는 것에 해당한다.

> 💡 **개발 팁 — "기능 하나"를 넘기려고 클래스를 만드는 비용**
> `SLenComp`은 정렬 기준 한 줄을 넘기기 위해 존재하는 클래스다. 이름을 짓고, 인터페이스를 구현하고, 파일 어딘가에 정의해 두어야 한다. 문제는 비용이 아니라 **읽는 사람의 동선**이다 — 정렬 기준이 무엇인지 확인하려면 호출부를 떠나 클래스 정의까지 찾아가야 한다. "무엇을 할지"를 인자로 넘기는 설계 자체는 전략 패턴(Strategy Pattern)으로 이미 검증된 좋은 구조이지만, 그 전략을 표현하는 문법이 클래스뿐이라는 게 걸림돌이었다. 람다는 이 구조를 버리는 게 아니라 **같은 구조를 호출부에서 바로 읽히게** 만든다.

### 매개변수가 있고 반환하지 않는 람다식 — 예제 `P666_OneParamNoReturn`

다양한 람다식에 익숙해질 수 있도록 매개변수와 반환형의 유무에 따른 람다식의 표현을 보이고자 한다. 먼저 '매개변수가 하나이고 반환하지 않는 람다식'이다.

```java
interface Printable {
    void print(String s);   // 매개변수 하나, 반환형 void
}

class OneParamNoReturn {
    public static void main(String[] args) {
        Printable p;
        p = (String s) -> { System.out.println(s); };   // 줄임 없는 표현
        p.print("Lambda exp one.");

        p = (String s) -> System.out.println(s);   // 중괄호 생략
        p.print("Lambda exp two.");

        p = (s) -> System.out.println(s);   // 매개변수 형 생략
        p.print("Lambda exp three.");

        p = s -> System.out.println(s);   // 매개변수 소괄호 생략
        p.print("Lambda exp four.");
    }
}
```

실행 결과

```
Lambda exp one.
Lambda exp two.
Lambda exp three.
Lambda exp four.
```

#### 줄여 쓰는 순서

예제에서 보이듯이 줄임이 없는 람다식은 다음과 같다. 매개변수 정보에 소괄호를 하고 메소드 몸체에 중괄호를 한다.

```java
(String s) -> { System.out.println(s); }
```

그러나 메소드의 몸체가 하나의 문장으로 이뤄져 있다면 다음과 같이 중괄호의 생략이 가능하다.

```java
(String s) -> System.out.println(s)
```

단 중괄호를 생략할 때 해당 문장의 끝에 위치한 세미콜론도 함께 지워야 한다. (메소드의 몸체를 이루는 하나의 문장이 `return`문이라면 중괄호의 생략은 불가능한데, 이에 대해서는 아래 *매개변수가 있고 반환하는 람다식* 에서 다룬다.)

그리고 매개변수 정보에 있어서 `s`가 `String`형임은 컴파일러 입장에서 유추가 가능하다. 해당 람다식이 채우게 될 메소드 정보를 통해서 유추가 가능하기 때문이다. 따라서 매개변수의 자료형 정보도 생략 가능하다.

```java
(s) -> System.out.println(s)
```

그리고 매개변수가 위와 같이 하나일 경우에는 소괄호도 생략할 수 있다.

```java
s -> System.out.println(s)
```

물론 메소드 몸체가 둘 이상의 문장으로 이뤄져 있거나, 매개변수의 수가 둘 이상인 경우에는 각각 중괄호와 소괄호의 생략이 불가능하다.

### 매개변수가 둘인 람다식 — 예제 `P667_TwoParamNoReturn`

```java
interface Calculate {
    void cal(int a, int b);   // 매개변수 둘, 반환형 void
}

class TwoParamNoReturn {
    public static void main(String[] args) {
        Calculate c;
        c = (a, b) -> System.out.println(a + b);
        c.cal(4, 3);   // 이번엔 덧셈이 진행

        c = (a, b) -> System.out.println(a - b);
        c.cal(4, 3);   // 이번엔 뺄셈이 진행

        c = (a, b) -> System.out.println(a * b);
        c.cal(4, 3);   // 이번엔 곱셈이 진행
    }
}
```

실행 결과

```
7
1
12
```

### 매개변수가 있고 반환하는 람다식 — 예제 `P668_TwoParamAndReturn`

값을 반환하는 메소드를 구현하는 람다식의 예이다.

```java
interface Calculate {
    int cal(int a, int b);   // 값을 반환하는 추상 메소드
}

class TwoParamAndReturn {
    public static void main(String[] args) {
        Calculate c;
        c = (a, b) -> { return a + b; };
        System.out.println(c.cal(4, 3));

        c = (a, b) -> a + b;
        System.out.println(c.cal(4, 3));
    }
}
```

실행 결과

```
7
7
```

위 예제에서 등장한 람다식은 다음과 같다.

```java
(a, b) -> { return a + b; }
```

이렇듯 메소드 몸체에 해당하는 내용이 `return`문이면 그 문장이 하나이더라도 중괄호의 생략이 불가능하다. 그러나 위의 람다식은 다음 람다식으로 대신할 수 있다.

```java
(a, b) -> a + b
```

이 경우 메소드 몸체에 연산이 등장하는데, 이 연산이 진행되면 그 결과로 값이 남게 된다. 그러면 이 값은 별도로 명시하지 않아도 반환의 대상이 된다. 따라서 `return`문이 메소드 몸체를 이루는 유일한 문장이면 위와 같이 작성할 수 있다. 그리고 이것이 보편적인 방식이다.

### 메소드 호출문의 반환값 — 예제 `P669_OneParamAndReturn`

```java
interface HowLong {
    int len(String s);   // 값을 반환하는 메소드
}

class OneParamAndReturn {
    public static void main(String[] args) {
        HowLong hl = s -> s.length();
        System.out.println(hl.len("I am so happy"));
    }
}
```

실행 결과

```
13
```

위 예제에 등장한 람다식은 다음과 같다.

```java
s -> s.length()
```

메소드 몸체를 이루는 유일한 문장이 메소드 호출문인데, 이 문장에서 호출하는 `length`는 값을 반환한다. 따라서 메소드의 호출 결과로 반환된 값이 남는다. 그리고 이렇게 반환된 값 역시 별도로 명시하지 않아도 반환의 대상이 된다. 따라서 다음과 같이 람다식을 작성할 필요가 없다.

```java
s -> { return s.length(); }
```

이와 같이 작성하면 복잡해 보이기만 할 뿐이다.

### 매개변수가 없는 람다식 — 예제 `P670_NoParamAndReturn`

매개변수가 없는 람다식은 매개변수를 표현하는 소괄호 안을 비우면 된다.

```java
interface Generator {
    int rand();   // 매개변수 없는 메소드
}

class NoParamAndReturn {
    public static void main(String[] args) {
        Generator gen = () -> {
            Random rand = new Random();
            return rand.nextInt(50);
        };

        System.out.println(gen.rand());
    }
}
```

실행 결과

```
49
```

이 예제에서 등장한 람다식은 다음과 같다.

```java
Generator gen = () -> {
    Random rand = new Random();
    return rand.nextInt(50);
};
```

매개변수 선언이 없는 관계로 매개변수 정보를 담는 소괄호가 비어 있다. 그리고 이렇듯 둘 이상의 문장으로 이뤄진 람다식은 중괄호로 반드시 감싸야 하며, 값을 반환할 때에도 `return`문을 반드시 사용해야 한다.

### 함수형 인터페이스(Functional Interfaces)와 어노테이션

앞서 보인 람다식 관련 예제에는 다음의 공통점이 하나 있다.

> "예제에 정의되어 있는 인터페이스에는 추상 메소드가 딱 하나만 존재한다."

이러한 인터페이스를 가리켜 '함수형 인터페이스(Functional Interfaces)'라 한다. 그리고 람다식은 이러한 함수형 인터페이스를 기반으로만 작성이 될 수 있다. 다음은 함수형 인터페이스와 그와 관련된 어노테이션 선언이 붙어 있는 인터페이스의 예이다.

```java
@FunctionalInterface
interface Calculate {
    int cal(int a, int b);
}
```

`@FunctionalInterface`은 함수형 인터페이스에 부합하는지를 확인하기 위한 어노테이션 타입이다. 위의 인터페이스에 둘 이상의 추상 메소드가 존재하면, 이는 함수형 인터페이스가 아니기 때문에 컴파일 오류로 이어진다.

그러나 `static`, `default` 선언이 붙은 메소드의 정의는 함수형 인터페이스의 정의에 아무런 영향을 미치지 않는다. 따라서 다음 인터페이스도 함수형 인터페이스이다.

```java
@FunctionalInterface
interface Calculate {
    int cal(int a, int b);
    default int add(int a, int b) { return a + b; }
    static int sub(int a, int b) { return a - b; }
}
```

위의 인터페이스를 대상으로도 람다식을 구성할 수 있다. 어차피 채워야 할 메소드는 하나이기 때문이다.

> 💡 **개발 팁 — 어노테이션은 주석이 아니라 컴파일러와 맺는 계약이다**
> `@FunctionalInterface`를 붙이지 않아도 추상 메소드가 하나뿐이면 람다식은 동작한다. 그럼에도 붙이는 이유는 **의도를 코드에 못 박아 두기 위해서**다. 나중에 누군가 이 인터페이스에 추상 메소드를 하나 더 추가하면, 이 인터페이스를 쓰던 모든 람다식이 한꺼번에 깨진다 — 어노테이션이 없으면 그 사실을 람다식 사용처에서 뒤늦게 알게 되지만, 붙여 두면 **인터페이스를 고치는 그 순간 컴파일 오류로 막힌다**. `@Override`와 정확히 같은 계열의 장치다: 실행 결과를 바꾸지 않으면서, 잘못된 변경이 배포까지 흘러가는 것을 컴파일 타임에 차단한다.

### 람다식과 제네릭 — 예제 `P672_LambdaGeneric`

인터페이스는 제네릭으로 정의하는 것이 가능하다. 그리고 이는 자바 8에 들어와서 매우 흔한 일이 되었다.

```java
@FunctionalInterface
interface Calculate <T> {   // 제네릭 기반의 함수형 인터페이스
    T cal(T a, T b);
}

class LambdaGeneric {
    public static void main(String[] args) {
        Calculate<Integer> ci = (a, b) -> a + b;
        System.out.println(ci.cal(4, 3));

        Calculate<Double> cd = (a, b) -> a + b;
        System.out.println(cd.cal(4.32, 3.45));
    }
}
```

실행 결과

```
7
7.7700000000000005
```

위 예제에서 인터페이스가 제네릭으로 정의되었으므로, 다음과 같이 참조변수의 형을 지정해서 문장을 구성해야 한다.

```java
Calculate<Integer> ci = (a, b) -> a + b;
Calculate<Double> cd = (a, b) -> a + b;
```

위의 두 문장에 있는 람다식은 동일하다. 그러나 참조변수의 자료형이 다른 관계로 이 둘은 전혀 다른 인스턴스의 생성으로 이어진다. 하나는 정수형 덧셈을 하는 인스턴스의 생성으로, 다른 하나는 실수형 덧셈을 하는 인스턴스의 생성으로 이어진다.

> 💡 **개발 팁 — 같아 보이는 람다식이 같은 것은 아니다**
> `(a, b) -> a + b` 두 줄은 소스상 완전히 동일하지만, 하나는 `Integer` 덧셈이고 다른 하나는 `Double` 덧셈이다. 람다식은 그 자체로 타입을 갖지 않고, **대입되는 대상(타깃 타입)이 무엇이냐에 따라 의미가 결정되기** 때문이다. 그래서 람다식은 타입 추론이 가능한 자리에만 놓일 수 있고, `var`에 그냥 대입하거나 타입 정보가 없는 곳에 던져 넣으면 컴파일되지 않는다. "코드가 같으니 동작도 같겠지"라는 직관이 통하지 않는 대표적인 지점이다.
