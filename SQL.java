/*import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class SQL extends Frame implements ActionListener {
    FlowLayout f = new FlowLayout(FlowLayout.CENTER);
    Label l1 = new Label("USER ID");
    TextField t1 = new TextField(15);
    Label l2 = new Label("PASSWORD");
    TextField t2 = new TextField(15);
    Button b1 = new Button("SEND DATA");
    Button b2 = new Button("CLEAR DATA");

    public SQL() {
        setLayout(f);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setSize(300, 300);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String s = t1.getText().trim();
                String s2 = t2.getText().trim();

                // 1. Register the modern MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 2. Setup database credentials for XAMPP
                String user = "root";
                String password = ""; // Empty string for default XAMPP
                String url = "jdbc:mysql://localhost:3306/inma"; // Assuming 'inma' database exists

                // 3. Establish connection
                Connection conn = DriverManager.getConnection(url, user, password);

                // 4. Use the correct 'conn' variable name to prepare statement
                PreparedStatement st = conn.prepareStatement("INSERT INTO tab VALUES(?, ?)");
                st.setString(1, s);
                st.setString(2, s2);

                // 5. Execute statement and clean up
                st.executeUpdate();
                System.out.println("Data inserted successfully into MySQL!");

                st.close();
                conn.close(); // Closed using correct variable name
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            t1.setText("");
            t2.setText("");
        }
    }

    public static void main(String[] args) {
        new SQL();
    }
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SQL extends JFrame implements ActionListener {
    // Modern Swing Components
    private final JTextField t1 = new JTextField(15);
    private final JPasswordField t2 = new JPasswordField(15); // Hides passwords visually
    private final JButton b1 = new JButton("Send Data");
    private final JButton b2 = new JButton("Clear Data");

    // XAMPP MySQL Configuration
    private static final String URL = "jdbc:mysql://localhost:3306/inma";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public SQL() {
        // Set modern look and feel matching the OS system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setTitle("Database Login");
        setLayout(new GridBagLayout()); // Better spacing than FlowLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User ID Row
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        add(t1, gbc);

        // Password Row
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(t2, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across both columns
        add(buttonPanel, gbc);

        // Event Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Frame Setup
        setSize(350, 200);
        setLocationRelativeTo(null); // Centers window on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Replaces manual WindowListener
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String s = t1.getText().trim();
            String s2 = new String(t2.getPassword()).trim(); // Modern password retrieval

            if (s.isEmpty() || s2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Modern try-with-resources handles open/close operations automatically
            // Class.forName() is removed as JDK 26 uses automatic SPI driver discovery
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement st = conn.prepareStatement("INSERT INTO tab VALUES(?, ?)")) {
                
                st.setString(1, s);
                st.setString(2, s2);
                st.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear fields after success
                t1.setText("");
                t2.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            t1.setText("");
            t2.setText("");
        }
    }

    public static void main(String[] args) {
        // Runs GUI on the correct Event Dispatch Thread
        SwingUtilities.invokeLater(SQL::new);
    }
}
