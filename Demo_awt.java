import java.awt.*;

public class Demo_awt extends FlowLayout {

    FlowLayout f = new FlowLayout(FlowLayout.CENTER);

    TextField t1 = new TextField(15);
    TextField t2 = new TextField(15);
    Button b1 = new Button("SEND");
    Button b2 = new Button("CLEAR");
    Label l1 = new Label("NAME");
    Label l2 = new Label("Add");
    Panel p = new Panel();
    Panel p1 = new Panel();
    Panel p2 = new Panel();

    public Demo_awt() {
        p.add(l1);
        p.add(t1);
        p.add(p);
        p1.add(l2);
        p1.add(t2);

        p2.add(b1);
        p2.add(b2);
        p2.add(p2);

    }

    public static void main(String[] args) {
        new Demo_awt();
    }
}
