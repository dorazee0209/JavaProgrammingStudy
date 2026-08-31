# Chapter 26 — 네스티드 클래스와 람다(Lambda)의 소개

람다는 자바 8에서 처음 소개되었음에도 불구하고 자바 문법에서 매우 중요한 위치를 차지한다. 그래서 네스티드 클래스를 람다의 공부를 위한 사전 지식 정도로 오해하는 경우가 있다. 그러나 네스티드 클래스는 그 자체로 중요한 의미를 갖는다.

## 26-1. 네스티드(Nested) 클래스와 이너(Inner) 클래스

다음과 같이 클래스 안에 또 다른 클래스를 정의할 수 있다.

```java
class Outer {
    class Nested {...}   // 네스티드 클래스
}
```

이렇게 클래스 내에 정의된 클래스를 가리켜 '네스티드 클래스(Nested Class)'라 하고, 이를 감싸는 클래스를 가리켜 '외부 클래스(Outer Class)'라 한다.

### 네스티드 클래스의 구분

기본적으로 클래스 내에 정의되는 모든 클래스를 가리켜 '네스티드 클래스'라 하는데, 네스티드 클래스는 `static`의 선언 여부를 기준으로 다음과 같이 나뉜다.

- Static 네스티드 클래스
- Non-static 네스티드 클래스

그리고 이 중에서 Non-static 네스티드 클래스를 가리켜 '이너(Inner) 클래스'라 한다. 즉 이 둘을 코드로 정리하면 다음과 같다.

```java
class OuterClass {
    static class StaticNestedClass {...}   // Static 네스티드 클래스
}

class OuterClass {
    class InnerClass {...}   // Non-static 네스티드 클래스, 이너 클래스
}
```

그리고 이너 클래스는 정의되는 위치나 특성에 따라 다시 세 종류로 나뉜다.

- 멤버 이너 클래스 (Member Inner Class)
- 로컬 이너 클래스 (Local Inner Class)
- 익명 이너 클래스 (Anonymous Inner Class)

이들은 중간에 위치한 '이너'를 생략하고 각각 다음과 같이 부르는 것이 일반적이다.

> 멤버 클래스, 로컬 클래스, 익명 클래스

> 💡 **개발 팁 — 클래스를 안에 넣는다는 것은 "이건 바깥에서 쓸 게 아니다"라는 선언이다**
> 네스티드 클래스는 문법적 장식이 아니라 **범위(scope)를 좁히는 도구**다. 특정 클래스 하나를 돕기 위해서만 존재하는 보조 클래스를 최상위(top-level)로 꺼내 두면, 패키지 전체가 그 이름을 볼 수 있게 되고 "이건 어디서 쓰는 클래스지?"를 매번 추적해야 한다. 안으로 넣어 두면 소속이 코드 구조 자체로 드러나고, 필요하면 `private`으로 완전히 감출 수도 있다. 캡슐화는 필드에만 적용하는 개념이 아니라 **클래스라는 단위에도 똑같이 적용된다**는 것이 네스티드 클래스의 출발점이다.

### Static 네스티드 클래스 (Static Nested Class) — 예제 `P643_StaticNested`

Static 네스티드 클래스는 `static` 선언이 갖는 특성이 반영된 클래스이다. 따라서 자신을 감싸는 외부 클래스의 인스턴스와 상관없이 Static 네스티드 클래스의 인스턴스 생성이 가능하다.

```java
class Outer {
    private static int num = 0;
    static class Nested1 {   // Static 네스티드 클래스
        void add(int n) { num += n; }
    }
    static class Nested2 {   // Static 네스티드 클래스
        int get() { return num; }
    }
}

class StaticNested {
    public static void main(String[] args) {
        Outer.Nested1 nst1 = new Outer.Nested1();
        nst1.add(5);

        Outer.Nested2 nst2 = new Outer.Nested2();
        System.out.println(nst2.get());
    }
}
```

실행 결과

```
5
```

위의 `Nested1`, `Nested2` 클래스 내에서는 `Outer`의 static 멤버 `num`에 접근하고 있다. `private`으로 선언되어 있어도 접근이 가능하다. 따라서 `Outer`의 static 멤버 `num`은 `Nested1`과 `Nested2`의 모든 인스턴스가 공유하게 된다. 그리고 이것이 'Static 네스티드 클래스'가 갖는 주요 특징이다.

Static 네스티드 클래스의 인스턴스 생성문은 다음과 같다. 이렇듯 외부 클래스의 이름을 포함하는 형태로 인스턴스의 생성이 이뤄져야 한다.

```java
Outer.Nested1 nst1 = new Outer.Nested1();
Outer.Nested2 nst2 = new Outer.Nested2();
```

그리고 예제에서 보였듯이 Static 네스티드 클래스의 인스턴스 생성은 외부 클래스의 인스턴스 생성과 무관하다. 외부 클래스의 인스턴스를 생성하지 않고도 Static 네스티드 클래스의 인스턴스 생성이 가능하다는 뜻이다. 때문에 이를 근거로 다음 사실을 유추할 수 있다.

> "Static 네스티드 클래스 내에서 외부 클래스의 인스턴스 변수와 메소드에 접근 불가능하다."

즉 Static 네스티드 클래스 내에서는 외부 클래스에 `static`으로 선언된 변수와 메소드에만 접근이 가능하다.

> 💡 **개발 팁 — 멤버 클래스는 되도록 `static`으로**
> 바깥 인스턴스에 접근할 일이 없다면 네스티드 클래스에는 `static`을 붙이는 것이 정석이다(『이펙티브 자바』의 유명한 항목이기도 하다). `static`이 빠진 네스티드 클래스는 자신을 만들어 준 **외부 클래스 인스턴스를 향한 숨은 참조를 내부에 계속 들고 다니는데**, 이 참조는 소스에 보이지 않으면서도 실제로 메모리를 붙잡는다. 그래서 네스티드 클래스의 인스턴스가 오래 살아남는 구조(콜백 등록, 리스너, 캐시)에서는 정작 다 쓴 외부 객체가 GC되지 못하고 남는 메모리 누수의 고전적인 원인이 된다. `static` 한 글자는 문법 취향이 아니라 **의존 관계를 끊는 선택**이다.
