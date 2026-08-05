import javax.swing.*;
import java.awt.*;

public class DemoSwing extends JFrame {
    // Modern Swing components
    private JTextField t1 = new JTextField(15);
    private JTextField t2 = new JTextField(15);
    private JButton b1 = new JButton("SEND");
    private JButton b2 = new JButton("CLEAR");
    private JLabel l1 = new JLabel("NAME:");
    private JLabel l2 = new JLabel("ADDRESS:");

    public DemoSwing() {
        // Set up the main window frame
        setTitle("Modern Input Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Central container for input fields using GridBagLayout for alignment
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: Name Field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(l1, gbc);
        gbc.gridx = 1;
        formPanel.add(t1, gbc);

        // Row 2: Address Field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(l2, gbc);
        gbc.gridx = 1;
        formPanel.add(t2, gbc);

        // Bottom container for action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        // Add sub-panels to the main frame window
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Auto-size and center the window on screen
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Run UI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            // Apply native operating system look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            
            new DemoSwing().setVisible(true);
        });
    }
}
