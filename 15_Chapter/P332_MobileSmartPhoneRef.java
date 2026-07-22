class MobilePhone {
    private String num;

    public MobilePhone(String number) {
        this.num = number;
    }

    public void answer() {
        System.out.println("Hi~ from " + num);
    }
}

class SmartPhone extends MobilePhone{
    private String version;

    public SmartPhone(String num, String version) {
        super(num);
        this.version = version;
    }
    public void playApp() {
        System.out.println("App is running in " + version);
    }
}

public class P332_MobileSmartPhoneRef {
    public static void main(String[] args) {
        SmartPhone p1 = new SmartPhone("011-123", "iOS");
        MobilePhone p2 = new SmartPhone("010-123", "Mac");

        p1.answer();
        p1.playApp();
        System.out.println();
        p2.answer();
//        p2.playApp();
    }
}