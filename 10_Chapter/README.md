# Chapter 10 — static 변수와 메소드

static 선언이 붙은 변수와 메소드를 다룬다. 이 장을 공부하고 나면 `main` 메소드에 static이 붙어 있는 이유를 알 수 있다.

## static이란?

멤버(변수·메소드)가 **특정 인스턴스가 아니라 클래스 자체에 속한다**는 뜻의 키워드.

| | static (클래스 멤버) | non-static (인스턴스 멤버) |
|---|---|---|
| 소속 | 클래스 | 인스턴스 |
| 메모리 | 딱 1개, 모든 인스턴스가 공유 | 인스턴스마다 별도 |
| 접근 | `클래스명.멤버` — 인스턴스 없이 OK | `참조변수.멤버` — 인스턴스 필요 |

## 클래스 메소드에서 인스턴스 변수에 접근할 수 있을까?

```java
class AAA {
    int num = 10;
    static int getNum() {
        return num;   // ❌ error!
    }
}
```
```
error: non-static variable num cannot be referenced from a static context
```

- 인스턴스 변수는 **인스턴스가 생성되어야** 메모리에 존재한다.
- 클래스 메소드는 **인스턴스 생성 이전에도** 호출 가능하다.
- → 클래스 메소드는 인스턴스 변수에 접근 불가, 같은 이유로 인스턴스 메소드 호출도 불가.

단, **인스턴스를 매개변수로 직접 받으면 접근 가능** — 그 순간 "어느 인스턴스의 값인지"가 명확해지기 때문.

```java
static int getNum(AAA obj) {
    return obj.num;   // ✅ obj가 가리키는 그 인스턴스의 num
}
```

### 참고 — static 메소드를 인스턴스 이름으로 호출하면?

```java
a.addNum(5);     // 컴파일·실행 O — 그러나 warning 발생
AAA.addNum(5);   // ✅ 정석
```
```
warning: [static] static method should be qualified by type name, AAA, instead of by an expression
```

static 메소드는 특정 인스턴스에 종속된 동작이 아니므로 `클래스명.메소드명`이 관례이자 정석.

## 참고 — 클래스 로딩(Class Loading)

가상머신이 클래스 정보를 읽어 들이는 행위. 인스턴스를 생성하려면 해당 클래스가 먼저 로딩되어야 한다 → **인스턴스 생성보다 클래스 로딩이 먼저**.

## System.out.println()에서 out과 println의 정체

```java
public final class System extends Object {
    public static final PrintStream out;   // 클래스 변수(참조변수)
    ....
}
```

- `out` = System 클래스의 **static 참조변수** (그래서 `System.out`으로 접근)
- `println` = out이 참조하는 PrintStream 인스턴스의 **인스턴스 메소드**
- 원래는 `java.lang.System.out.println(...)`이지만, 컴파일러가 `import java.lang.*;`을 삽입해줘서 패키지 이름 생략 가능

> "System에 위치한 클래스 변수 out이 참조하는 인스턴스의 println 메소드를 호출하는 문장"

## main이 public이고 static인 이유

```java
public static void main(String[] args)
```

- **public** — main의 호출이 클래스 외부(JVM)에서 이뤄지기 때문
- **static** — JVM은 main 호출을 위해 인스턴스를 생성하지 않으므로, 클래스 이름만으로 호출 가능해야 하기 때문

## 메인 메소드의 위치 (P231 ~ P233)

세 예제 모두 한 파일에 `Car`, `Boat` 두 클래스가 있지만, **main이 어느 클래스에 / 몇 번째로** 있는지에 따라 실행 방법이 갈린다.

| 예제 | main 위치 | `java 클래스명` | `java CarNBoat.java` (파일명 실행) |
|---|---|---|---|
| P231 MainInCar | Car (첫 번째 클래스) | `java Car` | ✅ 첫 클래스에 main 있음 |
| P232 MainInBoat | Boat (두 번째 클래스) | `java Boat` | ❌ 첫 클래스(Car)에 main 없음 |
| P233 MainInBoth | 둘 다 | `java Car` / `java Boat` 둘 다 OK | ✅ 단, 실행되는 건 **Car의 main** |

- 한 파일에 main을 여러 개 둬도 문법상 문제없다. **어느 main이 실행될지는 `java` 명령에 지정한 클래스 이름**이 결정한다.

### 참고 — 파일명과 같은 클래스가 없어도 실행되는 이유

`java 파일명.java` 형태(Java 11+ 단일 파일 소스코드 실행)는 정석적인 `javac` → `java 클래스명` 방식과 다르다.

- `.class` 파일을 남기지 않고 메모리에서 즉석 컴파일 후 바로 실행
- "public 클래스명 = 파일명" 규칙이 적용되지 않음
- 대신 **파일 내 첫 번째 top-level 클래스**의 main을 찾아 실행

그래서 `CarNBoat`라는 클래스가 파일에 하나도 없어도 `java CarNBoat.java`가 정상 동작한다.

## 또 다른 용도의 static 선언

### static 초기화 블록 — P235

```java
static String date;

static {   // 클래스 로딩 시 딱 한 번 실행되는 영역
    LocalDate ld = LocalDate.now();
    date = ld.toString();
}
```

값을 얻는 데 메소드 호출 등의 로직이 필요해서 **단순 대입만으로는 초기화하기 어려운** static 변수에 사용한다.

### static import 선언 — P236

```java
import static java.lang.Math.*;

System.out.println(PI);         // Math.PI 대신
System.out.println(abs(-55));   // Math.abs(-55) 대신
```

static 멤버를 클래스 이름 없이 바로 쓸 수 있게 해준다. 코드는 간결해지지만, 어느 클래스의 멤버인지 한눈에 안 보이는 단점도 있다.

### 정리 📌

- static 멤버 = **클래스 소속**. 인스턴스 없이 `클래스명.멤버`로 접근하고, 전체가 1개를 공유한다.
- 클래스 메소드에서 인스턴스 멤버 직접 접근은 ❌ (인스턴스를 매개변수로 받으면 ✅).
- `System.out` = static 참조변수, `println` = 그 참조 대상 인스턴스의 메소드.
- main이 `public static`인 이유 = 외부(JVM)에서, 인스턴스 없이 호출되기 때문.
- static 초기화 블록(로딩 시 1회 실행)과 static import(클래스명 생략)도 static의 또 다른 용도.
