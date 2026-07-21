class Man {
    String name;
    public void tellYourName() {
        System.out.println("My name is" + name);
    }
}

class BusinessMan extends Man {
    String company;
    String position;

    public BusinessMan(String name, String company, String position) {
        // 상위 클래스 Man의 초기화
        this.name = name;

        // class: BusinessMan의 멤버 초기화
        this.company = company;
        this.position = position;
    }

    public void tellYourInfo() {
        System.out.println("My company is " + this.company);
        System.out.println("My position is " + this.position);
        tellYourName();
    }
}

public class P316_MyBusinessMan {
    public static void main(String[] args) {
        BusinessMan man = new BusinessMan("Yoon", "LED", "Staff");
        man.tellYourInfo();
    }
}