// How to compile:
// javac -Xlint 25_Chapter/P638_FallThroughWarnings.java
// "-Xlint" options means "enable recommended warnings".

public class P638_FallThroughWarnings {
    public static void main(String[] args) {
        int n = 3;

        switch (n) {
            case 1:
                System.out.println(n);
            case 2:
                System.out.println(n);
            case 3:
                System.out.println(n);
        }
    }
}

