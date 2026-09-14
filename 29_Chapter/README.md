# Chapter 29 — 스트림 1

자바 8에서 소개된 스트림(Stream)은 처음에는 생소하게 느껴질 수 있다. 그러나 람다식과 함수형 인터페이스에 익숙하다면 쉽게 이해하고 활용할 수 있다. 스트림은 이번 챕터와 다음 챕터, 두 Chapter에 걸쳐 설명한다 — 이번 챕터에서는 스트림을 전반적으로 이해하고, 다음 챕터에서는 이를 바탕으로 다양한 메소드를 다룬다.

## 29-1. 스트림의 이해와 스트림의 생성

### 스트림(Stream)의 이해

속이 빈 긴 파이프의 한쪽 끝으로 물을 흘려보내면 반대쪽 끝으로 물이 흘러나온다. 자바에서도 이와 유사하게 '데이터의 흐름'을 만들 수 있으며, 이를 스트림이라 한다.

> "배열 또는 컬렉션 인스턴스에 저장된 데이터를 꺼내 파이프에 흘려보낸다."

데이터를 흘려보낼 파이프, 즉 연산은 두 종류로 나뉜다.

- **중간 연산(Intermediate Operation)** — 마지막이 아닌 위치에서 진행하는 연산
- **최종 연산(Terminal Operation)** — 마지막에 진행하는 연산

스트림을 생성한 뒤 중간 연산과 최종 연산을 적용하면, 원하는 기준으로 데이터를 필터링하고 가공한 결과를 적은 양의 코드로 얻을 수 있다.

> 💡 **참고 — 파이프는 연산입니다.** '스트림'이라는 개념에서 최종 연산은 마지막에 연결하는 파이프를, 중간 연산은 처음 또는 중간에 연결하는 파이프를 의미한다.

### 스트림(Stream)의 첫 번째 예제 — 예제 `P731_MyFirstStream`

배열에 저장된 데이터를 대상으로 스트림을 생성하고, 생성한 스트림에 두 개의 파이프(연산)를 적용해 결과를 얻는 예제다.

```java
int[] ar = {1, 2, 3, 4, 5};
IntStream stm1 = Arrays.stream(ar);        // 배열 ar로부터 스트림 생성
IntStream stm2 = stm1.filter(n -> n%2 == 1); // 중간 연산 진행
int sum = stm2.sum();                        // 최종 연산 진행
```

- `Arrays.stream(ar)` — 배열 `ar`에 저장된 데이터를 대상으로 스트림을 생성하고, 생성한 스트림을 `stm1`이 참조한다. 스트림은 **데이터의 복사본**이라고 생각하면 된다 — 중간 연산과 최종 연산을 진행하기 좋은 구조로 배치된 복사본이다.
- `stm1.filter(...)` — `stm1`이 참조하는 스트림에 `filter` 연산을 적용한다. 홀수만 통과시키므로 짝수는 걸러지고, 통과한 데이터로 다시 스트림이 형성되어 `stm2`가 참조한다.
- `stm2.sum()` — 홀수로만 이뤄진 `stm2`가 참조하는 스트림에 최종 연산 `sum`을 적용한다. `sum`은 스트림의 데이터를 모두 더한 결과를 반환하는 연산이다.

즉 이 예제는 배열에 저장된 데이터로 스트림을 생성한 뒤, 짝수를 걸러내는 파이프와 스트림의 모든 데이터를 더하는 파이프를 차례로 적용해 '홀수의 합'을 계산한다.

### 스트림(Stream)의 특성 — 예제 `P733_MyFirstStream2`

앞선 예제는 이해를 돕기 위해 스트림의 생성과 연산을 각각의 문장으로 나누어 작성했다. 실제로는 다음과 같이 메소드 호출을 이어 하나의 문장으로 작성하는 방식이 일반적이다.

```java
int sum = Arrays.stream(ar)      // 스트림 생성하고,
                 .filter(n -> n%2 == 1) // filter 통과시키고,
                 .sum();          // sum을 통과시켜 그 결과 반환
```

`filter`와 `sum`은 `IntStream`의 인스턴스 메소드이므로, 스트림의 생성부터 연산까지의 과정을 하나의 문장으로 이어 쓸 수 있다.

스트림 연산은 효율과 성능을 고려해 **지연(Lazy) 처리** 방식으로 동작한다. 위 문장에서는 메소드 호출이 `filter`에서 `sum`으로 이어지지만, `sum`이 호출되기 전까지 `filter`의 호출 결과는 스트림에 반영되지 않는다. 최종 연산인 `sum`이 호출되어야 `filter`의 호출 결과가 스트림에 반영되고, 이어서 `sum`의 호출 결과가 반영된다. 따라서 최종 연산은 '파이프에 물을 흘려보내기 위한 잠금 밸브를 여는 행위'를 수반한다고 볼 수 있다.

중간 연산만 진행하고 최종 연산을 진행하지 않으면 어떻게 될까? 최종 연산이 생략되면 그전에 아무리 많은 중간 연산을 진행했더라도 의미가 없다 — 아무런 결과도 나타나지 않는다.

> 💡 **개발 팁 — 메소드 체이닝(fluent interface)은 스트림만의 특징이 아닙니다.** 각 메소드가 다시 자기 자신을 조작할 수 있는 대상을 반환해 마침표(`.`)로 계속 이어 쓸 수 있게 만드는 설계를 유창한 인터페이스(fluent interface)라 부릅니다. `StringBuilder`의 `append` 체이닝이나 지난 챕터의 `Optional`의 `map`/`flatMap` 체이닝도 같은 설계 원칙을 씁니다 — 중간 결과에 이름을 붙이지 않아도 되므로 코드가 짧아지고, "무엇을 할지"가 순서대로 읽히는 점이 장점입니다.

### 본서에서 스트림을 설명하는 방향

스트림과 관련해 학습할 내용은 크게 세 가지로 구분할 수 있다.

- 스트림의 생성 방법
- 중간 연산의 종류와 내용 — 필터링(Filtering) 및 맵핑(Mapping) 관련 연산
- 최종 연산의 종류와 내용 — 리덕션(Reduction) 관련 연산

중간 연산과 최종 연산 관련 내용은 비교적 많아 한 번에 설명하면 혼란스러울 수 있다. 따라서 이번 챕터에서는 스트림 전반을 훑어보는 것을 목적으로 **스트림의 생성 방법**(배열 및 컬렉션 인스턴스 대상)만 설명하고, 나머지는 다음 챕터에서 설명한다.

### 스트림 생성하기: 배열 — 예제 `P735_StringStream`

배열에 저장된 데이터를 대상으로 스트림을 생성할 때 호출하는 대표적인 메소드는 다음과 같다.

```java
public static <T> Stream<T> stream(T[] array)   // Arrays 클래스에 정의
```

```java
String[] names = {"YOON", "LEE", "PARK"};
Stream<String> stm = Arrays.stream(names); // 스트림 생성
stm.forEach(s -> System.out.println(s));    // 최종 연산 진행
```

Chapter 28에서 `forEach` 메소드를 소개한 바 있다. 당시 소개한 `forEach`는 `Iterable<T>` 인터페이스에 정의된 다음 디폴트 메소드였다.

```java
default void forEach(Consumer<? super T> action) {
    for (T t : this)
        action.accept(t);
}
```

그런데 위 예제에서 호출한 `forEach`는 스트림 자신의 **인스턴스 메소드**다. 즉 두 메소드는 이름과 매개변수 선언은 같지만, 정의된 위치가 다르다 — 기능은 같으므로 혼란스러워하지 않아도 된다. `forEach`의 매개변수 형이 `Consumer<T>`이므로 `accept` 메소드의 구현에 해당하는 람다식을 인자로 전달해야 한다.

또한 `forEach`는 최종 연산이다. 위 예제에서는 스트림을 생성한 뒤 중간 연산 없이 이 메소드를 통해 스트림을 이루는 문자열을 출력했다 — 이처럼 중간 연산 없이 곧바로 최종 연산을 진행할 수도 있다. 실제로는 다음과 같이 메소드 호출을 이어 작성하는 형태가 보편적이다.

```java
Arrays.stream(names)
      .forEach(s -> System.out.println(s));
```

### 스트림 생성하기: 배열 (기본 자료형, 일부 구간) — 예제 `P737_DoubleStream`

기본 자료형의 값을 담은 배열을 대상으로 스트림을 생성하는 방법도 앞서 살펴본 방법과 동일하다. 다만 호출하는 메소드에 차이가 있다. `Arrays` 클래스에는 `int`/`double`/`long`형 배열을 대상으로 스트림을 생성하는 다음 메소드들이 정의되어 있다.

```java
public static IntStream stream(int[] array)
public static IntStream stream(int[] array, int startInclusive, int endExclusive)

public static DoubleStream stream(double[] array)
public static DoubleStream stream(double[] array, int startInclusive, int endExclusive)

public static LongStream stream(double[] array)
public static LongStream stream(double[] array, int startInclusive, int endExclusive)
```

두 번째 형태의 메소드는 배열의 **일부분**을 대상으로 스트림을 생성한다.

```java
double[] ds = {1.1, 2.2, 3.3, 4.4, 5.5};

Arrays.stream(ds)
      .forEach(d -> System.out.print(d + "\t"));
System.out.println();

Arrays.stream(ds, 1, 4)  // 인덱스 1부터 인덱스 4 이전까지
      .forEach(d -> System.out.print(d + "\t"));
System.out.println();
```

### 스트림 생성하기: 컬렉션 인스턴스 — 예제 `P738_ListStream`

컬렉션 인스턴스를 대상으로 스트림을 생성하려면 마찬가지로 `stream` 메소드를 찾으면 된다. 이를 위해 정의된 `stream` 메소드는 `java.util.Collection<E>`에 디폴트 메소드로 다음과 같이 정의되어 있다.

```java
default Stream<E> stream()
```

즉, 컬렉션 인스턴스를 대상으로도 `stream` 메소드를 호출해 스트림을 생성할 수 있다.

```java
List<String> list = Arrays.asList("Toy", "Robot", "Box");
list.stream()
    .forEach(s -> System.out.print(s + "\t"));
System.out.println();
```

이처럼 스트림을 생성하는 일은 어렵지 않다. 호출해야 할 메소드만 알면 된다.

## 29-2. 필터링(Filtering)과 맵핑(Mapping)

스트림이 배열을 대상으로 생성되었는지 컬렉션 인스턴스를 대상으로 생성되었는지와 관계없이, 동일한 방법으로 중간 연산과 최종 연산을 진행할 수 있다. (적용 가능한 연산의 종류에는 약간의 차이가 있다.) 즉 지금부터 설명하는 중간 연산인 필터링과 맵핑은 모든 스트림에 적용할 수 있다.

### 필터링(Filtering) — 예제 `P740_FilterStream`

필터링은 이름 그대로 스트림을 구성하는 데이터 중 일부를 조건에 따라 걸러내는 작업을 의미한다. 필터링에는 다음 메소드를 사용한다.

```java
Stream<T> filter(Predicate<? super T> predicate)   // Stream<T>에 존재
```

위 메소드의 매개변수 형은 `Predicate`이다. 따라서 `Predicate`의 다음 추상 메소드를 구현한 람다식을 인자로 전달해야 한다.

```java
Predicate<T>   boolean test(T t)
```

그러면 `filter` 메소드는 내부적으로 스트림의 데이터를 하나씩 인자로 전달하며 `test`를 호출한다. 그 결과 `true`가 반환되면 해당 데이터는 스트림에 남는다. 반면 `false`가 반환되면 해당 데이터는 걸러진다(버려진다).

```java
int[] ar = {1, 2, 3, 4, 5};
Arrays.stream(ar)              // 배열 기반 스트림 생성
      .filter(n -> n%2 == 1)   // 홀수만 통과시킨다.
      .forEach(n -> System.out.print(n + "\t"));
System.out.println();

List<String> sl = Arrays.asList("Toy", "Robot", "Box");
sl.stream()                          // 컬렉션 인스턴스 기반 스트림 생성
  .filter(s -> s.length() == 3)      // 길이가 3이면 통과시킨다.
  .forEach(s -> System.out.print(s + "\t"));
System.out.println();
```

위 예제에서는 배열과 컬렉션 인스턴스로부터 각각 스트림을 생성하였다. 이처럼 스트림을 생성한 대상의 유형은 다르지만, 이후에 이어지는 중간 연산과 최종 연산의 진행 방식에는 차이가 없다.

### 맵핑(Mapping) 1 — 예제 `P741_MapToInt`

맵핑도 필터링과 마찬가지로 중간 연산이다. 예를 들어 다음 문자열을 담고 있는 배열이 있다고 하자.

```java
String[] as = {"Box", "Robot", "Simple"};
```

이 배열에 저장된 데이터를 대상으로 스트림을 생성하면, 눈에 보이지 않는 스트림을 문자열로 표현하여 다음과 같이 나타낼 수 있다.

```
"Box", "Robot", "Simple"
```

이 스트림을 기반으로 다음 스트림을 생성하는 것이 바로 맵핑이다. (아래의 숫자는 문자열의 길이다.)

```
3, 5, 6
```

즉 문자열 스트림을 숫자 스트림으로 맵핑하였으며, 이때 맵핑의 기준은 문자열의 길이다. 이처럼 맵핑을 진행하면 스트림의 데이터 형이 달라진다.

맵핑에 사용하는 대표적인 메소드는 다음과 같으며, 이는 제네릭 메소드이다.

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper)   // Stream<T>에 존재
```

위 메소드의 매개변수 형은 `Function`이다. 따라서 다음 메소드를 구현한 람다식을 인자로 전달해야 한다.

```java
Function<T, R>   R apply(T t)
```

그러면 `map`은 내부적으로 스트림의 데이터를 하나씩 인자로 전달하며 `apply` 메소드를 호출한다. 그리고 그 결과로 반환되는 값을 모아 새로운 스트림을 생성한다.

```java
List<String> ls = Arrays.asList("Box", "Robot", "Simple");
ls.stream()
  .map(s -> s.length())
  .forEach(n -> System.out.print(n + "\t"));
System.out.println();
```

앞서 보인 예제는 잘 동작하지만, `map`의 인자로 전달한 람다식이 정수를 반환하므로 반환 과정에서 오토 박싱이 진행된다. 그래서 자바에서는 기본 자료형 값을 반환하는 경우를 고려하여 다음 맵핑 관련 메소드도 제공한다.

```java
IntStream mapToInt(ToIntFunction<? super T> mapper)
LongStream mapToLong(ToLongFunction<? super T> mapper)
DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
```

앞서 보인 예제를 `mapToInt` 기반으로 수정하면 다음과 같다. 이전 예제와의 유일한 차이점은 `map` 대신 `mapToInt`를 호출한 것이다. 이로 인해 이전 예제와 달리 오토 박싱이 진행되지 않는다.

```java
List<String> ls = Arrays.asList("Box", "Robot", "Simple");
ls.stream()
  .mapToInt(s -> s.length())
  .forEach(n -> System.out.print(n + "\t"));
System.out.println();
```

> 💡 **개발 팁 — 오토 박싱을 피하는 전용 메소드는 스트림 API 곳곳에 있습니다.** `map`이 항상 `Stream<R>`(객체 스트림)을 반환하는 것과 달리, `mapToInt`/`mapToLong`/`mapToDouble`은 기본 자료형 전용 스트림(`IntStream` 등)을 반환합니다. 데이터가 많을수록 박싱·언박싱 비용이 누적되므로, 숫자를 다루는 중간 연산에서는 가능한 한 이 전용 메소드를 사용하는 습관이 성능에 도움이 됩니다.

### 맵핑(Mapping) 2 — 예제 `P745_ToyStream`

맵핑 관련 예제를 하나 더 살펴본다. 이 예제에서는 필터링 후 맵핑을 한다. 즉 중간 연산을 두 번 진행한다.

```java
class ToyPriceInfo {   // 장난감 모델 별 가격 정보
    private String model;   // 모델 명
    private int price;      // 가격

    public ToyPriceInfo(String m, int p) {
        model = m;
        price = p;
    }
    public int getPrice() {
        return price;
    }
}
```

```java
List<ToyPriceInfo> ls = new ArrayList<>();
ls.add(new ToyPriceInfo("GUN_LR_45", 200));
ls.add(new ToyPriceInfo("TEDDY_BEAR_S_014", 350));
ls.add(new ToyPriceInfo("CAR_TRANSFORM_VER_7719", 550));

int sum = ls.stream()
            .filter(p -> p.getPrice() < 500)
            .mapToInt(t -> t.getPrice())
            .sum();
System.out.println("sum = " + sum);
```

먼저 `filter(p -> p.getPrice() < 500)`를 통해 가격이 500원 미만인 장난감의 가격 정보만 모아 스트림을 생성하였다. 이렇게 얻은 스트림은 가격이 500원 미만인 `ToyPriceInfo` 인스턴스의 스트림이다. 이어서 `mapToInt(t -> t.getPrice())`를 통해 인스턴스에 저장된 가격 정보를 꺼내 `int`형 스트림을 생성하였다. 끝으로 최종 연산 `sum`을 통해 `int`형 스트림에 저장된 값의 총합을 계산하여 반환하였다 — 즉 위 연산을 통해 얻는 결과는 '정가 500원 미만인 장난감 가격의 총합'이다.

## 29-3. 리덕션(Reduction), 병렬 스트림(Parallel Streams)

앞서 최종 연산 `sum`과 `forEach`를 사용해 보았다. 이어서 최종 연산을 대표하는 `reduce` 메소드를 소개한다.

### 리덕션과 reduce 메소드 — 예제 `P748_ReduceStream`

'리덕션(Reduction)'은 '데이터를 축소하는 연산'을 뜻한다. 앞서 보인 `sum`도 리덕션 연산에 해당한다. 다수의 데이터를 더해 '합'이라는 하나의 데이터만 남기므로 `sum`도 리덕션 연산이다. 이렇듯 대부분의 리덕션 연산은 사용하기 쉽다. 그러나 조금 다른 방식으로 리덕션을 진행하는 메소드가 있어 소개한다.

```java
T reduce(T identity, BinaryOperator<T> accumulator)   // Stream<T>에 존재
```

이 메소드는 다른 리덕션 연산보다 활용도가 높다. 다른 리덕션 연산은 연산 내용이 이미 정해져 있지만, `reduce`는 전달하는 람다식에 따라 연산 내용이 결정되기 때문이다. 이 메소드의 동작 원리를 설명하기 위해 두 번째 매개변수의 형인 `BinaryOperator<T>`의 추상 메소드를 보이면 다음과 같다.

```java
BinaryOperator<T>   T apply(T t1, T t2)
```

`reduce` 호출 시 위 메소드 `apply`에 대한 람다식을 인자로 전달해야 한다. 그러면 `reduce`는 내부적으로 `apply`를 호출하면서 스트림에 저장된 데이터를 다음과 같은 방식으로 줄여 나간다.

**[그림 29-1: 리덕션 연산의 진행 방식]** — 스트림에 총 4개의 데이터(`data-1`~`data-4`)가 존재하는 상황에서, `reduce`는 내부적으로 `apply`를 호출하면서 `data-1`과 `data-2`를 인자로 전달하고 그 결과로 `result-1`을 얻는다. 이어서 `result-1`과 `data-3`을 `apply`에 전달해 `result-2`를 얻는다. 마지막으로 `result-2`와 `data-4`를 `apply`에 전달해 `result-3`을 얻는데, 이것이 최종 결과이다. 따라서 `result-3`은 `reduce`가 반환하는 최종 값이 된다.

```java
List<String> ls = Arrays.asList("Box", "Simple", "Complex", "Robot");

BinaryOperator<String> lc = (s1, s2) -> {
    if (s1.length() > s2.length())
        return s1;
    else
        return s2;
};

String str = ls.stream()
               .reduce("", lc);   // 스트림이 빈 경우 빈 문자열 반환
System.out.println(str);
```

인자로 두 문자열을 전달받아 길이를 비교하고, 더 긴 문자열을 반환하는 람다식이다. (두 문자열의 길이가 같을 때는 두 번째 문자열을 반환하도록 작성했다.) 따라서 `reduce`는 내부적으로 스트림을 구성하는 문자열의 길이를 비교해 나간다. 마지막에는 가장 긴 문자열을 반환한다.

한 가지 더 살펴볼 내용은 `reduce` 메소드의 첫 번째 인자이다. 첫 번째 인자로 전달되는 값은 스트림을 구성하는 데이터가 하나도 없을 때 반환된다. 즉 위 예제에서 `ls`가 참조하는 컬렉션 인스턴스에 저장된 문자열이 없으면 빈 문자열이 반환된다. 그런데 이 정도만 알고 있으면 위의 문장을 다음과 같이 구성하는 오류를 범할 수 있다.

```java
String str = ls.stream()
               .reduce("Empty Stream", lc);   // 스트림이 빈 경우 "Empty Stream" 반환
```

의도는 좋다. 스트림이 비어 있을 경우 이를 알리는 문자열이 반환되도록 문장을 작성한 것이다. 그런데 이 문장으로 대체하고 예제를 실행하면 `reduce` 메소드는 "Empty Stream"을 반환한다. 스트림이 비어 있지 않아도 그렇다.

`reduce` 메소드는 '첫 번째 인자로 전달된 값'을 스트림이 빈 경우에 반환한다. 뿐만 아니라 스트림이 비어 있지 않으면 이를 스트림의 첫 번째 데이터로 간주하고 리덕션을 진행한다. 따라서 "Empty Stream"이 가장 긴 문자열이 되어 `reduce` 메소드의 반환 값이 될 수 있다. 참고로 이러한 `reduce` 메소드의 특성은 의외로 유용하다. 예를 들어 조건을 충족하는 데이터가 스트림에 없을 때, 이를 대신할 데이터를 지정할 수 있다.

> ⚠️ **함정 — `reduce`의 첫 번째 인자는 "빈 스트림용 기본값"이면서 동시에 "리덕션의 첫 데이터"이기도 합니다.** 둘 중 하나의 역할만 한다고 생각하면 위와 같은 오류에 빠지기 쉽습니다. 항등원(identity) — 즉 다른 값과 연산해도 그 값 자체를 바꾸지 않는 값 — 을 첫 번째 인자로 넘기는 것이 안전합니다. 문자열 이어붙이기에는 `""`, 합산에는 `0`, 곱셈에는 `1`이 항등원입니다.

### 병렬 스트림(Parallel Streams) — 예제 `P750_ReduceParallelStream`

하나의 작업을 둘 이상의 작업으로 나누어 동시에 진행하는 것을 '병렬 처리'라 한다. 이는 속도 측면에서 장점이 있지만, 작업 구성이 어려워 과거에는 프로그래머들이 쉽게 적용하지 못한 기술이었다. 그러나 멀티 코어 CPU가 대중화된 이 시점에서 자바는 언어 차원에서 '병렬 처리'를 지원한다. 따라서 프로그래머들은 작업 구성을 신경 쓰지 않고 병렬 처리를 진행할 수 있게 되었다.

```java
List<String> ls = Arrays.asList("Box", "Simple", "Complex", "Robot");

BinaryOperator<String> lc = (s1, s2) -> {
    if (s1.length() > s2.length())
        return s1;
    else
        return s2;
};

String str = ls.parallelStream()   // 병렬 처리를 위한 스트림 생성
               .reduce("", lc);
System.out.println(str);
```

위 예제와 이전 예제의 유일한 차이점은 다음 문장에서 `stream`이 아닌 `parallelStream` 메소드를 호출했다는 점이다.

```java
String str = ls.parallelStream()   // 병렬 처리를 위한 '병렬 스트림' 생성
               .reduce("", lc);
```

이렇듯 병렬 스트림을 생성하면 이어지는 연산은 CPU의 코어 수를 고려해 적절하게 병렬 처리된다. 따라서 [그림 29-1]도 병렬 스트림을 기반으로 하면 다음과 같이 달라진다.

**[그림 29-2: 병렬 스트림의 병렬 처리의 예]** — 서로 다른 코어를 기반으로 `data-1`과 `data-2`(Core-1), 그리고 `data-3`과 `data-4`(Core-2)를 대상으로 연산이 동시에 진행되는 상황을 표현한 것이다. 결과적으로 둘 이상의 연산을 동시에 진행하므로 '연산의 단계'를 줄일 수 있고, 이로 인해 속도 측면에서 이점을 얻을 수 있다.

앞서 보인 예제에서 스트림을 구성하는 문자열은 `"Box", "Simple", "Complex", "Robot"`이었는데, `reduce` 메소드의 첫 번째 인자로 빈 문자열이 전달되었으니 CPU의 코어가 넷 이상이라면 가상머신은 네 개의 코어를 활용해 다음과 같은 방식으로 첫 번째 연산을 동시에 진행한다.

```
"빈 문자열" vs. "Box"
"빈 문자열" vs. "Simple"
"빈 문자열" vs. "Complex"
"빈 문자열" vs. "Robot"
```

그리고 얻은 네 개의 결과물을 가지고 두 개의 코어를 활용해 다음 연산을 동시에 진행한다.

```
"Box" vs. "Simple"
"Complex" vs. "Robot"
```

마지막으로 하나의 코어를 활용해 다음 연산을 진행하고 최종 결과를 얻는다.

```
"Simple" vs. "Complex"
```

이렇듯 병렬 처리의 핵심은 연산 횟수를 줄이는 데 있지 않고 연산 단계를 줄이는 데 있다.