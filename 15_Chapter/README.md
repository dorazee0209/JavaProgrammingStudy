# Chapter 15 — 캐스트 연산자의 우선순위

## 상속의 이유이자 목적

> **연관된 일련의 클래스들에 대해 공통적인 규약을 정의할 수 있습니다.**

## `.`(멤버 접근) vs `(Type)`(캐스트) — 무엇이 먼저 결합되는가 (`P346_Wrapping.java`)

- Java는 `.`(멤버 접근)이 `(Type)`(캐스트)보다 **더 강하게 결합**한다.
- 그런데 **캐스트 표현식은 그 자체로 단독 문장(statement)이 될 수 없다**는 규칙이 Java에 있다. (허용되는 문장은 대입문, `++`/`--`, 메소드 호출, 객체 생성 등)

```java
public static void wrapBox(Box box) {
    if (box instanceof PaperBox) {
        ((PaperBox) box).paperWrap();   // ✅ box를 먼저 PaperBox로 캐스팅 → 그 결과에 paperWrap() 호출

        // (PaperBox) box.paperWrap();  // ❌ (PaperBox)(box.paperWrap())로 파싱됨
                                          //     캐스트가 리턴값에 걸리는 데다, 캐스트 표현식은
                                          //     단독 문장이 될 수 없어서 "not a statement" 컴파일 에러
    }
}
```

- 메소드가 `Object`(또는 상위 타입)를 반환하는 경우라면, **리턴값을 캐스팅**하는 건 흔한 정상 패턴이다 — 단, 대입문처럼 "표현식"의 일부로 쓰여야 한다.

```java
class Box {
    public Object makeSomething() {   // 반환형이 Object
        return new PaperBox();         // 실제로는 PaperBox를 만들어 Object로 업캐스팅해 반환
    }
}

PaperBox pb = (PaperBox) box.makeSomething();  // ✅ 리턴값(Object)을 PaperBox로 캐스팅해서 대입 — 정상 동작
```

## `instanceof`와 상속 — 상위 타입을 먼저 검사하면 안 되는 이유 (`P346_Wrapping.java`)

- `instanceof`는 "정확히 이 타입"이 아니라 **"이 타입이거나 그 자손 타입이면"** true를 반환한다.
- `Box` ← `PaperBox` ← `GoldPaperBox` 상속관계를 포함관계(벤다이어그램)로 그리면:

```
┌───────────────────────────────┐
│ Box                            │
│  ┌───────────────────────────┐ │
│  │ PaperBox                   │ │
│  │  ┌─────────────────────┐   │ │
│  │  │ GoldPaperBox         │   │ │
│  │  └─────────────────────┘   │ │
│  └───────────────────────────┘ │
└───────────────────────────────┘
```

- `GoldPaperBox` 객체는 원 안에 겹겹이 들어있으므로 `instanceof GoldPaperBox` / `instanceof PaperBox` / `instanceof Box` **셋 다 true**다.

### ❌ 버그: 바깥쪽(Box)부터 검사
```java
if (box instanceof Box) {           // Box, PaperBox, GoldPaperBox 전부 여기 걸려버림
    box.simpleWrap();
}
else if (box instanceof PaperBox) { // 영영 도달 못함
    ((PaperBox)box).paperWrap();
}
else {                              // 이것도 도달 못함
    ((GoldPaperBox)box).goldWrap();
}
```
→ 어떤 타입을 넣어도 가장 바깥쪽 조건이 항상 먼저 걸려서 `Simple Wrapping`만 세 번 출력됨.

### ✅ 수정: 가장 안쪽(자손)부터 검사
```java
if (box instanceof GoldPaperBox) {        // 가장 구체적인 타입 먼저
    ((GoldPaperBox)box).goldWrap();
}
else if (box instanceof PaperBox) {
    ((PaperBox)box).paperWrap();
}
else {
    box.simpleWrap();
}
```

- **원칙**: `instanceof` 체인은 항상 **자손(안쪽 원) → 조상(바깥쪽 원)** 순서로 검사해야 한다.
