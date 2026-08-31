public class P629_Varargs {
    public static void showAll(String...vargs) {
        System.out.println("LEN: " + vargs.length);

        for(String e : vargs)
            System.out.print(e + "\t");
        System.out.println();
    }

    public static void main(String[] args) {
        showAll("Box");
        showAll("Box", "Toy");
        showAll("Box", "Toy", "Robot");
    }
}

