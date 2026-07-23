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