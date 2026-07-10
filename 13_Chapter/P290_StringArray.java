import java.util.Scanner;

public class P290_StringArray {
    public static void main(String[] args) {
        String[] arr = new String[7];
        Scanner sc = new Scanner("Java\nSystem\nCompiler\nPark\nTree\nDinner\nBrunch cafe");

        for (int i = 0; i < arr.length; i++)
            arr[i] = sc.nextLine();

        for (int i = 0; i < arr.length; i++)
            System.out.printf("[%d]: %s \n", i+1, arr[i]);

        int cnt = 0;
        for (int i = 0; i < arr.length; i++)
            cnt += arr[i].length();

        System.out.printf("Cnt of all characters: %d\n", cnt);
    }
}

