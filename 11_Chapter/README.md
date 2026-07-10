# Chapter 11 — 메소드 오버로딩과 String 클래스

## 오버로딩 vs 오버라이딩 (Overloading vs Overriding)

이름이 비슷해서 헷갈리기 쉬운 두 개념이지만, 발생하는 위치와 목적이 전혀 다르다. 이 챕터에서 다루는 건 **오버로딩**이고, 오버라이딩은 뒤에 나올 상속 챕터에서 본격적으로 다루지만, 미리 차이를 비교해두고 넘어간다.

### 오버로딩(Overloading, 다중정의)
**같은 클래스 안**에, **이름은 같지만 매개변수 목록(개수·타입·순서)이 다른** 메소드를 여러 개 정의하는 것.
```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    double add(double a, double b) {   // 매개변수 타입이 달라서 오버로딩 성립
        return a + b;
    }
    int add(int a, int b, int c) {     // 매개변수 개수가 달라서 오버로딩 성립
        return a + b + c;
    }
}
```
- 어떤 `add`가 호출될지는 **컴파일 타임에 매개변수 목록을 보고 결정**된다.
- 매개변수 목록은 완전히 같은데 **리턴 타입만 다르면 오버로딩이 아니라 컴파일 에러**다. (`int add(int a, int b)`와 `double add(int a, int b)`는 공존 불가)

### 오버라이딩(Overriding, 재정의)
**상속 관계(부모-자식 클래스)**에서, 자식 클래스가 부모 클래스의 메소드를 **동일한 시그니처(이름·매개변수 목록)로 다시 정의**하는 것.
```java
class Animal {
    void sound() {
        System.out.println("동물이 소리를 낸다.");
    }
}
class Dog extends Animal {
    @Override
    void sound() {              // 부모의 sound()를 자식이 재정의
        System.out.println("멍멍!");
    }
}
```
- 실제로 어떤 `sound()`가 호출될지는 **런타임에 객체의 실제 타입을 보고 결정**된다 (동적 바인딩) — 다형성(polymorphism)의 핵심 원리.

### 한눈에 비교

| | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|---|---|---|
| 한글 용어 | 다중정의 | 재정의 |
| 발생 위치 | **같은 클래스** 안 | **상속 관계** (부모 ↔ 자식) |
| 메소드 이름 | 같아야 함 | 같아야 함 |
| 매개변수 목록 | **반드시 달라야** 함 | **반드시 같아야** 함 |
| 리턴 타입 | 자유 (단, 매개변수까지 같으면 리턴 타입만 다른 건 불가) | 원칙적으로 같아야 함 |
| 바인딩 시점 | 컴파일 타임 (정적 바인딩) | 런타임 (동적 바인딩) |
| 목적 | 같은 동작을 다양한 입력 형태로 제공 | 부모의 동작을 자식이 고유하게 재구현 |

### 정리 📌
- **오버로딩** = 이름 같은 메소드를 **한 클래스 안**에서 매개변수만 다르게 여러 벌 두는 것 → "입력 형태를 다양화"
- **오버라이딩** = 이름·매개변수 다 같은 메소드를 **자식 클래스**에서 덮어쓰는 것 → "동작을 다시 정의"
- 영단어로 기억하면: `overLOAD` = 짐(매개변수)을 더 많이/다르게 **싣는다**, `overRIDE` = 위에 올라타서 **덮어쓴다**

## System.out.println()에 인자로 다양한 자료형을 넣어도 되는 이유

> "메소드 length의 반환 값이 어떻게 println 메소드의 인자가 될 수 있을까?"

메소드 `println`이 다음과 같이 **오버로딩**되어 있기 때문에 가능하다. 특히 인자를 전달하지 않고도 호출이 가능한데, 이럴 경우 단순히 '개행'을 하게 된다.
```java
void println() { ... }
void println(int x) { ... }
void println(String x) { ... }
```
사실 `println` 메소드는 보다 다양한 인자를 전달받을 수 있도록 오버로딩되어 있는데, 여기서는 예제(`P248_StringInst.java`)에서 호출한 메소드를 대상으로만 오버로딩 관계를 소개했다. 그래서 `str.length()`(반환형 `int`)의 결과를 그대로 `println`에 넘겨도 `println(int x)` 오버로딩이 받아주기 때문에 문제없이 컴파일·실행된다.

그리고 이어서 다음 문장도 관찰할 필요가 있다.
```java
System.out.println("Funny String");
```
이는 마치 문자열을 통째로 전달하는 듯한 모습을 보인다. 그러나 메소드에는 문자열이 아닌, `"Funny String"`을 대상으로 만들어진 String 인스턴스의 **참조 값**이 전달되는 것이다. (문자열 리터럴도 결국 String 인스턴스이기 때문.)

### 문자열 리터럴을 넘기면 실제로 무슨 일이 벌어지나

```java
showString("Funny String");
```
위의 문장이 실행되면 일단 `"Funny String"`을 대상으로 String 인스턴스가 만들어진다. 그리고 이어서 이 인스턴스의 **참조 값**이 문자열 선언을 대신하게 된다. 예를 들어서 위의 문자열 선언을 통해 생성된 인스턴스의 참조 값이 `0x1234`라고 하면, 위의 문장은 메소드 호출 전(String 인스턴스 생성 후) 다음과 같은 상황에 놓이게 된다.
```java
showString("Funny String");
              → showString(0x1234);   // 0x1234는 인스턴스의 참조 값이라 가정
```
그래서 다음과 같이 매개변수 선언이 String형 참조변수로 선언된 메소드는 문자열을 인자로 전달받을 수 있다.
```java
public static void showString(String str) { ... }
```

큰따옴표로 묶인 문자열 선언만으로도 String 인스턴스가 생성된다고 하였는데, 이 부분이 의심스럽다면 다음 두 문장의 실행을 통해서 인스턴스의 생성을 직접 확인할 수도 있다.
```java
public static void main(String[] args) {
    int len = "123".length();
    System.out.println(len);
}
```
```
3
```
문자열 `"123"`을 대상으로 메소드 `length`를 호출하고 있다. 문자열 `"123"`이 인스턴스의 생성으로 이어지지 않으면 이러한 메소드의 호출은 불가능한 일이다.

### 정리 📌
- `println`이 여러 자료형을 인자로 받을 수 있는 건 신기한 문법이 아니라, 그냥 **오버로딩**이 잔뜩 되어 있는 것뿐이다.
- `str.length()`의 반환값이 `println`의 인자로 들어갈 수 있는 이유 → `println(int x)` 오버로딩이 존재해서
- 문자열 리터럴(`"Funny String"`, `"123"` 등)은 등장하는 순간 **그 자체로 String 인스턴스**가 되고, 코드에서 쓰이는 자리엔 그 인스턴스의 **참조 값**이 대신 들어간다.
- 그래서 `"123".length()`처럼 리터럴에 바로 메소드를 호출하는 것도 가능하다 — 인스턴스가 아니라면 애초에 메소드 호출 자체가 불가능했을 것이다.
- `println("문자열")`처럼 리터럴을 직접 넣어도 되는 이유 → 문자열 리터럴 자체가 String 인스턴스이고, 그 **참조 값**이 `println(String x)`로 전달되는 것

## String이 제공하는 대표 메소드

### 문자열 연결 — concat
```java
public String concat(String str)
```
원본 문자열 뒤에 `str`을 이어붙인 **새로운 String 인스턴스**를 반환한다. (String은 불변이라 원본은 그대로.)

### 문자열 일부 추출 — substring (오버로딩)
```java
public String substring(int beginIndex)
public String substring(int beginIndex, int endIndex)
```
- `substring(beginIndex)`: `beginIndex`부터 끝까지 잘라낸 부분 문자열
- `substring(beginIndex, endIndex)`: `beginIndex` ~ `endIndex-1`까지만 잘라낸 부분 문자열 (`endIndex`는 포함 안 됨)

### 문자열 내용 비교 — equals / compareTo / compareToIgnoreCase
```java
public boolean equals(Object anObject)
public int compareTo(String anotherString)
public int compareToIgnoreCase(String str)
```
- `equals`: 내용이 완전히 같으면 `true`. (`==`는 참조 비교라 다름 — `new String`으로 만든 두 인스턴스는 내용이 같아도 `==`는 `false`, `equals`는 `true`)
- `compareTo`: 사전 순서로 비교해 같으면 `0`, 앞서면 음수, 뒤지면 양수 (대소문자 구분)
- `compareToIgnoreCase`: `compareTo`와 동일하지만 대소문자는 구분하지 않음

### 기본 자료형의 값을 문자열로 바꾸기 — valueOf (오버로딩)
```java
static String valueOf(boolean b)
static String valueOf(char c)
static String valueOf(double d)
static String valueOf(float f)
static String valueOf(int i)
static String valueOf(long l)
```
기본 자료형(`boolean`, `char`, `double`, `float`, `int`, `long`)마다 하나씩 오버로딩되어 있는 **클래스 메소드**다. `static`이라 인스턴스 생성 없이 `String.valueOf(값)` 형태로 바로 호출한다.
```java
double e = 2.718281;
String se = String.valueOf(e);   // "2.718281"
```

### 정리 📌
- `concat`, `substring` 모두 **원본은 그대로 두고 새 String을 만들어 반환**한다 — String이 불변(immutable)이기 때문.
- 문자열 내용 비교는 반드시 `equals`로 (`==`는 참조 비교라서 다른 결과가 나올 수 있음).
- `compareTo` 계열은 결과값의 `0`/음수/양수로 "같다/앞선다/뒤진다"를 나타낸다.
- `valueOf`는 어떤 기본 자료형을 넘기든 컴파일러가 매개변수 타입을 보고 **알맞은 오버로딩**을 알아서 골라 호출해준다.

## StringBuilder 클래스

`StringBuilder`는 내부적으로 문자열을 저장하는 **메모리 공간**을 따로 갖는데, String과 달리 그 공간의 내용을 **추가·삭제·수정**할 수 있다. 그래서 계속 바뀌는 문자열을 다뤄야 한다면 String보다 `StringBuilder`가 효율적이다.

### 대표 메소드
```java
public StringBuilder append(int i)                        // 문자열 끝에 데이터 추가
public StringBuilder delete(int start, int end)            // start ~ end-1 삭제
public StringBuilder insert(int offset, String str)        // offset 위치에 str 삽입
public StringBuilder replace(int start, int end, String str) // start ~ end-1을 str로 대체
public StringBuilder reverse()                              // 저장된 문자열 뒤집기
public String substring(int start, int end)                 // start ~ end-1만 담은 String 반환
public String toString()                                     // 전체 내용을 담은 String 반환
```

### append는 특히 오버로딩이 다양함
```java
public StringBuilder append(boolean b)
public StringBuilder append(char c)
public StringBuilder append(double d)
public StringBuilder append(float f)
public StringBuilder append(int i)
public StringBuilder append(long lng)
public StringBuilder append(Object obj)
public StringBuilder append(String str)
```
(실제로는 더 다양하게 오버로딩되어 있다.) 어떤 자료형을 넘기든 알맞은 오버로딩이 자동으로 골라져 호출된다.

### 내부 메모리 공간 — 생성자로 크기 지정
`StringBuilder` 인스턴스 내부에는 문자열 관리를 위한 메모리 공간이 있는데, 이 크기를 생성자로 직접 지정해줄 수 있다.
```java
public StringBuilder()              // 16개 문자를 저장할 공간 확보 (기본값)
public StringBuilder(int capacity)  // capacity개의 문자를 저장할 공간 확보
public StringBuilder(String str)    // 전달된 문자열 + 16개 문자를 저장할 공간 확보
```
```java
StringBuilder stbuf = new StringBuilder(64);   // 64개 문자 공간으로 시작
```
물론 `StringBuilder`는 공간이 부족하면 스스로 늘려서 관리해준다. 다만 이 확장 작업은 비용이 크기 때문에, 사용 계획에 맞춰 애초에 적절한 크기로 생성해두면 그만큼 성능 이득을 볼 수 있다.

### 정리 📌
- String은 불변이라 수정할 때마다 **새 인스턴스**가 만들어지지만, `StringBuilder`는 자기 내부 메모리를 **직접 고쳐서 재사용**한다 — 문자열이 자주 바뀌는 상황엔 이쪽이 낫다.
- `append`/`delete`/`insert`/`replace`로 자유롭게 편집하고, `reverse`로 뒤집고, 마지막에 `toString()`으로 최종 결과를 String으로 뽑아낸다.
- `substring`/`toString`은 `StringBuilder` 자신이 아니라 **새로운 String 인스턴스**를 반환한다는 점도 기억.
- 내부 공간이 부족하면 자동으로 늘어나지만 비용이 드는 작업이라, 미리 필요한 크기를 생성자에 넘겨주면 성능에 유리하다.

## StringBuffer 클래스 — StringBuilder의 원조

`StringBuilder`는 사실 **자바 5에 와서야 등장한 클래스**다. 그 이전까지 문자열을 조립·수정하는 용도로 쓰이던 게 `StringBuffer`인데, 다음과 같은 부분이 일치한다.

- 생성자를 포함한 메소드의 수
- 메소드의 기능
- 메소드의 이름과 매개변수의 선언

이 세 가지가 일치한다는 건 사실상 같은 클래스라는 뜻이다.

### 유일한 차이 — 쓰레드 안전성
> "StringBuffer는 쓰레드에 안전하지만, StringBuilder는 쓰레드에 안전하지 않다."

- `StringBuffer`는 **멀티 쓰레드 환경에서 안전**하게 동작하도록 설계됐다. 그 안전장치 때문에 속도는 상대적으로 느리다.
- 하지만 대부분의 상황은 멀티 쓰레드와 무관한데, 그런 경우까지 `StringBuffer`의 안전장치 비용을 떠안는 건 아쉬운 일이다. 그래서 나중에 **쓰레드 안전은 포기하고 속도를 높인** `StringBuilder`가 새로 정의됐다.

### 정리 📌
- 둘은 생성자·메소드 구성이 완전히 같고, 딱 **쓰레드 안전성 여부** 하나만 다르다.
- 대부분의 경우(멀티 쓰레드가 아니라면) → **`StringBuilder`가 더 빠르므로 이쪽을 사용**.
- 여러 쓰레드가 같은 인스턴스를 동시에 건드릴 수 있는 상황이라면 → `StringBuffer`.
