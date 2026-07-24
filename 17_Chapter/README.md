# Chapter 17 — 인터페이스

## 추상 메소드만 담고 있는 인터페이스
- 인터페이스는 클래스와 기본 골격이 동일하다. 다만 `class` 대신 `interface` 선언이 붙고, **메소드는 몸체 없이 세미콜론으로 마무리**된다.
  ```java
  interface Printable {
      public void print(String doc);   // 추상 메소드
  }
  ```
- 이렇게 몸체가 비어 있는 메소드를 **추상 메소드(Abstract Method)** 라 한다.
- **인터페이스를 대상으로는 인스턴스 생성이 불가능**하다. 다만 다른 클래스에 의해 다음과 같이 상속(구현)이 될 뿐이다. (키워드 `implements`가 사용됨에 주목)
  ```java
  class Printer implements Printable {
      public void print(String doc) {   // Printable 인터페이스의 print 메소드 구현
          System.out.println(doc);
      }
  }
  ```

## 인터페이스의 구현(Implementation)
- 클래스가 인터페이스를 상속하는 행위는 '상속'이 아닌 **'구현(Implementation)'**이라 한다. 문법 관계는 상속과 동일하지만 본질은 '구현'이기 때문이다.
- 클래스의 인터페이스 구현 특징:
  - 구현할 인터페이스를 명시할 때 키워드 `implements`를 사용한다.
  - 한 클래스는 둘 이상의 인터페이스를 동시에 구현할 수 있다.
  - 상속과 구현은 동시에 가능하다.
  ```java
  class Robot extends Machine implements Movable, Runnable {...}
  ```

## 인터페이스 관련 추가 특징
- 인터페이스의 형을 대상으로 참조변수의 선언이 가능하다.
- 인터페이스의 추상 메소드와 이를 구현하는 메소드 사이에 오버라이딩 관계가 성립한다.
  → 따라서 어노테이션 `@Override`의 선언이 가능하다.

## 인터페이스에 선언되는 메소드와 변수
- 인터페이스에 존재할 수 있는 메소드에는 **추상 메소드, 디폴트 메소드, static 메소드**가 있다.
- 인터페이스 간 상속도 가능하며, 인터페이스의 형(Type) 이름을 대상으로 `instanceof` 연산을 할 수도 있다.
  → 즉 많은 특성이 클래스와 유사하다.

앞서 정의한 인터페이스를 다시 보면:
```java
interface Printable {
    public void print(String doc);   // 추상 메소드
}
```
위 인터페이스에 정의된 추상 메소드에는 다음의 특징이 있다.

> "인터페이스의 모든 메소드는 public이 선언된 것으로 간주합니다."

- 즉 인터페이스 내에 위치하는 메소드는 별도의 선언이 없어도 `public`이 된다.
- 때문에 위의 인터페이스 정의에서 메소드 앞에 `public`을 붙일 필요가 없다.

그리고 인터페이스에도 다음과 같이 변수를 선언할 수 있다.
```java
interface Printable {
    int PAPER_WIDTH = 70;
    int PAPER_HEIGHT = 120;
    void print(String doc);
}
```
- 이렇게 인터페이스 내에 선언되는 변수에는 다음의 특징이 있다.
  - 반드시 선언과 동시에 값으로 초기화를 해야 한다.
  - 모든 변수는 `public`, `static`, `final`이 선언된 것으로 간주한다.
- 결론적으로 인터페이스 내에 선언된 변수는 **상수**이다. `final`로 그리고 `static`으로 자동 선언이 되니, 다음과 같이 접근이 가능한 상수이다.
  ```java
  System.out.println(Printable.PAPER_WIDT);   // 인터페이스 내에 위치한 상수의 접근
  ```
- 그래서 상수는 대문자로 이름을 짓는 관례를 따라 인터페이스 내에 위치한 변수의(상수의) 이름은 대문자로 짓는다.

**⭐️ 인터페이스를 구현하는 클래스는 인터페이스에 존재하는 모든 '추상 메소드'를 구현해야 한다.**
- 하나라도 구현하지 않으면, 해당 클래스를 대상으로는 **인스턴스 생성이 불가능**하다.
- 쉽게 생각하면 된다 — 다 채워지지 않은 상태이니 인스턴스 생성이 불가능한 것이다.
