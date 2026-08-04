import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search_Letter extends JFrame {
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

    // NEW CUSTOMIZATION: Live Result Table Components
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Name", "Email", "Gender", "Discipline" }, 0);
    private final JTable resultTable = new JTable(tableModel);
    private final JScrollPane tableScrollPane = new JScrollPane(resultTable);

    // XAMPP Connection Defaults
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Search_Letter() {
        super("Student Live Search Form");

        // Match user's local operating system look style
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setLayout(null); // Retaining your original absolute positioning design layout

        JLabel titleLabel = new JLabel("Live Search Student Details:");
        titleLabel.setBounds(10, 15, 200, 20);
        add(titleLabel);

        JLabel l1 = new JLabel("Type Name:");
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

        // NEW CUSTOMIZATION: Setup result table layout at the bottom of the window
        tableScrollPane.setBounds(30, 245, 330, 130);
        add(tableScrollPane);

        // NEW CUSTOMIZATION: Attached a DocumentListener to t1 for automatic live searching
        t1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchDataLive();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchDataLive();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchDataLive();
            }
        });

        // Window Frame Adjustments (Increased height to 430 to perfectly show the table)
        setSize(400, 430); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // NEW CUSTOMIZATION: Method that executes instantly as you type
    private void searchDataLive() {
        String searchName = t1.getText().trim();

        // Clear table rows instantly if the input field becomes empty
        if (searchName.isEmpty()) {
            tableModel.setRowCount(0);
            return;
        }

        // Select records where name starts with whatever letters you type
        String searchQuery = "SELECT name, email, gender, discipline FROM registrations WHERE name LIKE ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

            // Appending '%' treats your typed text as a starting prefix (e.g., 'T%')
            pstmt.setString(1, searchName + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                // Clear old visual rows right before adding new search match entries
                tableModel.setRowCount(0);

                while (rs.next()) {
                    String foundName = rs.getString("name");
                    String foundEmail = rs.getString("email");
                    String foundGender = rs.getString("gender");
                    String foundDiscipline = rs.getString("discipline");

                    // Add matching rows straight to the UI table layout grid
                    tableModel.addRow(new Object[] { foundName, foundEmail, foundGender, foundDiscipline });
                }
            }

        } catch (SQLException ex) {
            System.err.println("Live Database Query Failed: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Search_Letter::new);
    }
}
