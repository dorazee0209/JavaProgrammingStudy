public class P623_InnerEnum {
    public static void main(String[] args) {
        Customer c1 = new Customer("Brown", "man");
        Customer c2 = new Customer("Susan Hill", "woman");

        System.out.println(c1);
        System.out.println(c2);
    }
}

class Customer {
    enum Gender {
        MALE, FEMALE
    }

    private String name;
    private Gender gen;

    public Customer(String name, String gen) {
        this.name = name;

        if(gen.equals("man"))
            this.gen = Gender.MALE;
        else
            this.gen = Gender.FEMALE;
    }

    @Override
    public String toString() {
        if(this.gen == Gender.MALE)
            return "Thank you, Mr. " + this.name;
        else
            return "Thank you, Mrs. " + this.name;
    }
}