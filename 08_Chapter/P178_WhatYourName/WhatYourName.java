class AAA {
    public void showName() {
        System.out.println("My name is AAA");
    }
}

class BBB {
    public void showName() {
        System.out.println("My name is BBB");
    }
}

public class WhatYourName {
    static void main(String[] args) {
        AAA aaa = new AAA();
        BBB bbb = new BBB();

        aaa.showName();
        bbb.showName();
    }
}