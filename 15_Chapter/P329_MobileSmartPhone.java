class MobilePhone {
    protected String number;

    public MobilePhone(String num) {
        number = num;
    }

    public void answer() {
        System.out.println("Hi~ from " + number);
    }
}

class SmartPhone extends MobilePhone {
    private String androidVer;

    public SmartPhone(String num, String ver) {
        super(num);
        androidVer = ver;
    }

    public void palyApp() {
        System.out.println("App is running in " + androidVer);
    }
}

public class P329_MobileSmartPhone {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone("010-123", "Nougat");
        phone.answer();
        phone.palyApp();
    }
}

