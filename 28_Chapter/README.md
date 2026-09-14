# Chapter 28 — 메소드 참조와 Optional

## 메소드 참조

- 람다식이 이미 정의된 메소드를 단순히 호출하는 역할만 한다면, 람다식 대신 **메소드 참조**를 사용할 수 있다.
- 메소드 참조는 `::` 연산자를 사용하며, 람다식의 인자 전달과 반환에 관한 정보 일부를 생략한다.
- 메소드 참조의 네 가지 유형은 static 메소드 참조, 참조변수를 통한 인스턴스 메소드 참조, 클래스 이름을 통한 인스턴스 메소드 참조, 생성자 참조이다.

### static 메소드 참조 — 예제 `P693_ArrangeList`, `P694_ArrangeList2`

- `Collections.reverse`처럼 static 메소드를 참조한다.
- 형식은 `ClassName::staticMethodName`이다.

```java
Consumer<List<Integer>> c = l -> Collections.reverse(l);
Consumer<List<Integer>> c = Collections::reverse;
```

> 💡 **개발 팁 — 의도가 사라지지 않을 때만 축약하기**  
> 메소드 참조는 이미 존재하는 메소드에 단순 위임할 때 읽기 좋다. 람다식 안에 추가 처리나 조건이 있다면 억지로 메소드 참조로 바꾸지 않는 편이 의도를 더 잘 드러낸다.

### 참조변수를 통한 인스턴스 메소드 참조 — 예제 `P695_ArrangeList3`, `P698_ArrangeList4`

- 이미 존재하는 인스턴스의 메소드를 참조한다.
- 람다식 안에서 접근하는 지역 변수는 `final`이거나 **effectively final**이어야 한다.
- 형식은 `referenceName::instanceMethodName`이다.

```java
Consumer<List<Integer>> c = e -> js.sort(e);
Consumer<List<Integer>> c = js::sort;
```

### 클래스 이름을 통한 인스턴스 메소드 참조 — 예제 `P700_NoObjectMethodRef`, `P702_YesObjectMethodRef`

- 호출 시 전달되는 첫 번째 인자가 메소드를 호출할 인스턴스가 되는 경우에 사용한다.
- 형식은 `ClassName::instanceMethodName`이다.

```java
ToIntBiFunction<IBox, IBox> bf = (b1, b2) -> b1.larger(b2);
ToIntBiFunction<IBox, IBox> bf = IBox::larger;
```

### 생성자 참조 — 예제 `P703_StringMake`, `P704_StringMakeMethodRef`

- 람다식이 인스턴스를 생성한 뒤 그 참조 값을 반환하는 역할만 한다면 생성자 참조로 바꿀 수 있다.
- 형식은 `ClassName::new`이다.

```java
Function<char[], String> f = ar -> new String(ar);
Function<char[], String> f = String::new;
```

## Optional<T> 클래스

| 구분 | 전체 선언 | 설명 |
| --- | --- | --- |
| 생성 | `public static <T> Optional<T> empty()` | 내용물이 없는 빈 `Optional`을 만든다. |
| 생성 | `public static <T> Optional<T> of(T value)` | `null`이 아닌 `value`를 감싼다. |
| 생성 | `public static <T> Optional<T> ofNullable(T value)` | `value`가 `null`이면 빈 `Optional`을 만든다. |
| 존재 확인 | `public boolean isPresent()` | 값이 있으면 `true`, 없으면 `false`를 반환한다. |
| 값 꺼내기 | `public T get()` | 저장된 값을 반환한다. |
| 값이 있을 때 실행 | `public void ifPresent(Consumer<? super T> action)` | 값이 있을 때만 `action`을 실행한다. |
| 변환 | `public <U> Optional<U> map(Function<? super T, ? extends U> mapper)` | 값을 변환한 결과를 `Optional`에 담아 반환한다. |
| 중첩 없는 변환 | `public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)` | `Optional`을 반환하는 변환 결과를 바로 연결한다. |
| 기본값 | `public T orElse(T other)` | 값이 없을 때 `other`를 반환한다. |

- 객체 사이의 연결에서 값이 없을 수 있으면 `null` 검사와 `if ~ else`가 반복되고, 빠뜨리면 `NullPointerException`이 발생할 수 있다.
- `Optional<T>`는 값을 감싸는 래퍼 클래스이며, 값의 존재 여부와 빈 경우를 함께 처리하도록 돕는다.

### Optional 생성과 값 확인 — 예제 `P710_StringOptional1`, `P711_StringOptional2`

- `of`는 `null`이 아닌 값을 감싼 `Optional`을 만든다. `null`을 전달하면 `NullPointerException`이 발생한다.
- `ofNullable`은 `null`도 받을 수 있으며, 이때 빈 `Optional`을 만든다.
- `isPresent`로 값의 존재 여부를 확인하고, `get`으로 저장된 값을 꺼낼 수 있다.
- `ifPresent`는 값이 있을 때만 전달한 `Consumer`를 실행하므로 `if`문을 줄일 수 있다.

```java
public static <T> Optional<T> of(T value)
public static <T> Optional<T> ofNullable(T value)
public boolean isPresent()
public T get()
public void ifPresent(Consumer<? super T> action)
```

### map과 orElse — 예제 `P714_OptionalMap`, `P717_OptionalOrElse`

- `map`은 값이 있을 때 변환 함수를 적용하고, 그 결과를 다시 `Optional`에 담아 반환한다. 빈 `Optional`에 호출하면 빈 `Optional`을 반환한다.
- 반환된 `Optional`에 다시 `map`을 호출하여 변환을 연결할 수 있다.
- `orElse`는 값이 있으면 그 값을, 비어 있으면 인자로 전달한 대체 값을 반환한다.
- `empty`는 내용물이 없는 `Optional`을 생성한다.

```java
public static <T> Optional<T> empty()
public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
public T orElse(T other)
```

> 💡 **개발 팁 — 빈 경우를 흐름에 포함하기**  
> 값이 없을 수 있는 상황에서는 `map(...).orElse(...)`처럼 정상 값과 대체 값을 한 흐름으로 표현하면, 여러 단계의 `null` 검사보다 누락하기 어려운 코드를 만들 수 있다.

### flatMap

- `map`은 변환 결과를 `Optional`로 감싸서 반환한다.
- 따라서 변환 대상이 이미 `Optional`을 반환한다면 `map`의 결과가 중첩될 수 있다.
- `flatMap`은 변환 함수가 반환한 `Optional`을 그대로 이어 주므로, `Optional`을 멤버로 두는 경우에 적합하다.

```java
public <U> Optional<U> flatMap(
    Function<? super T, ? extends Optional<? extends U>> mapper)
```

## OptionalInt, OptionalLong, OptionalDouble

- `OptionalInt`, `OptionalLong`, `OptionalDouble`은 각각 `int`, `long`, `double`을 대상으로 하는 Optional 클래스다.
- `Optional<T>`에서 타입 매개변수 `T`를 각 기본형으로 구체화한 것과 유사하다.
- `of`, `empty`, `ifPresent`, `orElse` 등의 사용 방식은 `Optional<T>`와 유사하다.
- 이 클래스들에는 `map`과 `flatMap` 메소드가 정의되어 있지 않다.

## 정리

- 메소드 참조는 람다식이 기존 메소드를 단순 호출할 때 사용할 수 있다.
- `Optional<T>`는 값의 존재 여부와 빈 경우를 함께 표현한다.
- `map`은 값을 변환해 `Optional`로 감싸고, `flatMap`은 `Optional`을 반환하는 변환 결과를 바로 연결한다.
- `orElse`는 빈 `Optional`일 때 사용할 대체 값을 제공한다.
