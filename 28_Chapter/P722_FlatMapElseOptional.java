import lombok.AllArgsConstructor;
import java.util.Optional;

public class P722_FlatMapElseOptional {
    public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.of(
            new ContInfo(Optional.ofNullable(null), Optional.ofNullable("ROK"))
        );

        String phone = ci.flatMap(c -> c.getPhone()).orElse("no phone");
        String adrs = ci.flatMap(c -> c.getAdrs()).orElse("no adrs");

        System.out.println(phone);
        System.out.println(adrs);
    }
}

@AllArgsConstructor
class ContInfo {
    private Optional<String> phone;
    private Optional<String> adrs;

    public Optional<String> getPhone() {
        return phone;
    }
    public Optional<String> getAdrs() {
        return adrs;
    }
}
