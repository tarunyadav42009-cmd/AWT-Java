import java.awt.*;

public class formpractise extends Frame {
    formpractise() {

        super("Student Registration Form");

        Label T = new Label("Enter your Details:");

        Label l1 = new Label("Name:");
        TextField t1 = new TextField(2);

        Label l2 = new Label("Email Address:");
        TextField t2 = new TextField(2);

        CheckboxGroup c1 = new CheckboxGroup();
        Label l3 = new Label("Gender:");
        Checkbox b1 = new Checkbox("Male", c1, false);
        Checkbox b2 = new Checkbox("Female", c1, false);

        Choice o1 = new Choice();
        Label l4 = new Label("Discipline:");
        o1.add("AN");
        o1.add("BD");
        o1.add("CO");
        o1.add("CE");
        o1.add("ME");

        Choice o2 = new Choice();
        Label l5 = new Label("Year/Semester:");
        o2.add("1");
        o2.add("2");
        o2.add("3");

        Choice o3 = new Choice();
        o3.add("1");
        o3.add("2");
        o3.add("3");
        o3.add("4");
        o3.add("5");
        o3.add("6");

        Checkbox r1 = new Checkbox("Accept Terms and Conditions");

        Button z1 = new Button("Submit");

        setLayout(null);
        T.setBounds(10, 45, 100, 20);
        add(T);

        l1.setBounds(30, 65, 100, 20);
        add(l1);

        t1.setBounds(130, 65, 100, 20);
        add(t1);

        l2.setBounds(30, 90, 95, 20);
        add(l2);

        t2.setBounds(130, 90, 100, 20);
        add(t2);

        l3.setBounds(30, 117, 100, 20);
        add(l3);

        b1.setBounds(30, 144, 50, 20);
        add(b1);

        b2.setBounds(90, 144, 60, 20);
        add(b2);

        l4.setBounds(30, 171, 100, 20);
        o1.setBounds(30, 195, 100, 20);
        add(o1);
        add(l4);

        l5.setBounds(180, 171, 100, 20);
        o2.setBounds(190, 195, 60, 20);
        o3.setBounds(270, 195, 60, 20);
        add(l5);
        add(o2);
        add(o3);

        r1.setBounds(30, 240, 180, 20);
        add(r1);

        z1.setBounds(30, 270, 100, 20);
        add(z1);

        setSize(400, 240);
        setVisible(true);

    }

    public static void main(String[] args) {
        new formpractise();
    }
}
