public class P635_AtDeprecated {
    public static void main(String[] args){
        Viewer view = new Viewer();
        view.showIt("Hello Annotations");
        view.brShowIt("Hello Annotations");
    }
}

interface Viewable {
    @Deprecated
    public void showIt(String str); // Depracated된 메소드

    public void brShowIt(String str);
}

class Viewer implements Viewable {
    @Override
    public void showIt(String str) {
        System.out.println(str);
    }

    @Override
    public void brShowIt(String str) {
        System.out.println(str);
    }
}