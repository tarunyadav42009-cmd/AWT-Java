import java.awt.*;

public class demoform extends Frame {
    FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);

    Label l2= new Label("Name:");
    TextField t1 = new TextField(10);

    Label l3=new Label("Enter Address");
    TextArea a1 = new TextArea(4,5);

    Label l1 = new Label("Select only one sport:");
    Checkbox c1 = new Checkbox("Chess",false);
    Checkbox c2 = new Checkbox("Volleyball",false);

    CheckboxGroup hr=new CheckboxGroup();
    Label l=new Label("Select Gender:");
    Checkbox r1=new Checkbox("Male",hr,false);
    Checkbox r2=new Checkbox("Female",hr,false);

   Button b1= new Button("Submit");


    demoform() {

        setLayout(f1);
        add(l2);
        add(t1);
        add(l3);
        add(a1);
        add(l1);
        add(c1);
        add(c2);
        add(l);
        add(r1);
        add(r2);
        add(b1);
        
       
        setSize(500,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new demoform();
    }
}
