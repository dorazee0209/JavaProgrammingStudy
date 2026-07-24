class Friend {
    private String name;
    private String phone;

    public Friend(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("phoen: " + phone);
    }
}

class UnivFriend extends Friend {
    private String major;

    public UnivFriend(String name, String major, String phone) {
        super(name, phone);
        this.major = major;
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Major: " + major);
    }
}

class CompFriend extends Friend {
    private String department;

    public CompFriend(String name, String department, String phone) {
        super(name, phone);
        this.department = department;
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("Department: " + department);
    }
}

public class P353_MyFriend2 {
    public static void main(String[] args) {
        Friend[] f = new Friend[10];
        int cnt = 0;

        f[cnt++] = new UnivFriend("Lee", "CS", "010-1");
        f[cnt++] = new UnivFriend("Kim", "Business", "010-2");
        f[cnt++] = new CompFriend("Yoon", "R&D", "010-3");
        f[cnt++] = new CompFriend("Park", "Research", "010-4");

        int lim = 0;
        for(Friend i: f) {
            i.showInfo();
            lim++;
            System.out.println();
            if(lim == cnt)
                break;
        }
    }
}

/*
 * 위의 예제에서는 UnivFriend 클래스와 CompFriend 클래스가 Friend 클래스를 상속하게 함으로써
 * 다음과 같은 효과를 얻게 되었다.
 *
 * - 인스턴스를 저장하는 배열이 하나이다.
 *   → Friend 클래스를 상속하는 클래스가 더 추가되어도 이 사실은 변함이 없다.
 *
 * - 정보를 저장하는 과정이 나뉘지 않는다.
 *   → 하나의 배열에 모든 인스턴스를 저장할 수 있다.
 *
 * - 저장된 정보를 모두 출력할 때 하나의 for문으로 충분하다.
 *   → 하나의 배열이 사용되었고 또 메소드 오버라이딩이 도움이 되었다.
 *
 * 참고로 Friend 클래스를 재활용된 클래스로 보면 안 된다. UnivFriend 클래스와 CompFriend 클래스
 * 이전에 등장하여 그 자체로 유용하게 사용이 되었다면 그렇게 보아도 된다. 그러나 Friend 클래스의
 * 등장 목적은 다음과 같다. 따라서 재활용의 관점에 부합하지 않는다.
 *
 *     "UnivFriend 클래스와 CompFriend 클래스에 공통 규약을 적용하기 위해 정의된 클래스"
 */