
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Search extends JFrame implements ActionListener {
    // Swing Components
    private final JTextField t1 = new JTextField(15);
    private final JPasswordField t2 = new JPasswordField(15);
    private final JButton b1 = new JButton("Send Data");
    private final JButton b2 = new JButton("Clear Data");
    private final JButton b3 = new JButton("Search ID"); // New search button

    // Table components to show the output
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] { "User ID", "Password" }, 0);
    private final JTable resultTable = new JTable(tableModel);
    private final JScrollPane tableScrollPane = new JScrollPane(resultTable);

    // MySQL Configuration
    private static final String URL = "jdbc:mysql://localhost:3306/inma";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Search() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Database Login & Search");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User ID Row
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        add(t1, gbc);

        // Password Row
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(t2, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(b1);
        buttonPanel.add(b3); // Added search button
        buttonPanel.add(b2);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Table Output Row (Displays query results inside the window)
        tableScrollPane.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(tableScrollPane, gbc);

        // Event Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        // Frame Setup
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            insertData();
        } else if (e.getSource() == b3) {
            searchData();
        } else if (e.getSource() == b2) {
            clearFields();
        }
    }

    private void insertData() {
        String s = t1.getText().trim();
        String s2 = new String(t2.getPassword()).trim();

        if (s.isEmpty() || s2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sqlQuery = "INSERT INTO t1 (user_id, password) VALUES(?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement st = conn.prepareStatement(sqlQuery)) {
                st.setString(1, s);
                st.setString(2, s2);
                st.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data inserted successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            handleException(ex);
        }
    }

    private void searchData() {
        String searchId = t1.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a User ID to search!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Clear existing rows in the UI table view before searching
        tableModel.setRowCount(0);

        String sqlQuery = "SELECT user_id, password FROM t1 WHERE user_id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement st = conn.prepareStatement(sqlQuery)) {

                st.setString(1, searchId);
                try (ResultSet rs = st.executeQuery()) {
                    boolean recordsFound = false;
                    while (rs.next()) {
                        recordsFound = true;
                        String id = rs.getString("user_id");
                        String pass = rs.getString("password");
                        // Push found SQL row directly to the visual JTable layout
                        tableModel.addRow(new Object[] { id, pass });
                    }

                    if (!recordsFound) {
                        JOptionPane.showMessageDialog(this, "No records found for ID: " + searchId, "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            handleException(ex);
        }
    }

    private void clearFields() {
        t1.setText("");
        t2.setText("");
        tableModel.setRowCount(0);
    }

    private void handleException(Exception ex) {
        if (ex instanceof ClassNotFoundException) {
            JOptionPane.showMessageDialog(this, "Driver File Missing in Build Path!", "Classpath Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        ex.printStackTrace();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Search::new);
    }
}
