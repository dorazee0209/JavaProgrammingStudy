import javax.swing.text.View;

public class P634_AtOverride {
    public static void main(String[] args) {
        Viewer view = new Viewer();
        view.showIt("Hello Annotations");
    }
}

interface Viewable {
    public void showIt(String str);
}

class Viewer implements Viewable{
    @Override
    public void showIt(String str) {
        System.out.println(str);
    }
}