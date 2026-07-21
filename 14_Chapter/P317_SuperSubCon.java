class SuperCLS {
    public SuperCLS() {
        System.out.println("I'm super class.");
    }
}

class SubCLS extends SuperCLS{
    public SubCLS() {
        System.out.println("I'm sub class.");
    }
}

public class P317_SuperSubCon {
    public static void main(String[] args) {
        new SubCLS();
    }
}