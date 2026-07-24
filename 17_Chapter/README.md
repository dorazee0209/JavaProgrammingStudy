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
