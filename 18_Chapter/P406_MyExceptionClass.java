import java.util.Scanner;

class ReadAgeException extends Exception {
    public ReadAgeException() {
        super("Invalid age input");
    }
}

public class P406_MyExceptionClass {
    public static void main(String[] args) {
        System.out.println("나이 입력: ");

        try {
            int age = readAge();
            System.out.printf("Age: %d\n", age);
        }
        catch(ReadAgeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int readAge() throws ReadAgeException {
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();

        if(age < 0)
            throw new ReadAgeException(); // Exception occurs
            // throw (new ReadAgeException());

        return age;
    }

}