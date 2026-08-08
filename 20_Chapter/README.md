# Chapter 20 — 자바의 기본 클래스

## 20-1. 래퍼 클래스 (Wrapper 클래스)

### 기본 자료형의 값을 감싸는 래퍼 클래스 — 예제 `P443_UseWrapperClass`

- `int`형 정수나 `double`형 실수와 같은 **기본 자료형의 값들도 인스턴스로 표현해야 하는 경우**가 있다.
- 예를 들어 다음 메소드의 인자로 정수 3과 실수 7.15를 전달해야 하는 상황이라고 가정해보자.

```java
public static void showData(Object obj) {
    System.out.println(obj);   // toString 메소드 호출하여 반환되는 문자열 출력
}
```

- 위 메소드는 **인스턴스를 인자로 요구한다.** 이렇듯 인스턴스의 참조 값을 요구하는 자리에 기본 자료형의 값을 놓아야 하는 경우가 종종 있다.
- 그리고 이러한 상황에서 필요한 것이 '래퍼 클래스'이다.

> **"래퍼 클래스는 기본 자료형의 값을 감싸는 클래스이다."**

> 💡 **개발 팁 — 컬렉션이 래퍼 클래스를 요구한다**
> 실무에서 래퍼 클래스를 가장 자주 만나는 곳은 `List`, `Map` 같은 **컬렉션 프레임워크**다. 이들은 `Object`를 담도록 설계되어 있어서 `List<int>`는 만들 수 없고 `List<Integer>`로 써야 한다. "인스턴스를 요구하는 자리"의 대표 사례인 셈이다.

### 기본 자료형별 래퍼 클래스

모든 기본 자료형을 대상으로 래퍼 클래스가 정의되어 있다. (오른편에 위치한 것은 생성자이다.)

| 래퍼 클래스 | 생성자 |
|---|---|
| `Boolean` | `public Boolean(boolean value)` |
| `Character` | `public Character(char value)` |
| `Byte` | `public Byte(byte value)` |
| `Short` | `public Short(short value)` |
| `Integer` | `public Integer(int value)` |
| `Long` | `public Long(long value)` |
| `Float` | `public Float(float value)`, `public Float(double value)` |
| `Double` | `public Double(double value)` |

### 래퍼 클래스의 두 가지 기능

래퍼 클래스의 중요한 기능 두 가지는 다음과 같다.

- 하나는 **값을 인스턴스로 감싸는 것**이고,
- 다른 하나는 **인스턴스에서 값을 꺼내는 것**이다.

**[그림 20-1: Boxing & Unboxing]**

```
기본 자료형의 값        Boxing →        Wrapper 인스턴스
   byte      ←--------------------→      Byte
   short     ←--------------------→      Short
   int       ←--------------------→      Integer
   float     ←--------------------→      Float
   double    ←--------------------→      Double
    ....          ← Unboxing              ....
```

- 값을 인스턴스에 감싸는 행위를 가리켜 **'박싱(Boxing)'** 이라 한다.
- 반대로 저장된 값을 꺼내는 행위를 가리켜 **'언박싱(Unboxing)'** 이라 한다.
- **박싱은 인스턴스의 생성을 통해서** 이뤄지지만, **언박싱은 래퍼 클래스에 정의된 메소드의 호출을 통해서** 이뤄진다.
