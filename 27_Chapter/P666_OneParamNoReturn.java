public class P666_OneParamNoReturn {
    public static void main(String[] args) {
        Printable p;
        p = (String s) -> { System.out.println(s); }; // 줄임 없는 표현
        p.print("Lambda exp one.");

        p = (String s) -> System.out.println(s); // 중괄호 생략
        p.print("Lambda exp two.");

        p = (s) -> System.out.println(s); // 매개변수 형 생략
        p.print("Lambda exp three.");

        p = s -> System.out.println(s); // 매개변수 소괄호 생략
        p.print("Lambda exp four.");
    }
}

interface Printable {
    void print(String s); // one parameter, no return
}