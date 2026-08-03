import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Search extends JFrame implements ActionListener {
    // Swing Components
    private final JTextField t1 = new JTextField(15);
    private final JButton b3 = new JButton("Search ID");

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

        setTitle("Database Search");
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

        // Search Button Row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(b3, gbc);

        // Table Output Row (Displays query results inside the window)
        tableScrollPane.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(tableScrollPane, gbc);

        // Event Listeners
        b3.addActionListener(this);

        // Frame Setup
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b3) {
            searchData();
        }
    }

    private void searchData() {
        String searchId = t1.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a User ID to search!", "Warning", JOptionPane.WARNING_MESSAGE);
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
                        JOptionPane.showMessageDialog(this, "No records found for ID: " + searchId, "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            handleException(ex);
        }
    }

    private void handleException(Exception ex) {
        if (ex instanceof ClassNotFoundException) {
            JOptionPane.showMessageDialog(this, "Driver File Missing in Build Path!", "Classpath Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        ex.printStackTrace();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Search::new);
    }
}

