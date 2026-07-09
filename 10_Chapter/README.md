# Chapter 10 — static 변수와 메소드

본 Chapter에서는 static 선언이 붙은 변수와 메소드에 대해서 설명한다. 때문에 본 Chapter를 공부하고 나면 main 메소드에 static 선언이 붙어있는 이유를 알 수 있다.

## static이란?

`static`은 멤버(변수/메소드)가 **특정 객체에 속하지 않고 클래스 자체에 속한다**는 뜻의 키워드다.

| | static | non-static(인스턴스) |
|---|---|---|
| 소속 | 클래스 | 객체(인스턴스) |
| 메모리 | 딱 1개, 모든 객체가 공유 | 객체마다 별도 |
| 호출/접근 | `클래스명.멤버` (객체 없이 OK) | `객체.멤버` (객체 필요) |

## 클래스 메소드에서 인스턴스 변수에 접근이 가능할까?

다음 코드를 보자.
```java
class AAA {
    int num = 0;
    static void addNum(int n) {
        num += n;   // 이 문장이 유효한가?
    }
}
```
논리적으로 생각해보면 위 문장 구성은 불가능하다. 인스턴스 변수는 인스턴스에 속하고, 인스턴스가 생성되어야 메모리 공간에 존재하게 된다. 반면 클래스 메소드는 인스턴스 생성 이전부터 호출이 가능하다. 그런데 어떻게 위와 같은 문장을 구성할 수 있겠는가? 따라서 다음과 같이 답할 수 있어야 한다.

> "클래스 메소드는 인스턴스에 속하지 않으므로 인스턴스 변수에 접근이 불가능하다."
> "같은 이유로 클래스 메소드는 인스턴스 메소드의 호출도 불가능하다."

실제로 컴파일해보면 다음과 같은 에러가 난다.
```java
class AAA {
    int num = 10;
    static int getNum() {
        return num;   // error!
    }
}
```
```
error: non-static variable num cannot be referenced from a static context
```

**단, 인스턴스를 파라미터로 직접 받으면 접근이 가능하다.** 그 순간 "어느 인스턴스의 값인지"가 명확해지기 때문이다.
```java
class AAA {
    int num = 10;
    static int getNum(AAA obj) {
        return obj.num;   // OK! obj가 가리키는 그 인스턴스의 num
    }
}
```

**참고 — static 메소드를 인스턴스명으로 호출하는 것도 문법상 가능은 하다.**
```java
AAA a = new AAA();
a.addNum(5);       // 컴파일 O, 실행 O — 그러나 경고 발생
AAA.addNum(5);      // 이게 정석
```
```
warning: [static] static method should be qualified by type name, AAA, instead of by an expression
```
static 메소드는 애초에 특정 인스턴스에 종속된 동작이 아니므로, `클래스명.메소드명`으로 쓰는 것이 관례이자 정석이다.

물론 인스턴스가 생성되기 전에는 인스턴스 변수 자체가 메모리에 존재하지 않으므로, 그 어떤 방식으로도 접근이 불가능하다.

## 참고 — 클래스 로딩(Class Loading)

가상머신이 특정 클래스 정보를 읽는 행위를 가리켜 **클래스 로딩(Class Loading)**이라 한다. 특정 클래스의 인스턴스 생성을 위해서는 해당 클래스가 반드시 가상머신에 의해 로딩되어야 한다. 즉 **인스턴스 생성보다 클래스 로딩이 먼저**이다.

## System.out.println()에서 out과 println의 정체는?

원칙적으로는 다음과 같이 호출해야 한다.
```java
java.lang.System.out.println( . . . );
```
그러나 컴파일러가 다음 문장을 삽입해주기 때문에 패키지의 이름 부분을 생략할 수 있다.
```java
import java.lang.*;    // 컴파일러가 삽입하는 import 선언
```

그리고 out은 `System.out`으로 접근을 하니, 이는 분명 static으로 선언된 클래스 변수가 분명하다. 클래스의 이름을 통해 접근하니 말이다. 실제로 out은 System 클래스 내에 다음과 같이 선언된 클래스 변수이다. (아래에서 System 클래스에 final 선언이 붙어 있는데 이것이 갖는 의미는 '상속'에서 설명한다.)
```java
public final class System extends Object {
    public static final PrintStream out;   // 참조변수 out
    ....
}
```

마지막으로 println은 PrintStream 클래스의 **인스턴스 메소드**이다. 따라서 다음 문장은,
```java
System.out.println( . . . );
```
다음과 같이 이해할 수 있어야 한다.

> "System에 위치한 클래스 변수 out이 참조하는 인스턴스의 println 메소드를 호출하는 문장"

## main 메소드가 public이고 static인 이유는?

```
C:\JavaStudy>java MyMainClass
```
위와 같이 실행하면, MyMainClass에 public으로 그리고 static으로 선언된 main 메소드를 찾아 실행을 하게 된다.
```java
public static void main(String[] args)
```
- **public인 이유**: main 메소드의 호출이 이뤄지는 영역은 클래스 외부이다. 따라서 public으로 선언하는 것이 타당함.
- **static인 이유**: 위 [static이란?](#static이란) 절에서 보았듯, static 멤버는 객체(인스턴스) 없이 클래스 이름만으로 호출이 가능하다. JVM은 `main`을 호출하기 위해 별도로 인스턴스를 생성하지 않으므로, `main`은 static으로 선언되어야만 호출될 수 있다.

## 메인 메소드의 위치

`P231_CarNBoat_MainInCar/CarNBoat.java`, `P232_CarNBoat_MainInBoat/CarNBoat.java`, `P233_CarNBoat_MainInBoth/CarNBoat.java` 모두 한 파일에 `Car`, `Boat` 두 클래스가 정의되어 있지만, **어느 클래스에 main이 있는지 / 파일 내 몇 번째로 선언되었는지**에 따라 실행 가능 여부가 갈린다.

**P231_CarNBoat_MainInCar/CarNBoat.java** — `main`이 있는 `Car`가 파일에서 **첫 번째**로 선언됨
- 정석적인 실행 방법: `javac CarNBoat.java` 후 `java Car` (메인 메소드가 포함된 클래스 이름으로 실행)
- 하지만: 메인 메소드가 포함된 클래스(`Car`)가 파일의 top-level 클래스 중 **첫 번째**이므로, `java CarNBoat.java` (파일명)로도 실행됨

**P232_CarNBoat_MainInBoat/CarNBoat.java** — `main`이 있는 `Boat`가 파일에서 **두 번째**로 선언됨
- 정석적인 실행 방법: `javac CarNBoat.java` 후 `java Boat`
- 하지만: 파일의 첫 번째 top-level 클래스는 `Car`인데 여기엔 `main`이 없으므로, `java CarNBoat.java`로는 실행되지 않음 (P231과 달리 안 됨)

**P233_CarNBoat_MainInBoth/CarNBoat.java** — `Car`, `Boat` **둘 다** `main`을 가짐
- `Car`와 `Boat`가 각각 독립적인 진입점(entry point)이 되므로, `javac CarNBoat.java` 후 `java Car`, `java Boat` **둘 다** 실행 가능 (이 예제는 두 main의 내용이 동일해서 어느 쪽으로 실행해도 결과가 같음)
- `java CarNBoat.java`(파일명 실행)도 됨: 파일의 첫 번째 top-level 클래스인 `Car`에 유효한 `main`이 있기 때문 — 이때 실행되는 건 `Boat`의 main이 아니라 **`Car`의 main**
- 즉 한 파일에 `main`을 여러 개 둬도 문법적으로 문제없다. 다만 **JVM이 어떤 클래스의 main을 실행할지는 `java` 명령에 지정한 클래스 이름(또는 파일명 실행 시 첫 번째 top-level 클래스)이 결정**한다.

## 참고 — 파일명으로 되어 있는 클래스가 없어도 실행되는 이유

`java 파일명.java` 형태의 실행(Java 11+ 도입, 단일 파일 소스코드 실행)은 `javac`로 `.class` 파일을 만들어 두고 `java 클래스명`으로 실행하는 정석적인 방식과 다르다.

- `.class` 파일을 디스크에 남기지 않고, 메모리 상에서 즉석으로 컴파일 후 바로 실행한다.
- 이 실행 방식에서는 "public 클래스의 이름이 파일명과 같아야 한다"는 규칙이 적용되지 않는다.
- 대신 **파일 안에서 가장 먼저 선언된 top-level 클래스**를 실행 대상으로 삼아, 그 클래스 안의 `main` 메소드를 찾아 실행한다.

그래서 `CarNBoat.java`라는 파일명과 일치하는 `CarNBoat`라는 클래스가 하나도 없어도, 파일 내 첫 번째 클래스에 `main`만 있으면 `java CarNBoat.java`로 정상 실행되는 것이다.

## 또 다른 용도의 static 선언

지금까지는 `static` 변수·메소드를 다뤘다면, `static`은 다음 두 가지 형태로도 쓰인다.

### static 초기화 블록 — `P235_DateOfExecution.java`
```java
import java.time.LocalDate;

public class P235_DateOfExecution {
    static String date;

    static { // 클래스 로딩 시 단 한 번 실행되는 영역
        LocalDate ld = LocalDate.now();
        date = ld.toString();
    }

    public static void main(String[] args) {
        System.out.println(date);
    }
}
```
```
2026-07-09
```
- `static { ... }` 블록을 **static 초기화 블록**이라 부르며, **클래스가 로딩되는 시점에 단 한 번만** 실행된다.
- static 변수를 `static String date = "...";`처럼 **단순 대입**만으로는 초기화하기 어려운 경우(값을 얻기 위해 메소드 호출 등의 로직이 필요한 경우) 사용한다.
- 이 예제에서는 `LocalDate.now()`(static 메소드)를 호출한 결과를 가공해서 `date`에 대입해야 하므로, 필드 선언부에 바로 못 쓰고 static 초기화 블록 안에서 처리한다.

### static import 선언 — `P236_StaticImport.java`
```java
import static java.lang.Math.*;

public class P236_StaticImport {
    public static void main(String[] args) {
        System.out.println(E);
        System.out.println(PI);

        System.out.println(abs(-55));
        System.out.println(max(77, 88));
        System.out.println(min(33, 55));
    }
}
```
```
2.718281828459045
3.141592653589793
55
88
33
```
- `import static 패키지.클래스.*;` 형태로 선언하면, 그 클래스의 **static 멤버들을 클래스 이름 없이** 바로 쓸 수 있다.
- 원래는 `Math.PI`, `Math.abs(-55)`처럼 매번 `Math.`를 붙여야 하지만, static import 덕분에 `PI`, `abs(-55)`처럼 마치 지역에 있는 멤버인 것처럼 사용 가능하다.
- 자주 쓰는 static 멤버가 많은 클래스(`Math`처럼)를 다룰 때 코드를 간결하게 만들어주지만, 어느 클래스의 멤버인지 코드만 봐서는 바로 안 보이는 단점도 있다.
