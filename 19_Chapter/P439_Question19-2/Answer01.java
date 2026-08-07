/*
 * [문제 19-2] 깊은 복사를 위한 clone의 오버라이딩
 *
 * 다음 클래스 정의에서 PersonalInfo의 clone 메소드 호출 시 깊은 복사가 이뤄지도록 clone
 * 메소드를 오버라이딩 하자.
 *
 *     class Business implements Cloneable {
 *         private String company;
 *         private String work;
 *         public Business(String company, String work) {
 *             this.company = company;
 *             this.work = work;
 *         }
 *         public void showBusinessInfo() {
 *             System.out.println("회사: " + company);
 *             System.out.println("업무: " + work);
 *         }
 *     }
 *
 *     class PersonalInfo implements Cloneable {
 *         private String name;
 *         private int age;
 *         private Business bz;
 *         public PersonalInfo(String name, int age, String company, String work) {
 *             this.name = name;
 *             this.age = age;
 *             bz = new Business(company, work);
 *         }
 *         public void showPersonalInfo() {
 *             System.out.println("이름: " + name);
 *             System.out.println("나이: " + age);
 *             bz.showBusinessInfo();
 *         }
 *     }
 */

class Business implements Cloneable {
    private String company;
    private String work;

    public Business(String company, String work) {
        this.company = company;
        this.work = work;
    }

    public void showBusinessInfo() {
        System.out.println("회사: " + company);
        System.out.println("업무: " + work);
    }

    @Override
    public Business clone() throws CloneNotSupportedException {
        return (Business)super.clone();
    }
}

class PersonalInfo implements Cloneable {
    private String name;
    private int age;
    private Business bz;

    public PersonalInfo(String name, int age, String company, String work) {
        this.name = name;
        this.age = age;
        bz = new Business(company, work);
    }

    public void showPersonalInfo() {
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        bz.showBusinessInfo();
    }

    @Override
    public PersonalInfo clone() throws CloneNotSupportedException {
        PersonalInfo cpy = (PersonalInfo)super.clone();
        
        // cpy.age = age;
        cpy.bz = bz.clone();

        return cpy;
    }
}

public class Answer01 {
    public static void main(String[] args) {
        PersonalInfo p1 = new PersonalInfo("KSJ", 27, "Kyobo", "DEV");
        PersonalInfo p2;

        try {
            p2 = p1.clone();
            p1.showPersonalInfo();
            p2.showPersonalInfo();
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
