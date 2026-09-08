public class P712_IfElseOptional {
    public static void main(String[] args) {
        ContInfo ci = new ContInfo(null, "ROK");
        String phone;
        String addr;

        if(ci.phone != null)
            phone = ci.getPhone();
        else
            phone = "There is no phone number";

        if(ci.adrs != null)
            addr = ci.getAdrs();
        else
            addr = "There is no address";

        System.out.println(phone);
        System.out.println(addr);
    }
}

class ContInfo {
    String phone;
    String adrs;

    public ContInfo(String phone, String adrs) {
        this.phone = phone;
        this.adrs = adrs;
    }

    public String getPhone () { return phone; }
    public String getAdrs () { return adrs; }
}