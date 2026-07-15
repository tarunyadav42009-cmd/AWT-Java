import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Marksheet_AWT extends Frame implements ActionListener {

    Label N = new Label("Enter the Details and Marks Obtained in respective subject: ");
    Label l1 = new Label("Name:");
    TextField t1 = new TextField(1);

    Label l2 = new Label("Hindi:");
    TextField t2 = new TextField(1);

    Label l3 = new Label("English:");
    TextField t3 = new TextField(1);

    Label l4 = new Label("Marathi:");
    TextField t4 = new TextField(1);

    Label l5 = new Label("Science:");
    TextField t5 = new TextField(1);

    Label l6 = new Label("Social Science:");
    TextField t6 = new TextField(1);

    Label l7 = new Label("Mathematics:");
    TextField t7 = new TextField(1);

    Button b1 = new Button("Click Here ");

    Label l8 = new Label("Total:");
    TextField t8 = new TextField(1);

    Label l9 = new Label("Average:");
    TextField t9 = new TextField(1);

    Label l10 = new Label("Remarks:");
    TextField t10 = new TextField(1);

    Label l11 = new Label("Grade:");
    TextField t11 = new TextField(1);

    Marksheet_AWT() {

        super("Student Marksheet");
        setLayout(null);

        N.setBounds(15, 30, 360, 20);
        add(N);

        l1.setBounds(30, 70, 60, 20);
        add(l1);

        t1.setBounds(120, 70, 150, 20);
        add(t1);

        l2.setBounds(30, 100, 60, 20);
        add(l2);

        t2.setBounds(128, 100, 135, 20);
        add(t2);

        l3.setBounds(30, 130, 60, 20);
        add(l3);

        t3.setBounds(135, 130, 115, 20);
        add(t3);

        l4.setBounds(30, 160, 60, 20);
        add(l4);

        t4.setBounds(144, 160, 96, 20);
        add(t4);

        l5.setBounds(30, 190, 60, 20);
        add(l5);

        t5.setBounds(153, 190, 80, 20);
        add(t5);

        l6.setBounds(30, 220, 90, 20);
        add(l6);

        t6.setBounds(162, 220, 60, 20);
        add(t6);

        l7.setBounds(30, 250, 90, 20);
        add(l7);

        t7.setBounds(162, 250, 60, 20);
        add(t7);

        b1.setBounds(90, 280, 100, 20);
        b1.addActionListener(this);
        add(b1);

        l8.setBounds(30, 320, 55, 20);
        add(l8);

        t8.setBounds(90, 320, 60, 20);
        add(t8);

        l9.setBounds(200, 320, 60, 20);
        add(l9);

        t9.setBounds(260, 320, 60, 20);
        add(t9);

        l10.setBounds(30, 365, 60, 20);
        add(l10);

        t10.setBounds(90, 365, 100, 20);
        add(t10);

        l11.setBounds(200, 365, 60, 20);
        add(l11);

        t11.setBounds(260, 365, 100, 20);
        add(t11);

        setSize(400, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            int hin = Integer.parseInt(t2.getText());
            int eng = Integer.parseInt(t3.getText());
            int mar = Integer.parseInt(t4.getText());
            int sci = Integer.parseInt(t5.getText());
            int social_Sci = Integer.parseInt(t6.getText());
            int Maths = Integer.parseInt(t7.getText());

            int total = hin + eng + mar + sci + social_Sci + Maths;
            double avg = total / 6;

            if (avg < 35) {
                t11.setText(String.valueOf("Fail"));
                t10.setText(String.valueOf("Bad"));
            } else if (avg >= 35 && avg < 45) {
                t11.setText(String.valueOf("Pass"));
                t10.setText(String.valueOf("Good"));
            } else if (avg >= 45 && avg < 60) {
                t11.setText(String.valueOf("Second Class"));
                t10.setText(String.valueOf("Average"));
            } else if (avg >= 60 && avg < 75) {
                t11.setText(String.valueOf("First Class"));
                t10.setText(String.valueOf("Very Good!"));
            } else if (avg >= 75) {
                t11.setText(String.valueOf("First Class with Distinction"));
                t10.setText(String.valueOf("Excellent!"));
            }
            t8.setText(String.valueOf(total));
            t9.setText(String.valueOf(avg));
        }
    }

    public static void main(String[] args) {
        new Marksheet_AWT();
    }

}
