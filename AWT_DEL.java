import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AWT_DEL extends JFrame implements ActionListener {
    // Modern Swing Components
    private final JTextField t1 = new JTextField(15);
    private final JButton b1 = new JButton("Delete Data");
    private final JButton b2 = new JButton("Clear Data");

    // Updated MySQL Configuration for database 'inma'
    private static final String URL = "jdbc:mysql://localhost:3306/inma";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public AWT_DEL() {
        // Set modern look and feel matching the OS system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Database Deletion");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User ID Row
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        add(t1, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Event Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Frame Setup
        setSize(350, 160); // Adjusted height since password field is removed
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String s = t1.getText().trim();

            if (s.isEmpty()) {
                JOptionPane.showMessageDialog(this, "User ID field cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Secure parameterized query targeting user_id
            String sqlQuery = "DELETE FROM t1 WHERE user_id = ?";

            try {
                // FORCE RUNTIME TO MANUALLY INITIALIZE THE MYSQL 9.2.0 DRIVER
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                        PreparedStatement st = conn.prepareStatement(sqlQuery)) {

                    // Bind the dynamic User ID parameter safely
                    st.setString(1, s);

                    int rowsDeleted = st.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Data deleted successfully!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No matching User ID found to delete.", "Notice",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    // Clear fields after success
                    t1.setText("");
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this,
                        "Driver File Missing in VS Code Build Path!\nPlease ensure mysql-connector-j-9.2.0.jar is in Referenced Libraries.",
                        "Classpath Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            t1.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AWT_DEL::new);
    }
}
