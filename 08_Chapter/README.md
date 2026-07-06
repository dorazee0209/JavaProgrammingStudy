# Chapter 08 — 패키지와 클래스 패스

## 소스파일과 public 클래스 규칙
- 하나의 소스파일에는 public으로 선언된 클래스의 정의를 하나만 둘 수 있다.
- 소스파일의 이름은 public으로 선언된 클래스의 이름과 동일해야 한다.

## 패키지 이름 규칙
- 클래스의 이름과 구분이 되도록 패키지의 이름은 모두 소문자로 구성한다.
- 인터넷 도메인 이름의 역순으로 패키지 이름을 구성한다.
- 패키지 이름의 끝에 클래스를 정의한 주체 또는 팀을 구분하는 이름을 추가한다.

### 예시 (p185~190): 이름 충돌이 발생하는 두 클래스의 등장

인터넷 도메인 주소가 wxfx.com인 회사에서 원의 넓이 관련 클래스를 개발하였다.

```java
// Circle.java (wxfx.com 회사에서 개발)
public class Circle {      // 원의 넓이 관련 클래스의 정의
    double rad;
    final double PI;

    public Circle(double r) {
        rad = r;
        PI = 3.14;
    }
    public double getArea() {
        return (rad * rad) * PI;   // 원의 넓이 반환
    }
}
```

다음 클래스도 이번 프로그램 개발에 있어서 필요한 클래스이다. 이 클래스는 인터넷 도메인 주소가 fxmx.com인 회사에서 개발한 클래스이다.

```java
// Circle.java (fxmx.com 회사에서 개발)
public class Circle {      // 원의 둘레 관련 클래스 정의
    double rad;
    final double PI;

    public Circle(double r) {
        rad = r;
        PI = 3.14;
    }
    public double getPerimeter() {
        return (rad * 2) * PI;     // 원의 둘레를 반환
    }
}
```

위의 두 클래스 정의는 개별적으로 보았을 때 문제가 없다. 그러나 이를 동시에 사용해야 하는 상황에서는 클래스 이름 Circle의 중복이 문제가 된다. 위의 클래스를 제공한 두 회사는 위치적으로나 업무적으로나 관련이 없는 회사이기에 이러한 문제는 충분히 발생할 수 있다.

두 Circle 클래스 모두 public으로 선언되어 있기 때문에 소스파일의 이름도 둘 다 Circle.java로 동일할 수밖에 없다. 따라서 이 둘을 저장할 때에는 각각 다른 위치에 저장해야 한다.

**패키지 선언은 다음 두 가지 특성을 만들어낸다.**
- 클래스 접근 방법의 구분 — 서로 다른 패키지의 두 클래스는 인스턴스 생성 시 사용하는 이름이 다르다.
- 클래스의 공간적인 구분 — 서로 다른 패키지의 두 클래스 파일은 저장되는 위치가 다르다.

**패키지 이름 결정** (위의 규칙을 적용하면)
- wxfx.com 회사의 smart팀에서 개발한 클래스 → `com.wxfx.smart`
- fxmx.com 회사의 simple팀에서 개발한 클래스 → `com.fxmx.simple`

**패키지 선언 추가** (소스파일 상단에 삽입)

```java
// Circle.java
package com.wxfx.smart;    // 패키지 선언

public class Circle {
    double rad;
    final double PI;

    public Circle(double r) {
        rad = r;
        PI = 3.14;
    }
    public double getArea() {
        return (rad * rad) * PI;
    }
}
```

```java
// Circle.java
package com.fxmx.simple;   // 패키지 선언

public class Circle {
    double rad;
    final double PI;

    public Circle(double r) {
        rad = r;
        PI = 3.14;
    }
    public double getPerimeter() {
        return (rad * 2) * PI;
    }
}
```

두 파일의 이름이 동일하므로 소스파일의 저장 위치를 달리할 수밖에 없다.
- 패키지 `com.wxfx.smart`의 `Circle.java` 저장 위치 → `C:\PackageStudy\src\circle1`
- 패키지 `com.fxmx.simple`의 `Circle.java` 저장 위치 → `C:\PackageStudy\src\circle2`

**패키지로 묶으면 인스턴스 생성 방법이 다음과 같이 달라진다.**
```
com.wxfx.smart.Circle c1 = new com.wxfx.smart.Circle(3.5);
com.fxmx.simple.Circle c2 = new com.fxmx.simple.Circle(5.5);
```

**클래스 파일이 저장되는 위치도 다음과 같이 달라진다.**
- 패키지 `com.wxfx.smart`의 `Circle.class` 저장 위치 → `…\com\wxfx\smart`
- 패키지 `com.fxmx.simple`의 `Circle.class` 저장 위치 → `…\com\fxmx\simple`

정리해보면, 동일한 이름의 두 클래스 파일이 패키지 선언으로 인해 **물리적으로도 분리**가 되었다.

## 패키지의 컴파일 (javac -d)
```
javac -d <directory> <filename>
   → <directory>  패키지를 생성할 위치 정보
   → <filename>   컴파일할 파일의 이름
```

## 참고: 직접 디렉토리를 생성해서 클래스 파일을 가져다 놓아도 됩니다
패키지 com.wxfx.smart로 묶인 Circle 클래스를 컴파일하니 패키지 이름과 동일한 이름 및 경로의
디렉토리들이 생성되었다. 컴파일 과정에서 옵션 -d를 삽입한 결과이다. 그러나 프로그래머가 직접
패키지 이름과 동일한 경로의 디렉토리를 만들고 -d 옵션 없이 컴파일 하여 얻은 클래스 파일을 해당
디렉토리에 가져다 놓아도 된다. 그렇게 패키지를 묶어도 된다.
