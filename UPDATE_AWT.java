import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UPDATE_AWT extends JFrame implements ActionListener {
    // Modern Swing Components
    private final JTextField t1 = new JTextField(15);
    private final JPasswordField t2 = new JPasswordField(15);
    private final JButton b1 = new JButton("Update Password");
    private final JButton b2 = new JButton("Clear Data");

    // Updated MySQL Configuration for database 'inma'
    private static final String URL = "jdbc:mysql://localhost:3306/inma";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public UPDATE_AWT() {
        // Set modern look and feel matching the OS system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Database Update");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User ID Row
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("User ID (Find):"), gbc);
        gbc.gridx = 1;
        add(t1, gbc);

        // New Password Row
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("New Password:"), gbc);
        gbc.gridx = 1;
        add(t2, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Event Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Frame Setup
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String s = t1.getText().trim();
            String s2 = new String(t2.getPassword()).trim();

            if (s.isEmpty() || s2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Secure parameterized query to update the password for a specific User ID
            String sqlQuery = "UPDATE t1 SET password = ? WHERE user_id = ?";

            try {
                // FORCE RUNTIME TO MANUALLY INITIALIZE THE MYSQL 9.2.0 DRIVER
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                        PreparedStatement st = conn.prepareStatement(sqlQuery)) {

                    // Bind parameters securely (1st '?' is password, 2nd '?' is user_id)
                    st.setString(1, s2);
                    st.setString(2, s);
                    
                    int rowsUpdated = st.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Data updated successfully!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No matching User ID found to update.", "Notice",
                                JOptionPane.WARNING_MESSAGE);
                    }

                    // Clear fields after success
                    t1.setText("");
                    t2.setText("");
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
            t2.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UPDATE_AWT::new);
    }
}
