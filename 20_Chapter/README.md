# Chapter 20 — 자바의 기본 클래스

## 20-1. 래퍼 클래스 (Wrapper 클래스)

### 기본 자료형의 값을 감싸는 래퍼 클래스 — 예제 `P443_UseWrapperClass`

`int`형 정수나 `double`형 실수와 같은 **기본 자료형의 값들도 인스턴스로 표현해야 하는 경우**가 있다. 예를 들어 다음 메소드의 인자로 정수 3과 실수 7.15를 전달해야 하는 상황이라고 가정해보자.

```java
public static void showData(Object obj) {
    System.out.println(obj);   // toString 메소드 호출하여 반환되는 문자열 출력
}
```

위 메소드는 **인스턴스를 인자로 요구한다.** 이렇듯 인스턴스의 참조 값을 요구하는 자리에 기본 자료형의 값을 놓아야 하는 경우가 종종 있다. 그리고 이러한 상황에서 필요한 것이 '래퍼 클래스'이다.

> **"래퍼 클래스는 기본 자료형의 값을 감싸는 클래스이다."**

> 💡 **개발 팁 — 컬렉션이 래퍼 클래스를 요구한다**
> 실무에서 래퍼 클래스를 가장 자주 만나는 곳은 `List`, `Map` 같은 **컬렉션 프레임워크**다. 이들은 `Object`를 담도록 설계되어 있어서 `List<int>`는 만들 수 없고 `List<Integer>`로 써야 한다. "인스턴스를 요구하는 자리"의 대표 사례인 셈이다.

#### 기본 자료형별 래퍼 클래스와 생성자

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

### 래퍼 클래스의 두 가지 기능 — 예제 `P444_BoxingUnboxing`

래퍼 클래스의 중요한 기능 두 가지는 다음과 같다. 하나는 **값을 인스턴스로 감싸는 것**이고, 다른 하나는 **인스턴스에서 값을 꺼내는 것**이다.

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

```java
Integer iObj = new Integer(10);          // 박싱
Double dObj = new Double(3.14);          // 박싱

int num1 = iObj.intValue();              // 언박싱
double num2 = dObj.doubleValue();        // 언박싱

// 래퍼 인스턴스 값 증가 방법
iObj = new Integer(iObj.intValue() + 10);
dObj = new Double(dObj.doubleValue() + 1.2);
```

**실행 결과**

```
10
3.14

10
3.14

20
4.34
```

#### 래퍼 인스턴스는 담고 있는 값을 수정하지 못한다

따라서 값의 수정이 필요하면 위 예제의 다음 문장들과 같이 **새로운 래퍼 인스턴스를 생성**해야 한다.

```java
iObj = new Integer(iObj.intValue() + 10);
dObj = new Double(dObj.doubleValue() + 1.2);
```

#### 래퍼 클래스별 언박싱 메소드

| 래퍼 클래스 | 언박싱 메소드 |
|---|---|
| `Boolean` | `public boolean booleanValue()` |
| `Character` | `public char charValue()` |
| `Integer` | `public int intValue()` |
| `Long` | `public long longValue()` |
| `Double` | `public double doubleValue()` |

### 오토 박싱(Auto Boxing) & 오토 언박싱(Auto Unboxing) — 예제 `P446_AutoBoxingUnboxing`

자바 5부터 **박싱과 언박싱이 필요한 상황에서 이를 자동으로 처리**하기 시작했다. 그리고 이를 가리켜 각각 **오토 박싱**, **오토 언박싱**이라 한다.

```java
Integer iObj = 10;      // 오토 박싱 진행
Double dObj = 3.14;     // 오토 박싱 진행

int num1 = iObj;        // 오토 언박싱 진행
double num2 = dObj;     // 오토 언박싱 진행
```

**실행 결과**

```
10
3.14

10
3.14
```

#### 컴파일러가 대신 채워 넣는 문장

위 예제의 다음 두 문장에서는 대입 연산자의 오른편에 각각 `Integer` 인스턴스와 `Double` 인스턴스가 와야 하는데, 이를 대신해서 **정수와 실수가 위치해 있다.** 이러한 상황에서는 정수와 실수를 기반으로 인스턴스가 생성된다. 즉 이 상황에서 **오토 박싱**이 이뤄진다.

| 내가 작성한 문장 | 실제로 실행되는 문장 |
|---|---|
| `Integer iObj = 10;` | `Integer iObj = new Integer(10);` |
| `Double dObj = 3.14;` | `Double dObj = new Double(3.14);` |

반대로 다음 두 문장에서는 대입 연산자의 오른편에 정수와 실수가 와야 하는데, 이를 대신해서 **인스턴스가 위치해 있다.** 이러한 상황에서는 다음과 같이 문장이 수정되어 실행되며, 이를 가리켜 **오토 언박싱**이라 한다.

| 내가 작성한 문장 | 실제로 실행되는 문장 |
|---|---|
| `int num1 = iObj;` | `int num1 = iObj.intValue();` |
| `double num2 = dObj;` | `double num2 = dObj.doubleValue();` |

#### 다양한 문장에서의 오토 박싱과 언박싱 — 예제 `P447_AutoBoxingUnboxing2`

오토 박싱과 오토 언박싱은 **다양한 상황과 문장에서 진행**이 된다.

```java
Integer num = 10;
num++;                      // 오토 박싱, 오토 언박싱 동시 진행
System.out.println(num);

num += 3;                   // 오토 박싱, 오토 언박싱 동시 진행
System.out.println(num);

int r = num + 5;            // 오토 언박싱 진행
Integer rObj = num - 5;     // 오토 언박싱 진행
System.out.println(r);
System.out.println(rObj);
```

**실행 결과**

```
11
14
19
9
```

예제의 다음 두 문장에서는 **오토 박싱과 오토 언박싱이 동시에** 진행이 된다.

| 내가 작성한 문장 | 실제로 실행되는 문장 |
|---|---|
| `num++;` | `new Integer(num.intValue() + 1);` |
| `num += 3;` | `new Integer(num.intValue() + 3);` |

그리고 이렇듯 오토 박싱과 오토 언박싱 덕분에 **`Integer`형 참조변수 `num`을 `int`형 변수처럼 사용**할 수 있게 되었다.

> 💡 **개발 팁 — `new Integer(10)`은 이제 쓰지 않는다**
> 자바 9부터 래퍼 클래스의 생성자는 **deprecated(사용 자제)** 로 지정되었다. 실제로 `new Integer(...)`가 있는 코드를 컴파일하면 *"uses or overrides a deprecated API"* 경고가 뜬다. 오토 박싱을 쓰거나 `Integer.valueOf(10)`을 쓰는 것이 권장되는데, `valueOf`는 자주 쓰이는 작은 값(-128~127)의 인스턴스를 **미리 만들어 재활용**하기 때문에 매번 새로 만드는 생성자보다 효율적이다. 교재의 코드는 개념 설명을 위한 것이니 그대로 이해하되, 실제 코드에서는 오토 박싱을 쓰면 된다.

> ⚠️ **주의 — 오토 언박싱과 `null`**
> 오토 언박싱은 내부적으로 `intValue()` 같은 **메소드 호출**로 바뀐다. 따라서 래퍼 참조변수가 `null`인 상태에서 언박싱이 일어나면 `NullPointerException`이 발생한다. `Integer num = null; int n = num;` 같은 코드가 컴파일은 되지만 실행 중에 터지는 이유다.

## 20-2. BigInteger 클래스와 BigDecimal 클래스

### 오차 없는 실수의 표현을 위한 BigDecimal 클래스 — 예제 `P453_DoubleError`, `P454_WowBigDecimal`

다음과 같이 실수를 **문자열로 전달**하면서 `BigDecimal` 인스턴스를 생성할 수 있다.

```java
BigDecimal d1 = new BigDecimal("1.6");   // 정상적인 방법
BigDecimal d2 = new BigDecimal("0.1");   // 정상적인 방법
```

이와 달리 다음과 같이 실수 1.6과 0.1을 인자로 전달하면서 생성할 수도 있다.

```java
BigDecimal d1 = new BigDecimal(1.6);     // 가능은 하지만
BigDecimal d2 = new BigDecimal(0.1);     // 가능은 하지만
```

그러나 이렇게 인스턴스를 생성할 경우 `BigDecimal` 인스턴스에 저장된 값은 더 이상 1.6과 0.1이 아니다. 실수는 표현되는 순간부터 오차를 지니기 때문이다. 즉 생성자에 전달된 값은 이미 오차가 있는 1.6과 0.1이다. 이는 그 값을 출력해 봄으로써 확인할 수 있다.

```java
BigDecimal d = new BigDecimal(1.6);
System.out.println("오차 있는 1.6 : " + d);
```

> **"오차 없는 값을 지니는 `BigDecimal` 인스턴스를 생성하려면, 그 값을 문자열로 구성해서 전달해야 한다."**

#### BigDecimal의 사칙연산 메소드

`BigInteger` 클래스와 마찬가지로 `BigDecimal` 클래스에도 다음 사칙연산을 포함하여 다양한 연산을 위한 메소드가 정의되어 있다.

| 연산 | 메소드 |
|---|---|
| 덧셈 | `public BigDecimal add(BigDecimal augend)` |
| 뺄셈 | `public BigDecimal subtract(BigDecimal subtrahend)` |
| 곱셈 | `public BigDecimal multiply(BigDecimal multiplicand)` |
| 나눗셈 | `public BigDecimal divide(BigDecimal divisor)` |

## 20-3. Math 클래스와 난수의 생성, 그리고 문자열 토큰(Token)의 구분

### 수학 관련 다양한 연산의 제공을 위한 Math 클래스 — 예제 `P456_SimpleMathUse`

`java.lang.Math` 클래스에는 수학과 관련된 다양한 연산이 **클래스 메소드(static)** 로 정의되어 있다. 따라서 **인스턴스를 생성하지 않고** `Math.메소드이름(...)` 형태로 바로 호출하면 된다. 원주율과 같은 상수도 클래스 변수로 정의되어 있어 `Math.PI`로 접근한다.

```java
System.out.println("원주율: " + Math.PI);
System.out.println("Root 2: " + Math.sqrt(2));
System.out.println("PI's degree: " + Math.toDegrees(Math.PI));

double radian45 = Math.toRadians(45);        // Convert to radians
System.out.println("sin(45): " + Math.sin(radian45));
System.out.println("log(25): " + Math.log(25));
System.out.println("2 to the power of 16: " + Math.pow(2, 16));
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

#### 디그리(Degree)와 라디안(Radian)

사인·코사인·탄젠트 값을 얻기 위해서는 `sin`, `cos`, `tan` 메소드를 호출해야 하는데, 이때 **전달되는 인자는 라디안 단위의 값이어야 한다.** 따라서 이 세 메소드를 호출하기 전에 **'디그리(Degree)' 단위의 값을 '라디안(Radian)' 단위로 변환하는 과정**을 거쳐야 한다.

```java
double radian45 = Math.toRadians(45);   // 45도 → 라디안으로 변환
System.out.println("sin(45): " + Math.sin(radian45));
```

> **"삼각함수 메소드에 전달하는 인자는 디그리가 아닌 라디안 단위의 값이어야 한다."**

반대로 라디안 값을 디그리 값으로 되돌릴 때에는 `Math.toDegrees` 메소드를 사용한다. 그래서 위 예제에서 `Math.toDegrees(Math.PI)`의 결과는 `180.0`이고, `Math.toDegrees(2.0 * Math.PI)`의 결과는 `360.0`이다.

#### 예제에서 사용한 주요 메소드

| 구분 | 메소드 | 설명 |
|---|---|---|
| 상수 | `Math.PI` | 원주율 π (`double`형 클래스 변수) |
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

### 난수(Random Number)의 생성 — 예제 `P457_RandomNumberGenerator`

난수의 생성에는 `java.util.Random` 클래스를 사용한다. 인스턴스를 생성한 뒤 `nextInt`, `nextDouble` 등의 메소드를 호출하면 난수를 얻을 수 있다.

```java
Random r = new Random();
for (int i = 0; i < 7; i++) {
    System.out.println(r.nextInt(1_000));
}
```

`nextInt(1000)`은 **0 이상 1000 미만**의 정수를 반환한다. 상한 값 자신은 포함되지 않는다. 그리고 이 예제는 **실행할 때마다 다른 값**이 출력된다.

| 메소드 | 반환되는 난수 |
|---|---|
| `public boolean nextBoolean()` | boolean형 난수 |
| `public int nextInt()` | int형 난수 |
| `public int nextInt(int bound)` | 0 이상 bound 미만의 int형 난수 |
| `public long nextLong()` | long형 난수 |
| `public float nextFloat()` | 0.0 이상 1.0 미만의 float형 난수 |
| `public double nextDouble()` | 0.0 이상 1.0 미만의 double형 난수 |

### 씨드(Seed) 기반의 난수 생성 — 예제 `P458_PseudoRandom`

컴퓨터를 이용한 난수의 생성은 생각보다 어려운 일이다. 컴퓨터는 **알고리즘을 기반으로 일을 하기 때문에** 난수를 생성하는 데에도 숨겨진 패턴이 존재할 수밖에 없다. 비록 쉽게 파악할 수 없을지라도 분명 패턴은 존재한다. 그래서 컴퓨터가 생성하는 난수를 가리켜 **'Pseudo-random number(가짜 난수)'** 라 한다.

```java
Random rand = new Random(12);   // 12가 씨드 값
```

위 문장에서 생성자에 전달된 숫자 12는 난수의 생성 과정에서 **씨앗으로 사용된다.** 씨앗으로 사용된 이 값을 **'씨드 값(Seed Number)'** 이라 한다. 즉 난수 생성 알고리즘이 이 숫자를 기반으로 돌아가기 때문에, **이 값이 같으면 생성되는 난수의 패턴은 100% 일치한다.** 따라서 씨드 값을 고정한 예제는 **몇 번을 실행해도 그 결과가 동일하다.**

```
[1회차 실행]       [2회차 실행]
866                866
812                812
556                556
133                133
624                624
211                211
750                750
```

> **"씨드 값이 같으면 생성되는 난수의 패턴은 100% 일치한다."**

#### 매 실행마다 다른 난수를 얻는 방법 — 예제 `P459_SeedSetRandom`

그렇다면 예제 `P457_RandomNumberGenerator`는 어떻게 매 실행 때마다 생성되는 난수의 패턴이 달랐던 것일까? 씨드 값으로 **현재 시간**을 넘겨보자.

```java
Random r = new Random(System.currentTimeMillis());
```

`System.currentTimeMillis()` 메소드 호출문은, 컴퓨터의 현재 시간을 기준으로 **1970년 1월 1일 자정 이후로 지나온 시간을 밀리 초(1/1000초) 단위로 계산하여 반환**한다. 따라서 예제를 실행할 때마다 `Random` 인스턴스에 심어지는 **씨드 값은 달라진다.** 그리고 그 결과로 **예측이 훨씬 어려워진 난수**가 만들어졌다.

그럼 `P457_RandomNumberGenerator`처럼 **씨드 값을 전달하지 않은 경우**는 어떨까?

```java
Random r = new Random();     // 씨드 값을 전달하지 않음
```

이 문장에서 호출하는 생성자는 내부적으로 다음과 같은 방법으로 씨드 값을 설정한다. 즉 **현재 시간을 기준으로 씨드 값을 만들어서, 씨드 값을 인자로 받는 다른 생성자를 호출한다.**

```java
public Random() {
    this(System.currentTimeMillis());   // Random(long seed) 생성자 호출
}
```

그래서 이전 예제 `P457_RandomNumberGenerator`는 실행할 때마다 다른 패턴의 난수가 만들어졌던 것이다. 그리고 다음 메소드 호출을 통해서 원하면 언제든지 새로운 씨드 값을 지정할 수 있다.

```java
public void setSeed(long seed)
```

> 💡 **개발 팁 — 씨드 고정은 '단점'이 아니라 '기능'이다**
> 씨드를 고정하면 항상 같은 난수가 나온다는 성질은, **테스트 코드를 작성할 때 아주 유용하다.** 난수를 쓰는 로직은 실행할 때마다 결과가 달라져서 검증이 어려운데, `new Random(42)`처럼 씨드를 박아두면 매번 동일한 입력으로 재현 가능한 테스트를 만들 수 있다.
>
> 반대로 **보안이 필요한 곳(비밀번호 초기화 토큰, 세션 ID 등)에는 `Random`을 쓰면 안 된다.** 현재 시간 기반이라 예측이 가능하기 때문이다. 이럴 때는 `java.security.SecureRandom`을 사용한다.

### 문자열의 토큰(Token) 구분

#### 토큰(Token)과 구분자(Delimiter)

특정 기준을 가지고 문자열을 작게 나누어야 할 때 `StringTokenizer` 클래스를 사용할 수 있다. 예를 들어 다음 문자열이 존재한다고 가정해 보자.

```
"PM:08:45"
```

이는 오후 8시 45분을 의미하는 문자열 정보이다. 그리고 이 문자열을 이루는 정보는 `PM`, `08`, `45` 세 가지이다.

- 이 세 가지 정보가 **콜론을 기준으로** 나뉘어 있다. 즉 위의 문자열에서 콜론은 **'구분자(Delimiter)'** 이다.
- 그리고 구분자를 기준으로 나뉜 문자열 조각은 **'토큰(Token)'** 이다.

이렇듯 콜론을 기준으로 토큰을 추출하는 코드를 작성하는 일은 생각보다 번거롭다. 그러나 `StringTokenizer` 클래스를 이용하면 간단한 일이 되어버린다.

#### StringTokenizer의 생성자

```java
public StringTokenizer(String str, String delim)
```

- **첫 번째 인자**로 토큰을 추출할 **문자열**을 전달한다.
- **두 번째 인자**로 **구분자 정보**를 문자열의 형태로 전달한다.

예를 들어서 앞서 보인 문자열에서 콜론을 기준으로 토큰을 추출하려면 다음과 같이 인스턴스를 생성하면 된다.

```java
StringTokenizer st = new StringTokenizer("PM:08:45", ":");
```

#### 구분자는 둘 이상을 둘 수 있다

구분자는 하나가 아니어도 된다. 예를 들어서 문자열 `"12 + 36 - 8 / 2 = 44"`에서 **숫자만 뽑아내려 한다면**, 다음과 같이 **구분자를 모아서 하나의 문자열로 구성하면 된다.**

```java
StringTokenizer st = new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");
```

- 위 문장의 구분자에는 **공백 문자가 포함되어 있음에 유의**하자. **공백도 다른 문자와 동일하게 구분자로** 취급된다.
- 두 번째 인자로 전달한 `"+-/= "`는 **문자열 하나가 구분자**라는 뜻이 아니라, **그 안의 각 문자 하나하나가 개별 구분자**라는 뜻이다.

#### 공백을 구분자에 포함시켜야 하는 이유

구분자에 **공백 문자를 포함시키지 않는다면**, 숫자만 토큰으로 추출되는 것이 아니라 **숫자의 앞뒤에 위치한 공백까지 하나의 토큰으로 묶여서** 추출이 된다. 그러나 공백 문자를 구분자에 포함시키면, 그리고 **구분자는 토큰을 구분하는 목적으로 사용되고 소멸되기 때문에** 숫자만을 토큰으로 추출할 수 있다.

```java
// 공백을 뺀 경우 → "12 ", " 36 ", " 8 ", " 2 ", " 44" 처럼 공백이 붙어서 나온다
new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/=");

// 공백을 포함시킨 경우 → "12", "36", "8" 처럼 숫자만 깔끔하게 나온다
new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");
```

#### 토큰의 추출 — hasMoreTokens와 nextToken

| 메소드 | 설명 |
|---|---|
| `public boolean hasMoreTokens()` | 반환할 토큰이 남아 있는가? |
| `public String nextToken()` | 다음 토큰을 반환 |

토큰을 반환하는 메소드는 `nextToken`이다. 그런데 **반환할 토큰이 없는 상태에서 이 메소드가 호출되면 예외가 발생한다.** 따라서 `hasMoreTokens` 메소드 호출을 통해서 토큰이 있는지 확인하고, **토큰이 있는 경우에 한해 `nextToken` 메소드를 호출**하도록 코드를 작성해야 한다.

```java
StringTokenizer st = new StringTokenizer("PM:08:45", ":");

while (st.hasMoreTokens())                // 토큰이 남아 있는지 먼저 확인하고
    System.out.println(st.nextToken());   // 있을 때만 꺼낸다
```

#### 구분자 하나와 여럿을 한 번에 — 예제 `P462_TokenizerString`

앞에서 본 두 가지 경우(구분자 하나 / 구분자 여럿)를 한 번에 확인하는 예제이다.

```java
StringTokenizer st1 = new StringTokenizer("PM:08:15", ":");
while(st1.hasMoreTokens())
    System.out.print(st1.nextToken() + ' ');
System.out.println();

StringTokenizer st2 = new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");
while(st2.hasMoreTokens())
    System.out.print(st2.nextToken() + ' ');
System.out.println();
```

**실행 결과**

```
PM 08 15 
12 36 8 2 44 
```

- `st1`은 콜론 하나만을 구분자로 삼아 `PM`, `08`, `15` 세 개의 토큰을 추출했다.
- `st2`는 `"+-/= "`에 담긴 문자들을 구분자로 삼아 **연산자와 공백을 모두 걸러내고 숫자만** 추출했다.
- 두 경우 모두 **구분자 자체는 출력되지 않았다.** 구분자는 토큰을 나누는 데 쓰이고 소멸되기 때문이다.

> 💡 **개발 팁 — `nextToken() + ' '`가 왜 정상 동작할까?**
> `' '`는 문자열 `" "`이 아니라 **`char`형 리터럴**이다. 그런데도 공백이 제대로 붙는 이유는, `+` 연산자의 **왼쪽 피연산자가 `String`(`nextToken()`의 반환값)이기 때문에 문자열 결합으로 처리**되기 때문이다.
> 만약 `System.out.print('a' + ' ')`처럼 양쪽이 모두 `char`라면 **정수 덧셈**이 되어 `129`가 출력된다. 헷갈리기 쉬우므로 문자열을 이어붙일 의도라면 `" "`처럼 **큰따옴표**를 쓰는 편이 안전하다.

#### 구분자도 토큰으로 반환받기

예제의 실행 결과에서 보이듯이 **구분자는 토큰으로 구분되지 않고 버려진다.** 만약에 **구분자도 토큰으로 반환을 받고 싶다면** 다음과 같이 인스턴스를 생성하면 된다.

```java
StringTokenizer st1 = new StringTokenizer("PM:08:45", ":", true);
//  → 마지막 인자 true는 구분자도 토큰으로 반환하라는 의미
```

#### 정리 — 토큰과 구분자

| 항목 | 내용 |
|---|---|
| 구분자(Delimiter) | 문자열을 나누는 기준이 되는 문자 |
| 토큰(Token) | 구분자를 기준으로 나뉜 문자열 조각 |
| 생성자의 두 번째 인자 | **"구분자로 쓸 문자들의 모음"** 이지, 하나의 구분 문자열이 아니다 |
| 호출 순서 | `nextToken`은 반드시 `hasMoreTokens`로 확인한 뒤에 (아니면 예외 발생) |
| 구분자의 운명 | 기본적으로 버려진다. 살리려면 **세 번째 인자에 `true`** |

## 20-4. Arrays 클래스

`java.util.Arrays` 클래스는 배열 조작에 도움을 주는 메소드들로 채워져 있다. 따라서 이 클래스에 정의된 메소드들을 사용하면 배열의 복사, 비교, 정렬 및 탐색과 관련된 코드를 비교적 쉽게 작성할 수 있다.

### 배열의 복사

배열 복사에 사용되는 메소드는 다음과 같다. 기본적으로 모든 기본 자료형 배열에 대해 오버로딩 되어 있으나, 아래에서는 `double`형 배열에 대해 정의된 메소드만 보였다.

#### Arrays.copyOf — 전체 또는 앞에서부터 일부 복사 — 예제 `P464_CopyOfArrays`

```java
public static double[] copyOf(double[] original, int newLength)
```

- **첫 번째 인자**로 복사할 원본 배열을, **두 번째 인자**로 **새로 생성할 배열의 길이**를 전달한다.
- 그리고 **복사된 결과인 새로운 배열의 참조 값을 반환한다.** 즉 원본을 건드리지 않고 새 배열을 만들어 준다.

```java
double[] arOrg = new double[5];     // {1.1, 2.2, 3.3, 4.4, 5.5} 로 채운 뒤

double[] arCpy1 = Arrays.copyOf(arOrg, arOrg.length);   // 배열 전체 복사
double[] arCpy2 = Arrays.copyOf(arOrg, 3);              // 세번째 요소까지만 복사
```

**실행 결과**

```
1.1	2.2	3.3	4.4	5.5	
1.1	2.2	3.3	
```

- `Arrays.copyOf(arOrg, arOrg.length)` → **원본과 길이가 같은 배열**이 만들어지므로 **전체 복사**가 된다.
- `Arrays.copyOf(arOrg, 3)` → **앞에서부터 3개**만 복사된다.
- 즉 `copyOf`는 **항상 인덱스 0번부터** 복사한다. 복사의 **시작 위치를 지정할 수는 없다.**

> 💡 **참고 — 원본보다 긴 길이를 지정하면?**
> 예외가 발생하지 않고, 남는 자리는 **해당 자료형의 기본값으로 채워진다.** (숫자형은 `0`, `boolean`은 `false`, 참조형은 `null`) 배열의 길이를 늘리는 용도로도 쓸 수 있다는 뜻이다.

#### Arrays.copyOfRange — 지정한 구간만 복사 — 예제 `P465_CopyOfArrays`

배열의 **중간 부분만** 복사하고 싶다면 `copyOfRange` 메소드를 사용한다.

```java
public static double[] copyOfRange(double[] original, int from, int to)
```

- **두 번째 인자** `from`은 복사를 **시작할 인덱스**이다. → **포함된다.**
- **세 번째 인자** `to`는 복사를 **끝낼 인덱스**이다. → **포함되지 않는다.**

```java
double[] cpOrg = Arrays.copyOfRange(arOrg, 1, 4);
```

**실행 결과**

```
2.2	3.3	4.4	
```

**[그림 20-2: copyOfRange가 복사하는 구간]**

```
인덱스     0     1     2     3     4
값       1.1   2.2   3.3   4.4   5.5
              └──── 복사 ────┘
               from=1        to=4 (미포함)
```

> **"`from`은 포함되고 `to`는 포함되지 않는다."** → 복사되는 요소의 개수는 `to - from`개이다.

#### System.arraycopy — 이미 존재하는 배열로 복사 — 예제 `P466_CopyOfSystem`

`Arrays`의 메소드들은 **새로운 배열을 만들어서 반환**한다. 반면 **이미 생성되어 있는 배열에 복사**를 하고 싶다면 `java.lang.System` 클래스의 `arraycopy` 메소드를 사용한다.

```java
public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
```

| 매개변수 | 의미 |
|---|---|
| `src` | **복사할 원본** 배열 |
| `srcPos` | 원본 배열에서 **복사를 시작할 인덱스** |
| `dest` | **복사될 대상** 배열 |
| `destPos` | 대상 배열에서 **붙여넣기를 시작할 인덱스** |
| `length` | **복사할 요소의 개수** |

- 반환형이 `void`임에 주목하자. **새 배열을 만들지 않고, 이미 있는 `dest` 배열의 내용을 덮어쓴다.**
- 매개변수형이 `Object`이므로 **모든 자료형의 배열**을 전달할 수 있다.

```java
double[] org = new double[5];       // {1.1, 2.2, 3.3, 4.4, 5.5}
double[] cpy = new double[3];       // 대상 배열을 직접 준비해야 한다

// 배열 org의 idx = 1에서 cpy의 idx = 0으로 3개의 요소를 복사
System.arraycopy(org, 1, cpy, 0, 3);
```

**실행 결과**

```
2.2	3.3	4.4	
```

**[그림 20-3: arraycopy의 복사 동작]**

```
org    1.1   2.2   3.3   4.4   5.5
              └─────┬─────┘  length=3, srcPos=1
                    ↓
cpy          2.2   3.3   4.4         destPos=0
```

#### 세 가지 방법의 비교

| 메소드 | 소속 | 반환값 | 시작 위치 지정 | 대상 배열 |
|---|---|---|---|---|
| `Arrays.copyOf(원본, 길이)` | `java.util.Arrays` | **새 배열** | ❌ (항상 0부터) | 자동 생성 |
| `Arrays.copyOfRange(원본, from, to)` | `java.util.Arrays` | **새 배열** | ⭕ | 자동 생성 |
| `System.arraycopy(src, srcPos, dest, destPos, len)` | `java.lang.System` | `void` | ⭕ | **직접 준비해야 함** |

> 💡 **개발 팁 — 어느 것을 써야 할까?**
> 대부분의 경우 **`Arrays.copyOf` / `copyOfRange`가 더 안전하고 읽기 쉽다.** 대상 배열을 직접 준비할 필요가 없어서 길이 계산 실수로 인한 `ArrayIndexOutOfBoundsException`을 피할 수 있기 때문이다.
> `System.arraycopy`는 **이미 존재하는 배열의 특정 위치에 끼워 넣어야 할 때**(예: 버퍼 재사용, 두 배열 이어 붙이기) 쓴다. 참고로 `Arrays.copyOf`도 내부적으로는 `System.arraycopy`를 호출하므로 **성능 차이는 없다.**

> ⚠️ **주의 — 이 복사는 모두 '얕은 복사(shallow copy)'다**
> 기본 자료형 배열은 값 자체가 복사되므로 문제가 없다. 그러나 **참조형 배열**을 복사하면 **인스턴스가 복사되는 것이 아니라 참조 값(주소)만 복사된다.** 즉 원본 배열과 복사본 배열이 **같은 인스턴스를 가리키게 되므로**, 한쪽에서 인스턴스의 내용을 바꾸면 다른 쪽에도 그대로 반영된다.

### 배열의 비교

배열의 내용 비교에 사용되는 메소드는 다음과 같다. 기본적으로 모든 기본 자료형의 배열에 대해 오버로딩 되어 있으나, 아래에서는 `int`형 배열에 대해 정의된 메소드만 보였다.

```java
public static boolean equals(int[] a, int[] a2)
```

- 매개변수 `a`와 `a2`로 전달된 배열의 내용을 비교하여 `true` 또는 `false`를 반환한다.
- 이 메소드는 두 배열에 저장된 **데이터의 수, 순서, 그리고 내용**이 같을 때 `true`를 반환한다.
- 배열의 길이가 다를 경우에는 `false`를 반환한다.

#### 기본 자료형 배열의 비교 — 예제 `P467_ArrayEquals`

```java
int[] arr1 = new int[5];            // {1, 2, 3, 4, 5} 로 채운 뒤
int[] arr2 = Arrays.copyOf(arr1, arr1.length);

System.out.println(Arrays.toString(arr2));
System.out.println(Arrays.equals(arr1, arr2));
```

**실행 결과**

```
[1, 2, 3, 4, 5]
true
```

`copyOf`로 복사한 배열이므로 데이터의 수·순서·내용이 모두 같아 `true`가 반환되었다.

#### Object형 배열에 대한 오버로딩

그리고 이 메소드는 다음과 같이 `Object`형 배열에 대해서도 오버로딩 되어 있다.

```java
public static boolean equals(Object[] a, Object[] a2)
```

- 이는 **인스턴스의 참조 값을 저장하고 있는 두 배열**에 대해서 비교를 진행한다.
- 그렇다면 어떤 상황에서 `true`를 반환할까? 이 메소드는 **참조 값이 아닌, 참조하는 인스턴스의 내용**을 비교한다.
- 그리고 이때 **`Object` 클래스에 정의된 `equals` 메소드가 사용된다.**

#### 인스턴스 배열의 비교 — 예제 `P467_ArrayObjEquals`

```java
class INum {
    private int num;
    public INum(int num) {
        this.num = num;
    }
}
```

```java
INum[] ar1 = new INum[3];
INum[] ar2 = new INum[3];

for (int i = 0; i < 3; i++) {
    ar1[i] = new INum(i+1);
    ar2[i] = new INum(i+1);
}

System.out.println(Arrays.equals(ar1, ar2));
```

**실행 결과**

```
false
```

위 예제에서는 두 배열에 **동일한 값으로 초기화된 서로 다른 인스턴스**를 각각 저장하였다. 그러나 배열의 비교 결과는 `false`이다. 이유는 `Object` 클래스에 정의되어 있는 `equals` 메소드가 다음과 같이 정의되어 있기 때문이다.

```java
public boolean equals(Object obj) {
    if(this == obj)        // 두 인스턴스가 동일 인스턴스이면
        return true;
    else
        return false;
}   // 이렇듯 Object 클래스에 정의된 equals 메소드는 참조 값 비교를 한다.
```

따라서 배열의 **참조 값 비교가 아닌 내용 비교가 목적이라면**, 다음과 같이 `equals` 메소드를 목적에 맞게 **오버라이딩** 해야 한다.

#### equals를 오버라이딩한 후의 비교 — 예제 `P469_ArrayObjEquals2`

앞의 예제에서 `main`은 그대로 두고, `INum` 클래스에 `equals` 오버라이딩만 추가하였다.

```java
class INum {
    private int num;
    public INum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals (Object obj) {
        if(this.num == ((INum)obj).num)     // 두 인스턴스의 내용 비교
            return true;
        else
            return false;
    }
}
```

**실행 결과**

```
true
```

`equals`를 내용 비교로 오버라이딩하자, 같은 배열 구성에 대해 결과가 `false`에서 `true`로 바뀌었다.

> 💡 **개발 팁 — 라이브러리가 내 코드를 거꾸로 호출한다**
> `Arrays.equals`는 `INum`이라는 클래스가 존재하는지도 모른다. JDK가 만들어질 때는 있지도 않았기 때문이다. 그런데도 `INum`의 비교 규칙대로 동작하는 이유는, **`Object` 타입으로 받아두고 `equals`를 호출하면 실제 객체의 오버라이딩된 버전이 불리는** 다형성 덕분이다.
> 그래서 `equals`를 오버라이딩하지 않으면 `Arrays.equals`뿐 아니라 `List.contains`, `HashMap`의 키 검색까지 모두 **주소 비교로 헛돌게** 된다. 더불어 `equals`를 오버라이딩할 때는 `hashCode`도 함께 재정의하는 것이 자바의 규약이다.

### 배열의 정렬

배열의 정렬에 사용되는 메소드는 다음과 같다. 기본적으로 모든 기본 자료형의 배열에 대해 오버로딩 되어 있으나, 아래에서는 `int`형 배열에 대해 정의된 메소드만 보였다.

```java
public static void sort(int[] a)
```

- 매개변수 `a`로 전달된 배열을 **오름차순(Ascending Numerical Order)** 으로 정렬한다.
- 즉 **위치상 뒤로 갈수록 큰 값이 저장되도록** 정렬을 한다.

#### 기본 자료형 배열의 정렬 — 예제 `P470_ArraySort`

```java
int[] ar1 = {1, 5, 4, 2, 3};
double[] ar2 = {3.3, 2.2, 5.5, 1.1, 4.4};

Arrays.sort(ar1);
Arrays.sort(ar2);

System.out.println(Arrays.toString(ar1));
System.out.println(Arrays.toString(ar2));
```

**실행 결과**

```
[1, 2, 3, 4, 5]
[1.1, 2.2, 3.3, 4.4, 5.5]
```

#### Object형 배열에 대한 오버로딩

그리고 이 메소드는 다음과 같이 `Object` 배열에 대해서도 오버로딩 되어 있다.

```java
public static void sort(Object[] a)
```

이는 **인스턴스의 참조 값을 저장하고 있는 배열**에 대한 정렬을 진행한다. 그렇다면 순서상 인스턴스의 앞서고 뒤섬은 어떻게 판단을 할까? 이는 다음 인터페이스의 구현을 기반으로 **프로그래머가 클래스 별로 결정**하게 되어 있다.

```java
interface Comparable
```

> → `int compareTo(Object o)` 메소드 구현을 통해 인스턴스의 **순서 판단 기준을 결정**

#### compareTo 메소드의 구현 방법

위 인터페이스에 존재하는 추상 메소드 `compareTo`의 구현 방법은 다음과 같다.

| 상황 | 반환값 |
|---|---|
| 인자로 전달된 `o`가 **작다면** | **양의 정수** 반환 |
| 인자로 전달된 `o`가 **크다면** | **음의 정수** 반환 |
| 인자로 전달된 `o`와 **같다면** | **`0`** 반환 |

인스턴스 대상의 크고 작음의 의미는 **오름차순 정렬 이후의 인스턴스 위치**를 의미한다. 예를 들어서 인스턴스 A와 Z를 비교하여 A가 작다면, 오름차순 정렬 이후에 **A는 Z의 앞에 위치**해야 한다.

#### 인스턴스 배열의 정렬 — 예제 `P472_ArrayObjSort`

구현에 앞서 **크고 작음에 대한 판단 기준을 결정해야 한다.** 예를 들어 다음과 같이 결정했다고 가정해보자.

> **"나이가 어린 인스턴스의 크기가 작은 것으로 결정한다."**

그렇다면 다음과 같이 `Comparable` 인터페이스를 구현해야 한다.

```java
class Person implements Comparable {
    String name;
    int age;

    @Override
    public String toString() {
        return name + ":" + age;
    }

    @Override
    public int compareTo(Object o) {
        if(this.age > ((Person)o).age)
            return 1;                  // 인자로 전달된 o가 작다면 양의 정수 반환
        else if(this.age < ((Person)o).age)
            return -1;                 // 인자로 전달된 o가 크다면 음의 정수 반환
        else
            return 0;                  // 인자로 전달된 o와 같다면 0을 반환
    }
}
```

```java
Person[] ar = {
    new Person("Lee", 17),
    new Person("Goo", 35),
    new Person("Soo", 5)
};

Arrays.sort(ar);
```

**실행 결과**

```
Before sorting: 
Lee:17
Goo:35
Soo:5

After sorting: 
Soo:5
Lee:17
Goo:35
```

`Person` 클래스 내에서 `compareTo` 메소드를 구현하였으니, `Arrays.sort` 메소드 내에서는 **이 메소드의 호출 결과로 반환되는 값을 기준으로 정렬**을 진행한다.

#### 뺄셈으로 간단히 정의하기

예제의 `compareTo` 메소드는 다음과 같이 간단하게 정의할 수도 있다.

```java
@Override
public int compareTo(Object o) {
    Person p = (Person)o;
    return this.age - p.age;
}   // 예제에서 정의한 compareTo 메소드와 사실상 동일
```

이렇게 정의를 해도 앞서 언급한 `compareTo` 메소드의 정의 기준을 완전히 만족한다. 나이가 크면 양수, 작으면 음수, 같으면 `0`이 자연스럽게 나오기 때문이다.

> ⚠️ **주의 — 같을 때 반드시 `0`을 반환해야 한다**
> `compareTo`는 *"같으면 0"* 을 지켜야 하는 **계약(contract)** 이다. 같은 경우에 `0`이 아닌 값을 반환하면, 값이 겹치지 않는 데이터에서는 문제없이 동작하다가 **값이 겹치는 순간 정렬이 어긋나거나** `IllegalArgumentException: Comparison method violates its general contract!` 예외가 발생한다. 자바의 정렬 알고리즘이 이 계약을 전제로 최적화되어 있기 때문이다.

> 💡 **개발 팁 — 뺄셈 방식의 함정: 오버플로우**
> `return this.age - p.age;`는 간결하지만, 두 값의 차가 `int`의 범위를 넘으면 **부호가 뒤집힌다.** 나이처럼 작은 값에서는 안전하지만, 큰 정수를 비교할 때는 위험하다. 그래서 실무에서는 `Integer.compare(a, b)`를 쓰는 것이 관례다. 뺄셈 없이 부호만 판단해주기 때문에 오버플로우가 원천적으로 없다.

> 💡 **개발 팁 — `Comparable`은 제네릭으로 쓰는 것이 요즘 방식**
> 교재의 `implements Comparable`은 **raw type**이라 `compareTo(Object o)`로 받아 매번 `(Person)o` 형변환을 해야 한다. `implements Comparable<Person>`으로 쓰면 `compareTo(Person o)`가 되어 **형변환이 사라지고**, 엉뚱한 타입이 들어오는 실수를 컴파일 시점에 잡을 수 있다. 제네릭은 21·22장에서 자세히 다룬다.
