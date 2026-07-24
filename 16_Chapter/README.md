# Chapter 16 — 상속의 목적

## 상속의 이유이자 목적

> **연관된 일련의 클래스들에 대해 공통적인 규약을 정의할 수 있습니다.**

## 클래스와 메소드의 final 선언

클래스를 정의하는데 있어서 해당 클래스를 다른 클래스가 상속하는 것을 원치 않는다면, 다음과 같이 final 선언을 추가하면 된다.

```java
public final class MyLastCLS {...}
    // → MyLastCLS 클래스는 다른 클래스가 상속할 수 없음
```

대표적인 final 클래스로 String 클래스가 있다. 따라서 우리는 String 클래스를 상속할 수 없다. 또한 다음과 같이 메소드의 정의에 final 선언을 추가하여 해당 메소드의 오버라이딩을 허용하지 않을 수도 있다.

```java
class Simple {
    // 아래의 메소드는 다른 클래스에서 오버라이딩 할 수 없음
    public final void func(int n) {...}
}
```
