/*import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Data extends Frame implements ActionListener {
    FlowLayout f = new FlowLayout(FlowLayout.CENTER);
    Label l1 = new Label("USER ID");
    TextField t1 = new TextField(15);
    Label l2 = new Label("PASSWORD");
    TextField t2 = new TextField(15);
    Button b1 = new Button("SEND DATA");
    Button b2 = new Button("CLEAR DATA");

    public Data() {
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
        new Data();
    }
}*/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Data1 extends Frame implements ActionListener {

    FlowLayout f = new FlowLayout();

    Label l1 = new Label("USER ID");
    TextField t1 = new TextField(15);

    Label l2 = new Label("PASSWORD");
    TextField t2 = new TextField(15);

    Button b1 = new Button("SEND DATA");
    Button b2 = new Button("CLEAR DATA");

    public Data1() {

        setLayout(f);

        t2.setEchoChar('*');

        add(l1);
        add(t1);

        add(l2);
        add(t2);

        add(b1);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setTitle("User Registration");
        setSize(350,200);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==b1) {

            String user = t1.getText().trim();
            String pass = t2.getText().trim();

            try {

                // Load MySQL Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Connect to MySQL Database
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/inma",
                        "root",
                        ""
                );

                // Insert Data
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO t1 VALUES(?,?)"
                );

                ps.setString(1, user);
                ps.setString(2, pass);

                int i = ps.executeUpdate();

                if(i>0) {
                    System.out.println("Data Inserted Successfully");
                } else {
                    System.out.println("Insertion Failed");
                }

                ps.close();
                con.close();

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==b2) {
            t1.setText("");
            t2.setText("");
        }
    }

    public static void main(String args[]) {
        new Data1();
    }
}
