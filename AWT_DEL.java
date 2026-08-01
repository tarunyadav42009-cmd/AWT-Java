import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AWT_DEL extends JFrame implements ActionListener {
    // Modern Swing Components
    private final JTextField t1 = new JTextField(15);
    private final JButton b1 = new JButton("Delete Data");

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

        setTitle("Database Login");
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
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Event Listeners
        b1.addActionListener(this);

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

            if (s.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Updated Target table to 't1' with generic target columns (modify column names
            // if different)
            String sqlQuery = "DELETE FROM `t1` WHERE user_id='s';";

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement st = conn.prepareStatement(sqlQuery)) {

                st.setString(1, s);
                st.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data inserted successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields after success
                t1.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AWT_DEL::new);
    }
}
