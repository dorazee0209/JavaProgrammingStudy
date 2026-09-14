import java.util.Optional;

public class P718_MapElseOptional {
    public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.of(new ContInfo(null, "ROK"));

        String phone = ci.map(c -> c.getPhone()).orElse("No phone number");
        String adrs = ci.map(c -> c.getAdrs()).orElse("No address");

        System.out.println(phone);
        System.out.println(adrs);
    }
}

class ContInfo {
    String phone; // could be null
    String adrs; // could be null

    public ContInfo(String phone, String adrs) {
        this.phone = phone;
        this.adrs = adrs;
    }
    public String getPhone() {
        return phone;
    }
    public String getAdrs() {
        return adrs;
    }
}