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
