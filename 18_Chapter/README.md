# Chapter 18 — 예외처리(Exception Handling)

## try ~ catch문의 기본 구조
예외를 처리할 때에는 `try ~ catch`문을 사용하며, 이 문장의 기본 구조는 다음과 같다.

```java
try {
    ...관찰 영역...
}
catch(Exception name) {
    ...처리 영역...
}
```

- `try ~ catch`문은 **try 영역**과 **catch 영역**으로 구분이 된다.
- 이 둘은 **하나의 문장**이므로 항상 연결되어 있어야 한다.

> **try 영역에서 발생한 예외 상황을 catch 영역에서 처리한다.**

## catch 영역은 메소드처럼 동작한다
자세히 보면 catch 영역은 그 생김새가 **메소드와 유사**하다. 그리고 실제로 메소드처럼 동작한다.

예를 들어 `ArithmeticException` 예외를 처리하는 `try ~ catch`문을 다음과 같이 구성하면,

```java
try {
    ...관찰 영역...
}
catch(ArithmeticException e) {
    ...처리 영역...
}
```

- try 영역의 실행 중간에 예외 상황이 만들어지고, 이로 인해 **가상머신이 `ArithmeticException` 인스턴스를 생성**한다.
- 이 인스턴스는 **메소드를 호출하듯이** catch 구문의 매개변수 `e`에 전달이 된다.
- 그러면 가상머신은 **catch 구문 안에서 무엇을 하든 상관없이 예외가 처리된 것으로 간주**하고 실행을 이어나간다.

## try로 감싸야 할 영역의 결정
다음 `try ~ catch`문에서 숫자 2의 위치에서 예외가 발생하고 catch 영역에서 처리가 되면,

```java
try {
    1.  ...
    2.  예외 발생 지점
    3.  ...
}
catch(Exception e) {
    ...
}
4. 예외 처리 이후 실행 지점
```

- 숫자 **3의 위치에서 실행을 이어가는 것이 아니라**, `try ~ catch`문 전체를 건너뛰어 **숫자 4의 위치**에서 실행을 이어가게 된다.
- 이러한 예외 처리 이후의 실행 특성은, **관련이 있는 작업들을 하나로 묶는** 기준이 된다.

### 어디까지 하나의 작업인가
예제에서 입력의 오류로 인한 `InputMismatchException` 예외가 발생할 수 있는 문장은 다음 둘이다.

```java
int n1 = kb.nextInt();
int n2 = kb.nextInt();
```

따라서 이들 각각에 대해 `try ~ catch`문을 구성하는 것도 생각해 볼 수 있다.
그러나 변수 `n1`의 값이 적절히 들어오지 않는다면, 변수 `n2`의 값을 입력받는 것도, `/` 연산을 진행하는 것도 의미가 없다.
즉 다음 문장들은 **하나의 작업**으로 볼 수 있다.

```java
System.out.print("a/b...a? ");    // 이 문장은 '하나의 작업'에서 제외 가능
int n1 = kb.nextInt();
System.out.print("a/b...b? ");
int n2 = kb.nextInt();
System.out.printf("%d / %d = %d \n", n1, n2, n1 / n2);
```

> 이를 진행하는 과정에서 **어느 한 곳에서 예외가 발생하면 나머지 부분을 건너뛰는 것이 적절**하다.

## 둘 이상의 예외를 처리하기 위한 구성
앞서 제시한 나눗셈 관련 예제에서는 다음 두 가지 예외의 발생 가능성이 있다.

```
java.lang.ArithmeticException
java.util.InputMismatchException
```

> 📌 교재에는 `java.lang.InputMismatchException`으로 적혀 있으나, 실제 패키지는 **`java.util`**이다.
> `import java.util.InputMismatchException;`이 필요하다.

이 둘을 하나의 catch로 묶으려면 `|` 기호로 나열한다.

```java
try {
    System.out.print("a/b...a? ");
    int n1 = kb.nextInt();
    System.out.print("a/b...b? ");
    int n2 = kb.nextInt();
    System.out.printf("%d / %d = %d \n", n1, n2, n1/n2);
}
catch(ArithmeticException | InputMismatchException e) {
    e.getMessage();
}

System.out.println("Good bye~~!");
```
