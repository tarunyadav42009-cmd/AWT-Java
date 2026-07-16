import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class formredirection extends Frame implements ActionListener {

    Label l1 = new Label("Choose what colour you want to set as Backround: ");
    Button b1 = new Button("Red");
    Button b2 = new Button("Green");
    Button b3 = new Button("Blue");

    formredirection() {

        setLayout(null);

        l1.setBounds(30, 63, 300, 20);
        add(l1);

        b1.setBounds(30, 90, 70, 20);
        b1.addActionListener(this);
        add(b1);

        b2.setBounds(30, 117, 70, 20);
        b2.addActionListener(this);
        add(b2);

        b3.setBounds(30, 145, 70, 20);
        b3.addActionListener(this);
        add(b3);

        setSize(400, 240);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            System.out.println("You Choose Red");
            setBackground(Color.red);
        }
        if (e.getSource() == b2) {
            System.out.println("You Choose Green");
            setBackground(Color.green);
        }
        if (e.getSource() == b3) {
            System.out.println("You Choose Blue");
            setBackground(Color.blue);
        }
    }

    public static void main(String[] args) {
        new formredirection();
    }
}
