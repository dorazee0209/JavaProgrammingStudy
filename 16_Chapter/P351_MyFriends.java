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