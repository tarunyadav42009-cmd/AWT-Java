import java.awt.*;

public class demotrack extends Frame {
    public demotrack(){
        
        super("VS Code Setup Assistant");

        
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 10, 15);
        setLayout(layout); 
        
        
        Label l1 = new Label("Enter your Choice:");
        
        Choice c1 = new Choice();
        c1.add("Java");
        c1.add("C");
        c1.add("C++");
        c1.add("Python");
        
        Button btn = new Button("Select Extensions");
        Label l2 = new Label("Result will appear here:                        "); // Added spacing so text doesn't cut off
    
  
        add(l1);
        add(c1);
        add(btn);
        add(l2);

        // Connecting the button logic to update the text dynamically
        btn.addActionListener(e -> {
            String selectedLanguage = c1.getSelectedItem();
            
            if (selectedLanguage.equals("Java")) {
                l2.setText("Result: Install 'Extension Pack for Java'");
            } else if (selectedLanguage.equals("Python")) {
                l2.setText("Result: Install 'Python by Microsoft'");
            } else {
                l2.setText("Result: Install 'C/C++ IntelliSense'");
            }
        });
        
        
        setSize(400, 200); 
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new demotrack();
    }
}
