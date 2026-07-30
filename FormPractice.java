/*import java.awt.*;

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
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormPractice extends JFrame implements ActionListener {
    // Modern UI Component Elements
    private final JTextField t1 = new JTextField();
    private final JTextField t2 = new JTextField();
    private final JRadioButton b1 = new JRadioButton("Male");
    private final JRadioButton b2 = new JRadioButton("Female");
    private final ButtonGroup genderGroup = new ButtonGroup();

    private final JComboBox<String> o1 = new JComboBox<>(new String[] { "AN", "BD", "CO", "CE", "ME" });
    private final JComboBox<String> o2 = new JComboBox<>(new String[] { "1", "2", "3" });
    private final JComboBox<String> o3 = new JComboBox<>(new String[] { "1", "2", "3", "4", "5", "6" });

    private final JCheckBox r1 = new JCheckBox("Accept Terms and Conditions");
    private final JButton z1 = new JButton("Submit");

    // XAMPP Connection Defaults
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public FormPractice() {
        super("Student Registration Form");

        // Match user's local operating system look style
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setLayout(null); // Retaining your original absolute positioning design layout

        JLabel titleLabel = new JLabel("Enter your Details:");
        titleLabel.setBounds(10, 15, 150, 20);
        add(titleLabel);

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(30, 45, 100, 20);
        add(l1);

        t1.setBounds(130, 45, 150, 20);
        add(t1);

        JLabel l2 = new JLabel("Email Address:");
        l2.setBounds(30, 75, 95, 20);
        add(l2);

        t2.setBounds(130, 75, 150, 20);
        add(t2);

        JLabel l3 = new JLabel("Gender:");
        l3.setBounds(30, 105, 100, 20);
        add(l3);

        // Grouping logic locks radio selections uniquely
        genderGroup.add(b1);
        genderGroup.add(b2);
        b1.setBounds(130, 105, 70, 20);
        b2.setBounds(210, 105, 80, 20);
        add(b1);
        add(b2);

        JLabel l4 = new JLabel("Discipline:");
        l4.setBounds(30, 140, 100, 20);
        add(l4);

        o1.setBounds(30, 165, 100, 22);
        add(o1);

        JLabel l5 = new JLabel("Year/Semester:");
        l5.setBounds(180, 140, 150, 20);
        add(l5);

        o2.setBounds(180, 165, 60, 22);
        o3.setBounds(255, 165, 60, 22);
        add(o2);
        add(o3);

        r1.setBounds(30, 210, 250, 20);
        add(r1);

        z1.setBounds(30, 245, 100, 25);
        z1.addActionListener(this);
        add(z1);

        // Window Frame Adjustments
        setSize(400, 340); // Adjusted height to accommodate layout elements beautifully
        setLocationRelativeTo(null); // Spawns window dead center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == z1) {
            // 1. Validate Form Rules
            String name = t1.getText().trim();
            String email = t2.getText().trim();
            String gender = b1.isSelected() ? "Male" : (b2.isSelected() ? "Female" : "");

            if (name.isEmpty() || email.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete Name, Email, and Gender fields.",
                        "Validation Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!r1.isSelected()) {
                JOptionPane.showMessageDialog(this, "You must accept the Terms and Conditions to continue.",
                        "Validation Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Extract choices
            String discipline = (String) o1.getSelectedItem();
            int year = Integer.parseInt((String) o2.getSelectedItem());
            int semester = Integer.parseInt((String) o3.getSelectedItem());
            boolean terms = r1.isSelected();

            // 2. Stream Data to Database via Modern JDBC Architecture
            String insertQuery = "INSERT INTO registrations (name, email, gender, discipline, year, semester, terms_accepted) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, gender);
                pstmt.setString(4, discipline);
                pstmt.setInt(5, year);
                pstmt.setInt(6, semester);
                pstmt.setBoolean(7, terms);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registration record saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Write Failed:\n" + ex.getMessage(), "SQL Exception",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private void clearFormFields() {
        t1.setText("");
        t2.setText("");
        genderGroup.clearSelection();
        o1.setSelectedIndex(0);
        o2.setSelectedIndex(0);
        o3.setSelectedIndex(0);
        r1.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormPractice::new);
    }
}
