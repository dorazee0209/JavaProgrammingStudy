# Chapter 11 — 메소드 오버로딩과 String 클래스

## 오버로딩 vs 오버라이딩 (Overloading vs Overriding)

이 챕터에서 다루는 건 **오버로딩**. 오버라이딩은 상속 챕터에서 본격적으로 다루지만, 이름이 비슷해 헷갈리므로 미리 비교해둔다.

```java
// 오버로딩 — 같은 클래스 안, 같은 이름, 다른 매개변수 목록
class Calculator {
    int add(int a, int b)           { return a + b; }
    double add(double a, double b)  { return a + b; }      // 타입이 달라서 성립
    int add(int a, int b, int c)    { return a + b + c; }  // 개수가 달라서 성립
}
```
```java
// 오버라이딩 — 상속 관계에서 같은 시그니처로 재정의
class Animal {
    void sound() { System.out.println("동물이 소리를 낸다."); }
}
class Dog extends Animal {
    @Override
    void sound() { System.out.println("멍멍!"); }
}
```

| | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|---|---|---|
| 한글 용어 | 다중정의 | 재정의 |
| 발생 위치 | **같은 클래스** 안 | **상속 관계** (부모 ↔ 자식) |
| 메소드 이름 | 같아야 함 | 같아야 함 |
| 매개변수 목록 | 반드시 **달라야** 함 | 반드시 **같아야** 함 |
| 리턴 타입 | 자유 (단, 리턴 타입만 다른 건 ❌ 컴파일 에러) | 원칙적으로 같아야 함 |
| 결정 시점 | 컴파일 타임 (정적 바인딩) | 런타임 (동적 바인딩) — 다형성의 핵심 |
| 목적 | 같은 동작을 다양한 입력 형태로 제공 | 부모의 동작을 자식이 고유하게 재구현 |

💡 영단어로 기억: `overLOAD` = 짐(매개변수)을 다르게 **싣는다** / `overRIDE` = 위에 올라타서 **덮어쓴다**

## println이 아무 자료형이나 받을 수 있는 이유

신기한 문법이 아니라, 그냥 **오버로딩이 잔뜩 되어 있는 것**뿐이다.

```java
void println()            // 인자 없이 호출하면 단순 개행
void println(int x)       // str.length()의 반환값(int)을 받아주는 오버로딩
void println(String x)
// ... 실제로는 훨씬 다양함
```

### 문자열 리터럴의 정체 — 그 자체로 String 인스턴스

```java
showString("Funny String");
   → showString(0x1234);   // 리터럴로 생성된 String 인스턴스의 참조 값이 전달됨 (0x1234라 가정)
```

- 큰따옴표 문자열은 등장하는 순간 **String 인스턴스**가 되고, 그 자리엔 인스턴스의 **참조 값**이 들어간다.
- 그래서 `println("문자열")`도 문자열 통째가 아니라 **참조 값**을 `println(String x)`에 넘기는 것.
- 인스턴스라는 증거 — 리터럴에 바로 메소드 호출이 가능하다:

```java
int len = "123".length();   // 3 — 인스턴스가 아니라면 불가능한 호출
```

## String 대표 메소드

| 메소드 | 하는 일 |
|---|---|
| `concat(String str)` | 뒤에 이어붙인 **새 String** 반환 (원본은 그대로 — 불변) |
| `substring(begin)` | begin부터 끝까지 추출 |
| `substring(begin, end)` | begin ~ end-1 추출 (**end는 미포함**) |
| `equals(Object o)` | **내용** 비교 → true/false |
| `compareTo(String s)` | 사전순 비교 — 같으면 0, 앞서면 음수, 뒤지면 양수 (대소문자 구분) |
| `compareToIgnoreCase(String s)` | 위와 같지만 대소문자 무시 |
| `static valueOf(...)` | 기본 자료형 값 → 문자열. 자료형별 오버로딩, `String.valueOf(2.718281)` |

⚠️ 문자열 **내용** 비교는 반드시 `equals` — `==`는 참조 비교라서, `new String`으로 만든 두 인스턴스는 내용이 같아도 `==`는 `false`.

## StringBuilder 클래스

String은 불변이라 수정할 때마다 새 인스턴스가 생기지만, `StringBuilder`는 내부 메모리 공간의 내용을 **직접 고쳐서 재사용**한다 → 계속 바뀌는 문자열엔 이쪽이 효율적.

| 메소드 | 하는 일 |
|---|---|
| `append(...)` | 끝에 추가 — 자료형별 오버로딩이 특히 다양 |
| `delete(start, end)` | start ~ end-1 삭제 |
| `insert(offset, str)` | offset 위치에 삽입 |
| `replace(start, end, str)` | start ~ end-1을 str로 대체 |
| `reverse()` | 저장된 문자열 뒤집기 |
| `substring(start, end)` | start ~ end-1을 담은 **새 String** 반환 |
| `toString()` | 전체 내용을 담은 **새 String** 반환 |

### 내부 공간 크기는 생성자로 지정

```java
new StringBuilder()        // 기본 16문자 공간
new StringBuilder(64)      // 64문자 공간
new StringBuilder("abc")   // "abc" + 16문자 공간
```

공간이 부족하면 알아서 늘어나지만 확장 비용이 크다 → 쓸 크기를 미리 지정해두면 성능 이득.

## StringBuffer 클래스 — StringBuilder의 원조

생성자·메소드의 **수, 기능, 이름, 매개변수 선언**이 전부 일치한다(사실상 같은 클래스). 차이는 딱 하나:

> "StringBuffer는 쓰레드에 안전하지만, StringBuilder는 쓰레드에 안전하지 않다."

- 원래 `StringBuffer`가 먼저 있었고(안전장치 때문에 상대적으로 느림), 자바 5에서 **안전은 포기하고 속도를 높인** `StringBuilder`가 새로 등장.
- 멀티 쓰레드와 무관한 대부분의 상황 → **StringBuilder** / 여러 쓰레드가 같은 인스턴스를 동시에 건드리는 상황 → StringBuffer.

### 정리 📌

- **오버로딩** = 한 클래스 안에서 매개변수만 다르게 여러 벌 / **오버라이딩** = 자식 클래스가 덮어쓰기.
- println의 만능처럼 보이는 능력 = 오버로딩 덕분.
- 문자열 리터럴은 그 자체가 String 인스턴스 → 코드에서 쓰이는 자리엔 **참조 값**이 들어간다.
- String은 불변 — `concat`/`substring` 모두 새 인스턴스 반환, 내용 비교는 `equals`.
- 자주 바뀌는 문자열은 `StringBuilder`(빠름), 멀티 쓰레드 환경이면 `StringBuffer`(안전).
