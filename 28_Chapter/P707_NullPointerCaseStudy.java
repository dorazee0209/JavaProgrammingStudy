import java.util.Comparator;
import java.util.function.Consumer;

public class P707_NullPointerCaseStudy {
    public static void showCompAddr(Friend f) { // print address of friend's company
        String addr = null;

        if(f != null) {
            Company com = f.getCmp();
            if(com != null) {
                ContInfo info = com.getCInfo();
                if(info != null)
                    addr = info.getAddr();
            }
        }

        if(addr != null)
            System.out.println(addr);
        else
            System.out.println("There's no address info.");
    }

    public static void main(String[] args) {
        ContInfo ci = new ContInfo("321-444-577", "ROK");
        Company cp = new Company("Yaho Co., Ltd.", ci);
        Friend f = new Friend("Lee Su", cp);

        showCompAddr(f);
    }
}

class Friend {
    String name;
    Company cmp; // could be null

    public Friend(String name, Company cmp) {
        this.name = name;
        this.cmp = cmp;
    }

    public String getName() { return name; }
    public Company getCmp() { return cmp; }
}

class Company {
    String cName;
    ContInfo contInfo; // could be null

    public Company(String cName, ContInfo contInfo) {
        this.cName = cName;
        this.contInfo = contInfo;
    }

    public String getCName() { return cName; }
    public ContInfo getCInfo() { return contInfo; }
}

class ContInfo {
    String phone; // null 일 수 있음
    String addr; // null 일 수 있음

    public ContInfo(String phone, String addr) {
        this.phone = phone;
        this.addr = addr;
    }

    public String getPhone() { return phone; }
    public String getAddr(){ return addr; }
}