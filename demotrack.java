import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class demotrack extends Frame {

    public demotrack() {

        super("VS Code Setup Assistant");

        setLayout(null);

        Label l1 = new Label("Enter your Choice:");
        Choice c1 = new Choice();
        Button btn = new Button("Select Extensions");
        Label l2 = new Label("Result will appear here:");

        c1.add("Java");
        c1.add("C");
        c1.add("C++");
        c1.add("Python");

        l1.setBounds(30, 60, 120, 30);
        c1.setBounds(160, 63, 120, 30);
        btn.setBounds(30, 110, 140, 30);
        l2.setBounds(30, 160, 340, 30);

        add(l1);
        add(c1);
        add(btn);
        add(l2);

        btn.addActionListener(e -> {
            String selectedLanguage = c1.getSelectedItem();
            switch (selectedLanguage) {
                case "Java":
                    l2.setText("Result: Install 'Extension Pack for Java'");
                    break;
                case "Python":
                    l2.setText("Result: Install 'Python by Microsoft'");
                    break;
                default:
                    l2.setText("Result: Install 'C/C++ IntelliSense'");
                    break;
            }
        });

        // Window Close Handler
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(400, 240);
        setVisible(true);
    }

    public static void main(String[] args) {
        new demotrack();
    }
}
