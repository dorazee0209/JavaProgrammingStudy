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

## 20-2. BigDecimal 클래스

### 오차 없는 실수의 표현

- 다음과 같이 실수를 **문자열로 전달**하면서 `BigDecimal` 인스턴스를 생성할 수 있다.

```java
BigDecimal d1 = new BigDecimal("1.6");   // 정상적인 방법
BigDecimal d2 = new BigDecimal("0.1");   // 정상적인 방법
```

- 이와 달리 다음과 같이 실수 1.6과 0.1을 인자로 전달하면서 `BigDecimal` 인스턴스를 생성할 수도 있다.

```java
BigDecimal d1 = new BigDecimal(1.6);     // 가능은 하지만
BigDecimal d2 = new BigDecimal(0.1);     // 가능은 하지만
```

- 그러나 이렇게 인스턴스를 생성할 경우 `BigDecimal` 인스턴스에 저장된 값은 더 이상 1.6과 0.1이 아니다. 실수는 표현되는 순간부터 오차를 지니기 때문이다. 즉 `BigDecimal`의 생성자에 전달된 값은 오차가 있는 1.6과 0.1이다. 그리고 이러한 사실은 다음과 같이 그 값을 출력해 봄으로써 확인할 수 있다.

```java
BigDecimal d = new BigDecimal(1.6);
System.out.println("오차 있는 1.6 : " + d);
```

> **"오차 없는 값을 지니는 `BigDecimal` 인스턴스를 생성하려면, 그 값을 문자열로 구성해서 전달해야 한다."**

### BigDecimal의 사칙연산 메소드

`BigInteger` 클래스와 마찬가지로 `BigDecimal` 클래스에도 다음 사칙연산을 포함하여 다양한 연산을 위한 메소드가 정의되어 있다.

| 연산 | 메소드 |
|---|---|
| 덧셈 | `public BigDecimal add(BigDecimal augend)` |
| 뺄셈 | `public BigDecimal subtract(BigDecimal subtrahend)` |
| 곱셈 | `public BigDecimal multiply(BigDecimal multiplicand)` |
| 나눗셈 | `public BigDecimal divide(BigDecimal divisor)` |

## 20-3. Math 클래스

### 수학 관련 연산을 제공하는 Math 클래스 — 예제 `P456_SimpleMathUse`

- `java.lang.Math` 클래스에는 수학과 관련된 다양한 연산이 **클래스 메소드(static)** 로 정의되어 있다.
- 따라서 **인스턴스를 생성하지 않고** `Math.메소드이름(...)` 형태로 바로 호출하면 된다.
- 원주율과 같은 상수도 클래스 변수로 정의되어 있어 `Math.PI`로 접근한다.

```java
public class P456_SimpleMathUse {
    public static void main(String[] args) {
        System.out.println("원주율: " + Math.PI);
        System.out.println("Root 2: " + Math.sqrt(2));
        System.out.println();
        System.out.println("PI's degree: " + Math.toDegrees(Math.PI));
        System.out.println("2PI 's degree: " + Math.toDegrees(2.0 * Math.PI));
        System.out.println();

        double radian45 = Math.toRadians(45); // Convert to radians
        System.out.println("sin(45): " + Math.sin(radian45));
        System.out.println("cos(45): " + Math.cos(radian45));
        System.out.println("tan(45): " + Math.tan(radian45));
        System.out.println();
        System.out.println("log(25): " + Math.log(25));
        System.out.println("2 to the power of 16: " + Math.pow(2, 16));
    }
}
```

**실행 결과**

```
원주율: 3.141592653589793
Root 2: 1.4142135623730951

PI's degree: 180.0
2PI 's degree: 360.0

sin(45): 0.7071067811865475
cos(45): 0.7071067811865476
tan(45): 0.9999999999999999

log(25): 3.2188758248682006
2 to the power of 16: 65536.0
```

### 디그리(Degree)와 라디안(Radian)

- 위 예제 관련하여 한 가지만 언급하면, 사인·코사인·탄젠트 값을 얻기 위해서는 `sin`, `cos`, `tan` 메소드를 호출해야 하는데, 이때 **전달되는 인자는 라디안 단위의 값이어야 한다.**
- 따라서 이 세 가지 메소드 호출 이전에 다음과 같이 **'디그리(Degree)' 단위의 값을 '라디안(Radian)' 단위의 값으로 변환하는 과정**을 거쳐야 한다.

```java
double radian45 = Math.toRadians(45);   // 45도 → 라디안으로 변환
System.out.println("sin(45): " + Math.sin(radian45));
```

> **"삼각함수 메소드에 전달하는 인자는 디그리가 아닌 라디안 단위의 값이어야 한다."**

- 반대로 라디안 값을 디그리 값으로 되돌릴 때에는 `Math.toDegrees` 메소드를 사용한다. 그래서 위 예제에서 `Math.toDegrees(Math.PI)`의 결과는 `180.0`이고, `Math.toDegrees(2.0 * Math.PI)`의 결과는 `360.0`이다.

### 예제에서 사용한 주요 메소드

| 구분 | 메소드 | 설명 |
|---|---|---|
| 상수 | `Math.PI` | 원주율 π (`double` 형 클래스 변수) |
| 제곱근 | `public static double sqrt(double a)` | 인자로 전달된 값의 제곱근 |
| 각도 변환 | `public static double toDegrees(double angrad)` | 라디안 → 디그리 |
| 각도 변환 | `public static double toRadians(double angdeg)` | 디그리 → 라디안 |
| 삼각함수 | `public static double sin(double a)` | 사인 값 (인자는 **라디안**) |
| 삼각함수 | `public static double cos(double a)` | 코사인 값 (인자는 **라디안**) |
| 삼각함수 | `public static double tan(double a)` | 탄젠트 값 (인자는 **라디안**) |
| 로그 | `public static double log(double a)` | 자연로그 값 |
| 거듭제곱 | `public static double pow(double a, double b)` | a의 b 제곱 |

> 💡 **개발 팁 — `tan(45)`가 왜 1이 아니라 0.9999...일까?**
> 실행 결과를 보면 `tan(45)`의 값이 `1.0`이 아닌 `0.9999999999999999`이다. `Math.toRadians(45)`로 얻은 라디안 값 자체가 무리수 π/4를 `double`로 근사한 값이기 때문이다. 20-2에서 본 실수의 오차 문제가 그대로 이어지는 것이므로, **부동소수점 연산 결과는 `==`로 비교하지 말고 오차 범위(예: `Math.abs(a - b) < 1e-9`)로 비교**해야 한다.

## 20-4. 난수의 생성과 Random 클래스

### 난수 생성 — 예제 `P475_RandomNumberGenerator`

- 난수의 생성에는 `java.util.Random` 클래스를 사용한다.
- 인스턴스를 생성한 뒤 `nextInt`, `nextDouble` 등의 메소드를 호출하면 난수를 얻을 수 있다.

```java
import java.util.Random;

public class P475_RandomNumberGenerator {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 7; i++) {
            System.out.println(r.nextInt(1_000));
        }
    }
}
```

- `nextInt(1000)`은 **0 이상 1000 미만**의 정수를 반환한다. (상한 값 자신은 포함되지 않는다.)
- 이 예제는 **실행할 때마다 다른 값**이 출력된다.

| 메소드 | 반환되는 난수 |
|---|---|
| `public boolean nextBoolean()` | boolean형 난수 |
| `public int nextInt()` | int형 난수 |
| `public int nextInt(int bound)` | 0 이상 bound 미만의 int형 난수 |
| `public long nextLong()` | long형 난수 |
| `public float nextFloat()` | 0.0 이상 1.0 미만의 float형 난수 |
| `public double nextDouble()` | 0.0 이상 1.0 미만의 double형 난수 |

### 씨드(Seed) 기반의 난수 생성

- 컴퓨터를 이용한 난수의 생성은 생각보다 어려운 일이다. 컴퓨터는 **알고리즘을 기반으로 일을 하기 때문에** 난수를 생성하는 데에도 숨겨진 패턴이 존재할 수밖에 없다.
- 비록 쉽게 파악할 수 없을지라도 분명 패턴은 존재한다. 그래서 컴퓨터가 생성하는 난수를 가리켜 **'Pseudo-random number(가짜 난수)'** 라 한다.

```java
Random rand = new Random(12);   // 12가 씨드 값
```

- 위 문장에서 `Random`의 생성자에 전달된 숫자 12는 난수의 생성 과정에서 **씨앗으로 사용된다.** (씨앗으로 사용된 이 값을 **'씨드 값(Seed Number)'** 이라 한다.)
- 즉 난수 생성 알고리즘이 이 숫자를 기반으로 돌아가기 때문에, **이 값이 같으면 생성되는 난수의 패턴은 100% 일치한다.**
- 따라서 씨드 값을 고정한 예제는 **몇 번을 실행해도 그 결과가 동일하다.**

```
# 1회차 실행       # 2회차 실행
866                866
812                812
556                556
133                133
624                624
211                211
750                750
```

> **"씨드 값이 같으면 생성되는 난수의 패턴은 100% 일치한다."**

### 매 실행마다 다른 난수를 얻는 방법 — 예제 `SeedSetRandom`

그렇다면 예제 `P475_RandomNumberGenerator`는 어떻게 매 실행 때마다 생성되는 난수의 패턴이 달랐던 것일까? 다음 예제를 보자.

```java
import java.util.Random;

class SeedSetRandom {
    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < 7; i++)
            System.out.println(rand.nextInt(1000));
    }
}
```

- 위 문장에 포함되어 있는 `System.currentTimeMillis()` 메소드 호출문은, 컴퓨터의 현재 시간을 기준으로 **1970년 1월 1일 자정 이후로 지나온 시간을 밀리 초(1/1000초) 단위로 계산하여 반환**한다.
- 따라서 예제를 실행할 때마다 `Random` 인스턴스에 심어지는 **씨드 값은 달라진다.** 그리고 그 결과로 **예측이 훨씬 어려워진 난수**가 만들어졌다.

그럼 `P475_RandomNumberGenerator`처럼 **씨드 값을 전달하지 않은 경우**는 어떨까?

```java
Random rand = new Random();     // 씨드 값을 전달하지 않음
```

이 문장에서 호출하는 생성자는 내부적으로 다음과 같은 방법으로 씨드 값을 설정한다. 즉 **현재 시간을 기준으로 씨드 값을 만들어서, 씨드 값을 인자로 받는 다른 생성자를 호출한다.**

```java
public Random() {
    this(System.currentTimeMillis());   // Random(long seed) 생성자 호출
}
```

- 그래서 이전 예제 `P475_RandomNumberGenerator`는 실행할 때마다 다른 패턴의 난수가 만들어졌던 것이다.
- 그리고 `Random` 인스턴스의 다음 메소드 호출을 통해서 원하면 언제든지 새로운 씨드 값을 지정할 수 있다.

```java
public void setSeed(long seed)
```

> 💡 **개발 팁 — 씨드 고정은 '단점'이 아니라 '기능'이다**
> 씨드를 고정하면 항상 같은 난수가 나온다는 성질은, **테스트 코드를 작성할 때 아주 유용하다.** 난수를 쓰는 로직은 실행할 때마다 결과가 달라져서 검증이 어려운데, `new Random(42)`처럼 씨드를 박아두면 매번 동일한 입력으로 재현 가능한 테스트를 만들 수 있다.
>
> 반대로 **보안이 필요한 곳(비밀번호 초기화 토큰, 세션 ID 등)에는 `Random`을 쓰면 안 된다.** 현재 시간 기반이라 예측이 가능하기 때문이다. 이럴 때는 `java.security.SecureRandom`을 사용한다.

---

## 20-5. 문자열의 토큰(Token) 구분 — StringTokenizer 클래스

### 토큰(Token)과 구분자(Delimiter)

특정 기준을 가지고 문자열을 작게 나누어야 할 때 `StringTokenizer` 클래스를 사용할 수 있다. 예를 들어 다음 문자열이 존재한다고 가정해 보자.

```
"PM:08:45"
```

이는 오후 8시 45분을 의미하는 문자열 정보이다. 그리고 이 문자열을 이루는 정보는 다음 세 가지이다.

```
PM, 08, 45
```

- 이 세 가지 정보가 **콜론을 기준으로** 나뉘어 있다. 즉 위의 문자열에서 콜론은 **'구분자(Delimiter)'** 이다.
- 그리고 구분자를 기준으로 나뉜 문자열 조각은 **'토큰(Token)'** 이다.

이렇듯 콜론을 기준으로 토큰을 추출하는 코드를 작성하는 일은 생각보다 번거롭다. 그러나 `StringTokenizer` 클래스를 이용하면 간단한 일이 되어버린다.

### StringTokenizer의 생성자

```java
public StringTokenizer(String str, String delim)
```

- **첫 번째 인자**로 토큰을 추출할 **문자열**을 전달한다.
- **두 번째 인자**로 **구분자 정보**를 전달하는데, 문자열의 형태로 전달한다.

예를 들어서 앞서 보인 문자열을 콜론을 기준으로 토큰을 추출하려면 다음과 같이 `StringTokenizer` 인스턴스를 생성하면 된다.

```java
StringTokenizer st = new StringTokenizer("PM:08:45", ":");
```

### 구분자는 둘 이상을 둘 수 있다

구분자는 하나가 아니어도 된다. 예를 들어서 다음 문자열에서 **숫자만 뽑아내려 한다면**,

```
"12 + 36 - 8 / 2 = 44"
```

다음과 같이 `StringTokenizer` 인스턴스를 생성하면 된다. 즉 **구분자를 모아서 하나의 문자열로 구성하면 된다.**

```java
StringTokenizer st = new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");
```

- 위 문장의 구분자에는 **공백 문자가 포함되어 있음에 유의**하자. **공백도 다른 문자와 동일하게 구분자로** 취급된다.
- 두 번째 인자로 전달한 `"+-/= "`는 **문자열 하나가 구분자**라는 뜻이 아니라, **그 안의 각 문자 하나하나가 개별 구분자**라는 뜻이다.

### 토큰의 추출 — hasMoreTokens와 nextToken

| 메소드 | 설명 |
|---|---|
| `public boolean hasMoreTokens()` | 반환할 토큰이 남아 있는가? |
| `public String nextToken()` | 다음 토큰을 반환 |

- 토큰을 반환하는 메소드는 `nextToken`이다.
- 그런데 **반환할 토큰이 없는 상태에서 이 메소드가 호출되면 예외가 발생한다.**
- 따라서 `hasMoreTokens` 메소드 호출을 통해서 토큰이 있는지 확인하고, **토큰이 있는 경우에 한해 `nextToken` 메소드를 호출**하도록 코드를 작성해야 한다.

```java
StringTokenizer st = new StringTokenizer("PM:08:45", ":");

while (st.hasMoreTokens())          // 토큰이 남아 있는지 먼저 확인하고
    System.out.println(st.nextToken());   // 있을 때만 꺼낸다
```

### 구분자도 토큰으로 반환받기

- 예제의 실행 결과에서 보이듯이 **구분자는 토큰으로 구분되지 않고 버려진다.**
- 만약에 **구분자도 토큰으로 반환을 받고 싶다면** 다음과 같이 `StringTokenizer` 인스턴스를 생성하면 된다.

```java
StringTokenizer st1 = new StringTokenizer("PM:08:45", ":", true);
//  → 마지막 인자 true는 구분자도 토큰으로 반환하라는 의미
```

### 공백을 구분자에 포함시켜야 하는 이유

그리고 예제에서 토큰을 추출했던 다음 문자열을 다시 보자.

```
"12 + 36 - 8 / 2 = 44"
```

- 이 문자열의 토큰을 나누는 구분자에 **공백 문자를 포함시키지 않는다면**, 숫자만 토큰으로 추출되는 것이 아니라 **숫자의 앞과 뒤에 위치한 공백까지 하나의 토큰으로 묶여서** 추출이 된다.
- 그러나 예제에서는 **공백 문자를 구분자에 포함시켰기 때문에,** 그리고 **구분자는 토큰을 구분하는 목적으로 사용되고 소멸되기 때문에** 숫자만을 토큰으로 추출할 수 있었다.

```java
// 공백을 뺀 경우 → " 12 ", " 36 ", " 8 " 처럼 공백이 붙어서 나온다
new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/=");

// 공백을 포함시킨 경우 → "12", "36", "8" 처럼 숫자만 깔끔하게 나온다
new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");
```

> 💡 **핵심 정리**
> - **구분자(Delimiter)** = 문자열을 나누는 기준이 되는 문자
> - **토큰(Token)** = 구분자를 기준으로 나뉜 문자열 조각
> - 두 번째 인자는 **"구분자로 쓸 문자들의 모음"** 이지, 하나의 구분 문자열이 아니다.
> - `nextToken`은 반드시 **`hasMoreTokens`로 확인한 뒤에** 호출한다. (아니면 예외 발생)
> - 구분자는 **기본적으로 버려진다.** 살리고 싶으면 생성자의 **세 번째 인자에 `true`** 를 전달한다.
