import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class demomath extends Frame implements ActionListener {

    Label l1 = new Label("NO1:");
    TextField t1 = new TextField(2);

    Label l2 = new Label("NO2:");
    TextField t2 = new TextField(2);

    Button b1 = new Button("Output");

    Label l3 = new Label("Addition:");
    TextField t3 = new TextField(1);

    Label l4 = new Label("Subtraction:");
    TextField t4 = new TextField(1);

    Label l5 = new Label("Division:");
    TextField t5 = new TextField(1);

    Label l6 = new Label("Multiplication:");
    TextField t6 = new TextField(1);

    demomath() {

         super("Basic Calculator");
        setLayout(null);
        

        l1.setBounds(30, 100, 50, 20);
        add(l1);

        t1.setBounds(90, 100, 90, 20);
        add(t1);

        l2.setBounds(30, 150, 50, 20);
        add(l2);

        t2.setBounds(90, 150, 90, 20);
        add(t2);

        b1.setBounds(100, 200, 70, 20);
        b1.addActionListener(this);
        add(b1);

        l3.setBounds(30, 250, 50, 20);
        add(l3);

        t3.setBounds(126, 250, 90, 20);
        add(t3);

        l4.setBounds(30, 300, 70, 20);
        add(l4);

        t4.setBounds(126, 300, 90, 20);
        add(t4);

        l5.setBounds(30, 350, 50, 20);
        add(l5);

        t5.setBounds(126, 350, 90, 20);
        add(t5);

        l6.setBounds(30, 400, 80, 20);
        add(l6);

        t6.setBounds(126, 400, 90, 20);
        add(t6);

        setSize(400, 200);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            int no1 = Integer.parseInt(t1.getText());
            int no2 = Integer.parseInt(t2.getText());

            int add = no1 + no2;
            int sub = no1 - no2;
            int mul = no1 * no2;
            double div = no1 / no2;

            t3.setText(String.valueOf(add));
            t4.setText(String.valueOf(sub));
            t5.setText(String.valueOf(div));
            t6.setText(String.valueOf(mul));
        }
    }

    public static void main(String[] args) {
        new demomath();
    }
}
