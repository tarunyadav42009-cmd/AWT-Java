import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // Live Result Table Components
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[] { "Name", "Email", "Gender", "Discipline" }, 0);
    private final JTable resultTable = new JTable(tableModel);
    private final JScrollPane tableScrollPane = new JScrollPane(resultTable);

    // XAMPP Connection Defaults
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Search_Letter() {
        super("Student Multi-Filter Live Search");

        // Matching user's local operating system look style
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setLayout(null); // Retaining your original absolute positioning design layout

        JLabel titleLabel = new JLabel("Live Filter Student Details:");
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

        tableScrollPane.setBounds(30, 205, 330, 165);
        add(tableScrollPane);

        // --- LIVE SEARCH LISTENERS FOR ALL SECTIONS ---

        // 1. DocumentListener for Name Field (t1)
        t1.getDocument().addDocumentListener(new CustomDocumentListener());

        // 2. DocumentListener for Email Field (t2)
        t2.getDocument().addDocumentListener(new CustomDocumentListener());

        // 3. ActionListeners for Radio Buttons (b1 & b2)
        ActionListener radioListener = e -> searchDataLive();
        b1.addActionListener(radioListener);
        b2.addActionListener(radioListener);

        // 4. ActionListeners for ComboBox Dropdowns (o1, o2, & o3)
        ActionListener comboListener = e -> searchDataLive();
        o1.addActionListener(comboListener);
        o2.addActionListener(comboListener);
        o3.addActionListener(comboListener);

        // Window Frame Adjustments
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Optional: Trigger initial search to display all records on startup
        searchDataLive();
    }

    // Reusable inner class to route text updates back to the search engine
    private class CustomDocumentListener implements DocumentListener {
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
    }

    // Searching method that dynamically chains filters across all form elements
    private void searchDataLive() {
        String searchName = t1.getText().trim();
        String searchEmail = t2.getText().trim();
        String searchGender = b1.isSelected() ? "Male" : (b2.isSelected() ? "Female" : "");
        String searchDiscipline = (String) o1.getSelectedItem();
        String searchYear = (String) o2.getSelectedItem();
        String searchSemester = (String) o3.getSelectedItem();

        // Building base query to dynamically narrow down fields via SQL parameters
        String searchQuery = "SELECT name, email, gender, discipline FROM registrations " +
                "WHERE name LIKE ? " +
                "AND email LIKE ? " +
                "AND gender LIKE ? " +
                "AND discipline LIKE ? " +
                "AND year LIKE ? " +
                "AND semester LIKE ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

            // Bind values with dynamic wildcards to instantly match partial inputs
            pstmt.setString(1, searchName + "%");
            pstmt.setString(2, searchEmail + "%");
            pstmt.setString(3, searchGender.isEmpty() ? "%" : searchGender);
            pstmt.setString(4, searchDiscipline + "%");
            pstmt.setString(5, searchYear + "%");
            pstmt.setString(6, searchSemester + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                tableModel.setRowCount(0); // Clear old results

                while (rs.next()) {
                    String foundName = rs.getString("name");
                    String foundEmail = rs.getString("email");
                    String foundGender = rs.getString("gender");
                    String foundDiscipline = rs.getString("discipline");

                    tableModel.addRow(new Object[] { foundName, foundEmail, foundGender, foundDiscipline });
                }
            }

        } catch (SQLException ex) {
            System.err.println("Live Database Filter Failed: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Search_Letter::new);
    }
}
