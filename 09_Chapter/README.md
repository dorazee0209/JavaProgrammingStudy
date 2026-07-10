# Chapter 09 — 정보 은닉 그리고 캡슐화

## 게터(Getter)와 세터(Setter)
메소드 `setRad`는 '값의 설정'을 위한 메소드이고, 메소드 `getRad`는 '값의 참조'를 위한 메소드이다. 이렇듯 값의 설정과 참조를 위한 메소드를 가리켜 각각 다음과 같이 부른다.

- **게터(Getter)**
  → 인스턴스 변수의 값을 **참조**하는 용도로 정의된 메소드
  → 변수의 이름이 `name`일 때, 메소드의 이름은 `getName`으로 짓는 것이 관례
- **세터(Setter)**
  → 인스턴스 변수의 값을 **설정**하는 용도로 정의된 메소드
  → 변수의 이름이 `name`일 때, 메소드의 이름은 `setName`으로 짓는 것이 관례

## 접근 제어자 (Access Modifier)
클래스의 정의를 대상으로는 다음 두 가지 선언이 가능하다.

- 클래스 정의 대상: `public`, `default`

클래스에는 `protected`, `private`를 붙일 수 없다. 클래스 자체를 `protected`나 `private`로 제한해버리면 그 클래스를 외부에서 아예 쓸 수 없게 되어 의미가 없어지기 때문이다.

그리고 인스턴스 변수와 메소드를 대상으로는 다음 네 가지 선언이 모두 가능하다.

- 인스턴스 변수와 메소드 대상: `public`, `protected`, `private`, `default`

접근 가능한 범위는 다음과 같다 (좁은 것 → 넓은 것 순).

- `private` → 같은 클래스 내부에서만
- `default` (아무것도 안 붙임) → 같은 패키지 내에서만
- `protected` → 같은 패키지 + 상속받은 자식 클래스
- `public` → 어디서든

접근 허용 범위를 부등식으로 나타내면 다음과 같다.

```
public > protected > default > private
```

## printResult가 static인 이유

```java
public class P198_InfoHideCircle {
    static void main(String[] args) {
        Circle c = new Circle(1.5);
        printResult(c);

        c.setRad(3.4);
        printResult(c);
    }

    public static void printResult(Circle c) {
        System.out.println("Rad = " + c.getRad());
        System.out.println("Area = " + c.getArea());
    }
}
```

`main`(static 메소드) 안에서 `printResult(c)`를 **객체 없이 이름만으로** 호출하고 있다. 이게 가능한 이유는 `printResult`도 `static`이기 때문이다.

- static 메소드 안에서는 `this`(자기 자신을 가리키는 참조)가 존재하지 않는다. static 메소드는 특정 객체에 속하지 않고 객체 생성 전에도 호출될 수 있어서, "지금의 나(this)"라고 할 대상 자체가 없기 때문이다.
- non-static(인스턴스) 메소드는 반드시 `객체.메소드()` 형태로, 객체를 통해서만 호출할 수 있다.
- 만약 `printResult`에서 `static`을 빼면 `main`(static)이 `this` 없이 non-static 메소드를 부르려는 셈이 되어 `non-static method printResult(Circle) cannot be referenced from a static context` 컴파일 에러가 난다.
- `this.printResult(c)`로 고쳐써도 안 되는 건 마찬가지다. `this`는 "지금 실행 중인 메소드가 속한 객체 자기 자신"을 가리키는 참조인데, `main`은 static이라 애초에 속한 객체가 없다. 즉 `this`라는 이름의 변수 자체가 `main` 안에는 존재하지 않으므로 `this.printResult(c)`도 `non-static variable this cannot be referenced from a static context` 컴파일 에러가 난다.

## 정리

1. **클래스 내에 선언하는 변수, static 붙이면?** → static을 붙여서 선언한 변수는 **클래스 변수**(모든 객체가 공유), 붙이지 않은 변수는 **인스턴스 변수**(객체마다 별도)다.
2. **static 변수엔 `this`를 못 붙여?** → non-static 메소드 안에서라면 `this.count`처럼 써도 문법상 컴파일은 된다(다만 스타일상 비권장, `ClassName.count`가 더 명확한 표기). 진짜 못 쓰는 경우는 **static 메소드 안**이다 — 거긴 `this` 자체가 없어서, static 변수든 아니든 상관없이 `this`를 쓰는 순간 에러가 난다.
3. **`this`는 클래스가 아니라 인스턴스 관련 키워드?** → 맞다. `this`는 항상 "지금 이 코드를 실행 중인 특정 객체(인스턴스)"를 가리키며, 클래스라는 설계도 자체를 가리키지 않는다. 그래서 인스턴스가 없는 static 컨텍스트에는 `this`가 존재할 수 없다.
4. **static + non-static 변수를 통틀어 부르는 이름은?** → 클래스 안, 메소드 밖에 선언하는 변수들을 통틀어 **필드(field)** 또는 **멤버 변수(member variable)**라고 부른다. 그 안에서 다시 클래스 변수(static)와 인스턴스 변수(non-static)로 나뉜다. 메소드 안에서 선언하는 변수는 이와 구분해서 **지역 변수(local variable)**라고 부른다.
