public class P624_InClassInst {
    public static void main(String[] args) {
        System.out.println(Person.MAN);
        System.out.println(Person.WOMAN);
    }
}

class Person {
    public static final Person MAN = new Person();
    public static final Person WOMAN = new Person();

    @Override
    public String toString() {
        return "I am a person who loves dogs.";
    }
}