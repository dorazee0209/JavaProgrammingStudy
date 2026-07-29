# Chapter 18 — 예외처리(Exception Handling)

> **예외(Exception)** 란 실행 중에 발생하는 '정상적이지 않은 상황'이다.
> 자바는 이를 **인스턴스**로 만들어 전달하고, 우리는 `try ~ catch`문으로 그것을 받아 처리한다.

| # | 주제 | 예제 파일 |
|---|---|---|
| 1 | `try ~ catch`문의 기본 구조 | `P384_ExceptionCase` |
| 2 | catch 영역은 메소드처럼 동작한다 | `P387_ExceptionCase2` |
| 3 | try로 감싸야 할 영역의 결정 | `P389_ExceptionCase3` · `P391_ExceptionCase4` |
| 4 | 둘 이상의 예외를 처리하기 위한 구성 | `P392_ExceptionCase5` · `P392_ExceptionCase6` |
| 5 | `Throwable` 클래스와 예외처리의 책임 전가 | `P394_ExceptionMessage` · `P395_ExceptionMessage2` |
| 6 | 예외 클래스의 구분 (3분류) | `P396_ArrayIndexOutOfBounds` · `P397_ClassCase` · `P398_NullPointer` |
| 7 | `Exception`을 상속하는 예외의 처리 **의무** | `P401_IOExceptionCase` · `P401_IOExceptionCase2` · `P402_IOExceptionCase3` |
| 8 | `finally` 구문 | `P410_FinallyCase` · `P411_FinallyCase2` |
| 9 | `try-with-resources` 구문 | `P412_TryWithResources` |

## 1. try ~ catch문의 기본 구조

예외를 처리할 때에는 `try ~ catch`문을 사용하며, 이 문장의 기본 구조는 다음과 같다.

```java
try {
    ...관찰 영역...
}
catch(Exception name) {
    ...처리 영역...
}
```

- `try ~ catch`문은 **try 영역**과 **catch 영역**으로 구분이 된다.
- 이 둘은 **하나의 문장**이므로 항상 연결되어 있어야 한다.

> 📌 **try 영역에서 발생한 예외 상황을 catch 영역에서 처리한다.**

## 2. catch 영역은 메소드처럼 동작한다

자세히 보면 catch 영역은 그 생김새가 **메소드와 유사**하다. 그리고 실제로 메소드처럼 동작한다.

```java
try {
    ...관찰 영역...
}
catch(ArithmeticException e) {
    ...처리 영역...
}
```

| 단계 | 무슨 일이 일어나는가 |
|---|---|
| ① | try 영역 실행 중 예외 상황이 만들어진다 |
| ② | **가상머신이 `ArithmeticException` 인스턴스를 생성**한다 |
| ③ | 이 인스턴스가 **메소드를 호출하듯이** catch 구문의 매개변수 `e`에 전달된다 |
| ④ | 가상머신은 **catch 안에서 무엇을 하든 상관없이** 예외가 처리된 것으로 간주하고 실행을 이어간다 |

## 3. try로 감싸야 할 영역의 결정

숫자 2의 위치에서 예외가 발생하고 catch 영역에서 처리가 되면,

```java
try {
    1.  ...
    2.  예외 발생 지점
    3.  ...
}
catch(Exception e) {
    ...
}
4. 예외 처리 이후 실행 지점
```

- 숫자 **3의 위치에서 실행을 이어가는 것이 아니라**, `try ~ catch`문 전체를 건너뛰어 **숫자 4의 위치**에서 실행을 이어가게 된다.
- 이러한 예외 처리 이후의 실행 특성은, **관련이 있는 작업들을 하나로 묶는** 기준이 된다.

### 어디까지 하나의 작업인가

예제에서 입력의 오류로 인한 `InputMismatchException` 예외가 발생할 수 있는 문장은 다음 둘이다.

```java
int n1 = kb.nextInt();
int n2 = kb.nextInt();
```

따라서 이들 각각에 대해 `try ~ catch`문을 구성하는 것도 생각해 볼 수 있다.
그러나 변수 `n1`의 값이 적절히 들어오지 않는다면, 변수 `n2`의 값을 입력받는 것도, `/` 연산을 진행하는 것도 의미가 없다.
즉 다음 문장들은 **하나의 작업**으로 볼 수 있다.

```java
System.out.print("a/b...a? ");    // 이 문장은 '하나의 작업'에서 제외 가능
int n1 = kb.nextInt();
System.out.print("a/b...b? ");
int n2 = kb.nextInt();
System.out.printf("%d / %d = %d \n", n1, n2, n1 / n2);
```

> 💡 이를 진행하는 과정에서 **어느 한 곳에서 예외가 발생하면 나머지 부분을 건너뛰는 것이 적절**하다.

## 4. 둘 이상의 예외를 처리하기 위한 구성

앞서 제시한 나눗셈 관련 예제에서는 다음 두 가지 예외의 발생 가능성이 있다.

```
java.lang.ArithmeticException
java.util.InputMismatchException
```

> ⚠️ 교재에는 `java.lang.InputMismatchException`으로 적혀 있으나, 실제 패키지는 **`java.util`**이다.
> `import java.util.InputMismatchException;`이 필요하다.

### 방법 ① catch를 여러 개 나열

```java
catch(ArithmeticException e) {
    e.getMessage();
}
catch(InputMismatchException e) {
    e.getMessage();
}
```

### 방법 ② 하나의 catch로 묶기 (multi-catch)

`|` 기호로 나열한다.

```java
try {
    System.out.print("a/b...a? ");
    int n1 = kb.nextInt();
    System.out.print("a/b...b? ");
    int n2 = kb.nextInt();
    System.out.printf("%d / %d = %d \n", n1, n2, n1/n2);
}
catch(ArithmeticException | InputMismatchException e) {
    e.getMessage();
}

System.out.println("Good bye~~!");
```

## 5. Throwable 클래스와 예외처리의 책임 전가

자바의 최상위 클래스인 `java.lang.Object`를 제외하고, 예외 클래스의 최상위 클래스는 다음과 같다.

```
java.lang.Throwable        예외 클래스의 최상위 클래스
```

이 클래스에는 발생한 예외의 정보를 알 수 있는 메소드가 정의되어 있는데, 대표적인 메소드 둘은 다음과 같다.

| 메소드 | 설명 |
|---|---|
| `public String getMessage()` | 예외의 **원인**을 담고 있는 문자열을 반환 |
| `public void printStackTrace()` | 예외가 발생한 **위치**와 **호출된 메소드**의 정보를 출력 |

### 넘어오는 예외를 처리하기

`md1`으로부터 넘어오는 예외를 처리하려면, **`md1`의 호출문을 `try ~ catch`문으로 감싸면 된다.**

```java
try {
    md1(3);     // md1으로부터 예외가 넘어온다.
}
catch(Throwable e) {
    e.printStackTrace();
}
```

- 그런데 실제 넘어오는 예외는 `Throwable`이 아니다.
- 그러나 **모든 예외 클래스는 `Throwable`을 상속**하므로, 상속 관계에 의해 `md2`에서 발생한 예외를 위와 같이 처리할 수도 있다.

> ⚠️ **단, 이는 좋은 예외처리의 예는 아니다.** 무엇이 잘못됐는지 구분할 수 없기 때문이다.

### 실행 결과에서 확인할 수 있는 것

- 예외가 처리되고 나니 **`try ~ catch`문 다음에 위치한 문장이 실행**된다.
- catch 구문에서 호출한 `printStackTrace` 메소드의 출력 내용은, 앞서 **가상머신이 예외를 처리할 때 출력한 문장과 유사**하다.
- 사실 가상머신도 예외의 처리 과정에서 프로그램을 종료하기 전에 예외 인스턴스의 `printStackTrace` 메소드를 호출한다.

## 6. 예외 클래스의 구분

예외 클래스의 최상위 클래스가 `Throwable`임은 앞서 설명하였다.
그런데 이를 상속하는 예외 클래스는 다음과 같이 **세 부류**로 나뉜다.

```
java.lang.Object
  └ java.lang.Throwable
      ├ java.lang.Error                  ①
      └ java.lang.Exception              ②
           └ java.lang.RuntimeException  ③
```

| 부류 | 성격 | 예외처리 |
|---|---|---|
| ① `Error` 계열 | 가상머신·하드웨어 수준의 심각한 오류 | **의미 없음** (처리 대상 아님) |
| ② `Exception` 계열 (③ 제외) | 외부 환경의 문제 | **의무** — 안 하면 컴파일 오류 |
| ③ `RuntimeException` 계열 | 프로그래머의 실수 | **선택** |

### ① Error 클래스를 상속하는 예외

| 예외 클래스 | 발생 상황 |
|---|---|
| `VirtualMachineError` | 가상머신에 심각한 오류 발생 |
| `IOError` | 입출력 관련해서 코드 수준 복구가 불가능한 오류 발생 |

자바 프로그램이 파일에 저장된 데이터를 읽는 중에 갑자기 하드디스크에 물리적 오류가 발생하여
더 이상 데이터를 읽을 수 없는 상황이 생길 수 있다. 이러한 수준의 상황에서 발생하는 것이 `IOError` 예외이다.

> ⚠️ **`Error` 클래스를 상속하는 예외는 처리의 대상이 아니다.**
> 바꾸어 말하면 **처리할 수 있는 예외가 아니다.** 따라서 이런 유형의 예외가 발생하면
> 그냥 프로그램이 종료되도록 놔두고, 이후에 원인을 파악하는 과정이 이어져야 한다.

> 🔬 **실측 보충** — 여기서 "처리할 수 없다"는 말은 **문법이 막는다는 뜻이 아니다.**
> `catch(StackOverflowError e)`도 컴파일되고 실제로 잡히기까지 한다.
> 다만 그 시점엔 가상머신이 이미 정상 상태가 아니라서 **잡아 봐야 할 수 있는 일이 없다.**
> 그래서 "처리 대상이 아니다"라고 하는 것이다.

우리가 흔히 보는 `StackOverflowError`, `OutOfMemoryError`가 바로 `VirtualMachineError`의 자식이다.

```
StackOverflowError ↑ VirtualMachineError ↑ Error ↑ Throwable
OutOfMemoryError   ↑ VirtualMachineError ↑ Error ↑ Throwable
IOError                                  ↑ Error ↑ Throwable
```

### ② Exception 클래스를 상속하는 예외

`RuntimeException` 계열을 제외한 나머지로, **처리하지 않으면 컴파일이 되지 않는다.**

| 예외 클래스 | 발생 상황 |
|---|---|
| `IOException` | 입출력 과정에서 문제 발생 |
| `FileNotFoundException` | 열려는 파일이 존재하지 않음 |
| `ClassNotFoundException` | 찾으려는 클래스가 존재하지 않음 |

→ 자세한 내용은 **7번 항목**에서 이어진다.

### ③ RuntimeException 클래스를 상속하는 예외

**앞서 보였던 모든 예외 클래스가 바로 이 예외에 해당한다.**
자바에서 발생시키는 예외의 종류는 다양하며, 그 수만큼 예외 클래스도 다양하게 정의되어 있다.

**배열 관련 예외**

| 예외 클래스 | 발생 상황 | 실제 메시지 예 |
|---|---|---|
| `ArrayIndexOutOfBoundsException` | 배열의 범위를 벗어난 인덱스 접근 | `Index 5 out of bounds for length 3` |
| `NegativeArraySizeException` | 배열 생성 시 길이를 음수로 지정 | `-1` |
| `ArrayStoreException` | 배열에 적절치 않은 인스턴스를 저장 | `java.lang.Integer` |

**그 외 자주 만나는 예외**

| 예외 클래스 | 발생 상황 | 실제 메시지 예 |
|---|---|---|
| `NullPointerException` | `null`인 참조변수로 멤버에 접근 | `Cannot invoke "String.length()" because "s" is null` |
| `ClassCastException` | 허용되지 않는 형변환(다운캐스팅) 시도 | `class java.lang.String cannot be cast to class java.lang.Integer` |
| `ArithmeticException` | **정수**를 0으로 나눔 | `/ by zero` |
| `NumberFormatException` | 숫자로 바꿀 수 없는 문자열을 변환 | `For input string: "abc"` |
| `StringIndexOutOfBoundsException` | 문자열의 범위를 벗어난 인덱스 접근 | `Index 10 out of bounds for length 3` |
| `InputMismatchException` | `Scanner` 입력이 기대한 타입과 불일치 | `null` |
| `IndexOutOfBoundsException` | 컬렉션(`List` 등)의 범위를 벗어난 접근 | `Index 0 out of bounds for length 0` |
| `UnsupportedOperationException` | 지원하지 않는 연산 요청(예: 불변 리스트 수정) | `null` |

> ⚠️ `getMessage()`가 항상 내용을 담고 있는 것은 아니다. `InputMismatchException`처럼 **`null`을 반환**하는 경우도 있다.

> 🔬 **메시지는 환경에 따라 조금씩 다르다** (위 표는 OpenJDK 26 실측값)
> - `NullPointerException` — 변수명(`"s"`)이 나오려면 **`javac -g`** 로 컴파일해야 한다.
>   IntelliJ는 기본으로 켜주지만, 터미널에서 그냥 `javac`로 하면 `"<local1>"`처럼 슬롯 번호가 나온다.
> - `ClassCastException` — 실제로는 뒤에 `(... are in module java.base of loader 'bootstrap')`가 더 붙는다.

> 💡 **0으로 나누기는 정수일 때만 예외다.**
> 실수 연산은 예외 없이 특수한 값을 반환한다 — `3.0 / 0.0` → `Infinity`, `0.0 / 0.0` → `NaN`

위 표의 예외는 **모두 `RuntimeException`의 자손**이다. 다만 바로 아래 자식인 것도 있고, 중간 단계를 거치는 것도 있다.

```
ArithmeticException             ↑ RuntimeException
ArrayIndexOutOfBoundsException  ↑ IndexOutOfBoundsException ↑ RuntimeException
StringIndexOutOfBoundsException ↑ IndexOutOfBoundsException ↑ RuntimeException
NumberFormatException           ↑ IllegalArgumentException  ↑ RuntimeException
InputMismatchException          ↑ NoSuchElementException    ↑ RuntimeException
```

## 7. Exception을 상속하는 예외 클래스의 예외처리

`try ~ catch`문을 **지워서 예외처리를 생략한 것뿐인데 컴파일 오류가 발생**한다.
그것도 `IOException` 예외 발생이 가능한 문장 — 정확히는 **예외 발생 가능성이 있는 '메소드 호출문'** 에서 오류가 난다.

```
error: unreported exception IOException; must be caught or declared to be thrown
        writer = Files.newBufferedWriter(file);
                                        ^
```

앞서 언급한 `Error` 계열이나 `RuntimeException` 계열은 예외의 처리가 **선택**이다.
그러나 `Exception`을 상속하지만 **`RuntimeException`은 상속하지 않는** 예외는,
`try ~ catch`문으로 처리하거나 **다른 영역으로 넘긴다고 반드시 명시**해야 한다.

### 왜 '메소드 호출문'에서 오류가 날까

예외를 실제로 던지는 쪽은 내가 쓴 문장이 아니라 **내가 호출한 메소드**다.
그 메소드가 자기 선언부에 "나는 이 예외를 던질 수 있다"고 **미리 명시**해 두었기 때문에,
컴파일러가 호출한 쪽을 보고 "그럼 너는 이걸 어떻게 할 건데?"라고 묻는 것이다.

```java
// java.nio.file.Files 의 실제 선언
public static BufferedWriter newBufferedWriter(Path path, OpenOption... options)
        throws IOException     // ← 이 표시 때문에 호출한 쪽이 처리를 강제받는다
```

> 💡 **선택지는 두 개뿐** — ① `try ~ catch`로 **직접 처리**하거나, ② `throws`로 **책임을 넘기거나**.
> ②는 앞서 본 "예외처리의 책임 전가"와 같은 이야기다.

> 🔤 **`unreported`** = "보고되지 않은". 컴파일러 입장에선 예외를 처리하든 넘기든 **의사 표시(report)** 를 하라는 뜻이다.

### 왜 자바는 예외처리를 강제하는가

앞으로 배우게 될 메소드 중에는 예외를 전달하는 메소드의 수가 적지 않은데, 그 메소드들이 전달하는 예외 대부분은 `Exception`을 상속한다.
따라서 이러한 메소드의 호출을 위해서는 예외를 던지거나, 처리하는 코드를 넣어주어야 한다.

> 📌 **이것이 자바에서 예외처리가 필수인 이유이다.**

### 보충 — Checked 예외와 Unchecked 예외

위 ②와 ③을 부르는 다른 이름이며, **처리를 강제하느냐**로 나눈 것이다.

| 구분 | Checked 예외 | Unchecked 예외 |
|---|---|---|
| 위치 | `Exception`의 자식 (단, `RuntimeException` 계열 제외) | `RuntimeException`의 자식 |
| 처리 | **컴파일러가 강제** | 강제하지 않음 |
| 안 하면 | **컴파일 에러** | 컴파일은 통과, 실행 중 종료 |
| 성격 | 외부 환경 문제 (파일 없음, 네트워크 끊김) | 프로그래머의 실수 (`null` 참조, 범위 초과) |
| 예 | `IOException`, `FileNotFoundException` | `NullPointerException`, `ArithmeticException` |

> 📌 6번 항목까지 다룬 예외들은 **모두 Unchecked 예외**다. 그래서 `try ~ catch` 없이도 컴파일이 되었던 것이다.

## 8. finally 구문

`try ~ catch`문에는 **`finally`** 블록을 추가로 붙일 수 있다.

```java
try {
    ...
}
catch(IOException e) {
    e.printStackTrace();
}
finally {
    ...
}
```

> 📌 **실행의 흐름이 `try` 안으로 들어오면 `finally` 구문은 반드시 실행된다.**

즉 try 영역에서 예외가 발생하든 안 하든, catch에서 예외를 처리하든 안 하든 **무조건 실행**되는 영역이다.
그래서 파일을 닫는 `close()`처럼 **예외 발생 여부와 무관하게 꼭 실행되어야 하는 마무리 작업**을 넣기에 적합하다.

### finally 안에서도 예외처리가 필요할 수 있다

`close()` 메소드의 호출문에서도 `IOException` 예외가 발생할 수 있다.
따라서 `finally` 블록 안에 `close()`를 그냥 넣으면 **컴파일 오류**가 발생한다. (`P410_FinallyCase`)

```java
finally {
    if(writer != null)
        writer.close();        // ❌ error: unreported exception IOException
}
```

그래서 `finally` 구문을 다음과 같이 수정해야 한다. (코드가 복잡해지더라도 어쩔 수 없다.) (`P411_FinallyCase2`)

```java
finally {
    try {
        if(writer != null)
            writer.close();    // IOException 발생 가능
    }
    catch(IOException e) {
        e.printStackTrace();
    }
}
```

이렇듯 **`finally` 내에서도 `try ~ catch`문을 작성할 수 있으며, 이 상황에서 이는 선택이 아니라 필수다.**
(`close()`가 `IOException`을 던질 수 있는 메소드이기 때문 — 7번 항목에서 본 "의무" 규칙이 그대로 적용된다.)

> 💡 실제로 이러한 코드 구성이 **이전에는 최선이었다.** 그다음 항목이 이 문제를 해결한다.

## 9. try-with-resources 구문

앞서 다음 문장이 정상적으로 실행이 되면,

```java
writer = Files.newBufferedWriter(file);
```

다음 문장(`writer.close();`)도 반드시 실행되어야 한다고 설명하였다. 이러한 스타일의 코드 구성은 빈번하게 등장한다.
그런데 앞서 보였듯이 `finally` 구문으로 이를 처리할 경우 코드가 복잡해진다.
그러나 **자바 7에서 `try-with-resources`문**이 추가되어 이러한 코드의 구성이 단순해졌다.

### 기본 구성

```java
try( resource ) {
    ...
}
catch(Exception name) {
    ...
}
```

- `catch` 구문은 **선택**이다. 필요 없으면 생략할 수 있다. (다만 대부분 필요로 하기 때문에 기본 구성에 포함시켰다.)
- `try` 뒤의 소괄호는 **메소드처럼 보이지만 메소드가 아니고, 메소드와 유사하게 동작하지도 않는다.**
- 소괄호 안(`resource` 위치)에서는 **종료의 과정을 필요로 하는 리소스를 생성**할 수 있다.
- 이 리소스는 `try-with-resources`문을 **빠져나오면서 자동으로 종료**된다.

### 8번 예제와 비교하면

앞의 `P411_FinallyCase2`와 **내용이 완전히 동일**하다. 그러나 코드가 매우 간결해졌다.

```java
try(BufferedWriter writer = Files.newBufferedWriter(file)) {
    writer.write('A');
    writer.write('Z');
}
catch(IOException e) {
    e.printStackTrace();
}
```

이로써 참조변수 `writer`가 참조하는 인스턴스의 종료는 신경 쓰지 않아도 된다.
try 안에서 예외가 발생하건 안 하건 `writer`를 대상으로 한 다음 메소드의 호출은 보장되기 때문이다.

```java
writer.close();    // 직접 이 문장을 넣지 않아도 된다.
```

### AutoCloseable 인터페이스

그렇다면 리소스의 종료 관련 메소드가 `close`인 경우에만 자동으로 호출이 될까? 이에 대한 답은 다음 인터페이스에 있다.

```
java.lang.AutoCloseable
```

이 인터페이스는 `try-with-resources`문에 의해 자동으로 종료되어야 할 리소스 관련 클래스가 **반드시 구현해야 하는 인터페이스**다.
(예제에서 보인 `BufferedWriter` 클래스도 이 인터페이스를 구현하고 있다.) 그리고 이 인터페이스에는 다음 추상 메소드가 존재한다.

```java
void close() throws Exception
```

즉 `try-with-resources`문에서 호출하는 메소드는 **`AutoCloseable` 인터페이스의 `close` 메소드**다. 따라서 `close` 이외의 메소드 호출을 기대하는 것은 곤란하다.

> 🔤 **`AutoCloseable`** = "스스로(Auto) 닫을(Closeable) 수 있는". 이름 자체가 인터페이스의 역할을 그대로 설명한다.

### 리소스가 둘 이상이라면

세미콜론(`;`)으로 리소스를 구분하면 된다.

```java
try( resource1; resource2 ) {
    ...
}
catch(Exception name) {
    ...
}
```

> 📌 예외처리가 포함된 예제를 앞으로 자주 접하게 될 텐데, `try-with-resources`문을 쓸 수 있는 상황에서는 이를 적극 사용할 예정이다. 코드가 훨씬 간결해지니 쓰지 않을 수가 없다.

## 📎 참고 — 예외처리는 성능의 저하로 이어진다

교재의 설명은 다음과 같다.

> `try` 구문 안에 위치한 코드는 `try` 구문 밖에 위치한 코드에 비해 실행 속도가 느리다.
> 따라서 과도한 예외처리는 심각한 성능의 저하로 이어질 수 있다. 그러므로 예외처리가 불필요한 코드를 `try` 구문 안에 두는 일을 자제해야 한다.

참고로 모든 예외를 민감하게 처리하는 프로그램은 생각보다 많지 않다. 규모가 클수록, 성능이 중요시될수록 `try ~ catch`문 이외의 다양한 방법으로 그리고 선별적으로 예외를 처리한다.

### 🔬 직접 측정해보면 — 느려지는 건 try가 아니라 throw

**동일한 로직을 `try` 안팎에 두고 바이트코드를 비교하면 명령어가 완전히 같다.**
`try`는 실행 명령이 아니라 **Exception table 이라는 별도의 표**로만 기록되기 때문이다.

```
static int outside(int[]);        static int inside(int[]);
   0: iconst_0                       0: iconst_0
   ...  (동일)  ...                  ...  (동일)  ...
  23: ireturn                       23: ireturn
                                  Exception table:               ← try는 여기에만 기록된다
                                    from  to  target  type
                                       0  23      24  RuntimeException
```

실제로 2,000,000회 반복 측정한 결과도 같은 이야기를 한다.

| 경우 | 소요 시간 |
|---|---|
| `try` 없음 | 0.9 ms |
| `try` 있음 — **예외 안 던짐** | **0.9 ms** (차이 없음) |
| `try` 있음 — **예외 던짐** | **1789.3 ms** (약 2,000배) |

> 📌 **비용은 `try`로 감싸는 순간이 아니라, 예외를 실제로 `throw` 하는 순간에 발생한다.**
> 예외 인스턴스를 만들 때 **호출 스택 전체를 캡처**(`printStackTrace`가 출력하는 그 정보)하기 때문이다.

> 💡 그래서 결론은 이렇게 바꿔 읽는 게 정확하다 —
> "**`try`를 아끼라**"가 아니라 "**예외를 흐름 제어에 쓰지 말라**".
> 정상적으로 자주 일어나는 상황(예: 입력값 검증)을 예외로 처리하면 그때 진짜로 느려진다.
> 그런 건 `if`문으로 미리 거르는 것이 맞다.

## 📌 정리

| # | 핵심 | 내용 |
|---|---|---|
| 1 | **구조** | `try`(관찰) + `catch`(처리)는 **하나의 문장** |
| 2 | **동작** | 가상머신이 예외 **인스턴스를 생성**해 catch의 매개변수로 전달 |
| 3 | **흐름** | 예외 발생 시 try의 **나머지 전체를 건너뛰고** `try ~ catch` 다음으로 |
| 3 | **묶는 기준** | "하나가 실패하면 나머지도 의미 없는" 문장들 = **하나의 작업** |
| 4 | **여러 예외** | catch를 나열하거나, `catch(A \| B e)`로 묶기 |
| 5 | **최상위** | 모든 예외의 조상은 `java.lang.Throwable` |
| 6 | **3분류** | `Error`(처리 불가) / `Exception`(처리 의무) / `RuntimeException`(처리 선택) |
| 7 | **처리 의무** | Checked 예외는 `try ~ catch` 또는 `throws` **둘 중 하나 필수** |
| 8 | **finally** | try 진입 시 **무조건 실행** — 내부에 예외 가능성이 있으면 **자체 try~catch 필요** |
| 9 | **try-with-resources** | 소괄호 안 리소스를 **자동으로 `close()`** — 리소스는 `AutoCloseable` 구현 필수 |
| 📎 | **성능** | 비용은 `try`가 아니라 **`throw`** 에서 발생 — 예외를 흐름 제어에 쓰지 말 것 |
