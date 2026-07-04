public class P150_ReculFactorial {
    static void main(String[] args) {
        System.out.println("3F: " + factorial(3));
        System.out.println("12F: " + factorial(12));
    }

    public static int factorial(int n) {
        if(n == 1) {
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }
}

