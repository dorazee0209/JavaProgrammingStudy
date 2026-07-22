# Chapter 15 — 다형성과 형변환

## 참조변수의 타입이 접근 가능한 멤버를 결정한다
- 실제 인스턴스가 무엇이든 상관없이, **접근 가능한 멤버는 참조변수의 선언된 타입 기준으로 컴파일 시점에 결정**된다.
- 하위 클래스에서 새로 추가한 멤버는 상위 타입 참조변수로는 접근할 수 없다 (컴파일 에러).
- 단, **오버라이딩된 메소드**는 다르다 — 참조변수 타입이 상위 타입이어도, 실행 시점에는 **실제 객체의 오버라이딩된 버전**이 호출된다 (동적 바인딩).

```java
Object obj = new StrawberryCheeseCake();
obj.딸기치즈케익전용메소드();  // ❌ 컴파일 에러 — Object엔 그런 멤버가 없음
```

## instanceof와 다운캐스팅 (`P344_YummmyCakeOf.java`)
- `Object[]` 배열처럼 실제 타입을 알 수 없는 참조를 다룰 때, `instanceof`로 먼저 실제 타입을 확인하고 **다운캐스팅**해야 하위 타입 전용 멤버에 접근할 수 있다.

```java
for (Object i : ary) {
    if (i instanceof Cake) {
        System.out.println("케익 인스턴스 or");
    }
    if (i instanceof StrawberryCheeseCake) {
        StrawberryCheeseCake sc = (StrawberryCheeseCake) i;  // 다운캐스팅
        // sc.딸기치즈케익전용메소드();  // 캐스팅 후에는 접근 가능
    }
}
```

## Object 클래스가 제공하는 메소드
- `Object`에는 인스턴스 변수(필드)가 없다 — 그래서 변수는 "가리기(hiding)"만 가능하고 오버라이딩 대상 자체가 아니다.
- 메소드 중 일부는 `final`이라 오버라이딩이 원천 차단된다.

| 메소드 | 오버라이딩 가능? |
|---|---|
| `toString()` | ✅ |
| `equals(Object obj)` | ✅ |
| `hashCode()` | ✅ |
| `clone()` (protected) | ✅ |
| `finalize()` (protected, deprecated) | ✅ |
| `getClass()` | ❌ (`final`) |
| `wait()` / `wait(long)` / `wait(long, int)` | ❌ (`final`) |
| `notify()` / `notifyAll()` | ❌ (`final`) |

### 오버라이딩은 선택 사항이다
상속만 받고 오버라이딩하지 않아도, 조상(`Object`)의 기본 구현을 그대로 사용할 수 있다.

```java
StrawberryCheeseCake sc = new StrawberryCheeseCake();
System.out.println(sc.toString());  // StrawberryCheeseCake@251a69d7 (Object의 기본 구현)
System.out.println(sc.equals(sc));  // true (기본은 == 와 동일한 참조 비교)
```

## 캐스트 연산자의 우선순위 (`P346_Wrapping.java`)
- `.` (멤버 접근)이 `(Type)` (캐스트)보다 **더 강하게 결합**한다.
- 그래서 캐스팅 대상이 "참조변수 자신"인지 "메소드 리턴값"인지에 따라 괄호 위치가 달라져야 한다.

```java
public static void wrapBox(Box box) {
    if (box instanceof PaperBox) {
        ((PaperBox) box).paperWrap();   // ✅ box를 먼저 PaperBox로 캐스팅 → paperWrap() 호출
        // (PaperBox) box.paperWrap();  // ❌ (PaperBox)(box.paperWrap())로 파싱됨
                                          //     캐스트 표현식은 단독 문장이 될 수 없어 컴파일 에러
    }
}
```

- 참고로 **메소드의 리턴값을 캐스팅**하는 것은 흔한 정상 패턴이다 — 단, 대입문 등 "표현식"의 일부로 쓰여야 한다.

```java
// makeSomething()이 Object를 리턴한다고 할 때
PaperBox pb = (PaperBox) box.makeSomething();  // ✅ 리턴값을 캐스팅해서 대입
```

## 정리
| 표현 | 파싱 결과 | 유효 여부 |
|---|---|---|
| `(PaperBox)box.paperWrap();` | `(PaperBox)(box.paperWrap())` | ❌ 캐스트 표현식은 단독 문장 불가 |
| `((PaperBox)box).paperWrap();` | `box`를 먼저 캐스팅 → 결과에 메소드 호출 | ✅ |
| `Type v = (Type) obj.methodReturningObject();` | 캐스트가 메소드 **리턴값**에 적용 | ✅ 대입문 안에서는 정상 |
