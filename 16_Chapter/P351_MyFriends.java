class UnivFriend {
    private String name;
    private String major;
    private String phone;

    public UnivFriend(String name, String major, String phone) {
        this.name = name;
        this.major = major;
        this.phone = phone;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Major: " + major);
        System.out.println("Phone: " + phone);
    }
}

class CompFriend {
    private String name;
    private String department;
    private String phone;

    public CompFriend(String name, String department, String phone) {
        this.name = name;
        this.department = name;
        this.phone = phone;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Department" + department);
        System.out.println("Phone: " + phone);
    }
}

public class P351_MyFriends {
    public static void main(String[] args) {
        // for Univ Friend
        UnivFriend[] u = new UnivFriend[5];
        int u_cnt = 0;

        // for Company Friend
        CompFriend[] c = new CompFriend[5];
        int c_cnt = 0;

        // store Info into "u"
        u[u_cnt++] = new UnivFriend("Lee", "Computer", "010-1");
        u[u_cnt++] = new UnivFriend("Kim", "Elec", "010-2");

        // store Info into "c"
        c[c_cnt++] = new CompFriend("Yoon", "R&D", "010-3");
        c[c_cnt++] = new CompFriend("Park", "Research", "010-4");

        // print all Info
        int cnt = 0;
        for(UnivFriend i : u) {
            i.showInfo();
            cnt++;
            if(cnt == u_cnt)
                break;
        }
        cnt = 0;
        for(CompFriend i : c) {
            i.showInfo();
            cnt++;
            if(cnt == c_cnt)
                break;
        }
    }
}

/*
 * 위 예제의 main 메소드를 잘 관찰하자. 그러면 다음 사실들을 알 수 있다.
 *
 * - 인스턴스를 저장하는 배열이 두 개이다.
 *
 * - 대학 동창의 정보를 저장하는 과정과 직장 동료의 정보를 저장하는 과정이 나뉜다.
 *   → 저장에 필요한 배열과 변수가 다르기 때문이다.
 *
 * - 저장된 정보를 모두 출력할 때 두 개의 for문을 작성해야 한다.
 *   → 출력에 사용되는 배열과 변수가 다르기 때문이다.
 *
 * 즉 위 예제에서 제공하는 main 메소드의 핵심은 다음과 같다.
 *
 *     "배열이 두 개이므로 무엇을 하건 그 과정이 둘로 나뉜다."
 */