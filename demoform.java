import java.awt.*;

public class demoform extends Frame {

    FlowLayout f1 = new FlowLayout();

    Label l2 = new Label("Enter Name:");
    TextField t1 = new TextField(10);

    Label l3 = new Label("Enter Class:");
    TextField a1 = new TextField(10);

    Label l1 = new Label("Select sport:");
    Checkbox c1 = new Checkbox("Chess", false);
    Checkbox c2 = new Checkbox("Volleyball", false);

    CheckboxGroup hr = new CheckboxGroup();
    Label l = new Label("Select Gender:");
    Checkbox r1 = new Checkbox("Male", hr, false);
    Checkbox r2 = new Checkbox("Female", hr, false);

     Label t2 = new Label("Enter Roll No:");
    TextField n1 = new TextField(1);

    Button b1 = new Button("Submit");

    demoform() {

        super("Student Sports Form");

        setLayout(null);
        l2.setBounds(30, 70, 90, 20);
        add(l2);

        t1.setBounds(135, 70, 135, 20);
        add(t1);

        l3.setBounds(30, 100, 90, 20);
        add(l3);

        a1.setBounds(135, 100, 135, 30);
        add(a1);

        t2.setBounds(30, 145, 100, 50);
        add(t2);

        n1.setBounds(135, 160, 135, 20);
        add(n1);

        l1.setBounds(30, 200, 126, 20);
        add(l1);

        c1.setBounds(30, 240, 90, 20);
        add(c1);

        c2.setBounds(135, 240, 90, 20);
        add(c2);

        l.setBounds(30, 280, 90, 20);
        add(l);

        r1.setBounds(30, 310, 90, 20);
        add(r1);

        r2.setBounds(135, 310, 90, 20);
        add(r2);

        b1.setBounds(30, 360, 90, 20);
        add(b1);

        setSize(500, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new demoform();
    }
}
