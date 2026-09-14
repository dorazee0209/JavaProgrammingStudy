import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Optional;

public class P724_NullPointerCaseStudy3 {
    public static void showResult(Optional<Friend> f) {
        String temp;
        System.out.println(
            f.map(Friend::getName).orElse("No name.")
        );
        System.out.println(
            f.flatMap(Friend::getCmp)
                .map(Company::getName)
                .orElse("No name")
        );
        System.out.println(
            f.flatMap(Friend::getCmp)
                .flatMap(Company::getCInfo)
                .flatMap(ContInfo::getPhone)
                .orElse("No ph.")
        );
        System.out.println(
            f.flatMap(Friend::getCmp)
                .flatMap(Company::getCInfo)
                .flatMap(ContInfo::getAdrs)
                .orElse("No adrs")
        );
    }

    public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.ofNullable(
            new ContInfo(Optional.ofNullable(null), Optional.ofNullable("ROK"))
        );
        Optional<Company> comI = Optional.ofNullable(new Company("Geoje Yaho", ci));
        Optional<Friend> fi = Optional.ofNullable(new Friend("NANAMI", comI));

        showResult(fi);
    }
}

@AllArgsConstructor
@Getter
class Friend { // f
    String name;
    Optional<Company> cmp;
}

@AllArgsConstructor
@Getter
class Company { // comI
    String name;
    Optional<ContInfo> cInfo;
}

@AllArgsConstructor
@Getter
class ContInfo { // ci
    Optional<String> phone;
    Optional<String> adrs;
}