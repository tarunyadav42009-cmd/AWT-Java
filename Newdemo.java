import java.awt.*;
import java.awt.event.*;

public class Newdemo extends Frame implements MouseListener {
    // State variables tracking the circle's position
    private int mouseX = 200;
    private int mouseY = 200;

    public Newdemo() {
        // Initialize the desktop frame window
        setTitle("AWT Repaint Demo");
        setSize(400, 400);
        setBackground(Color.WHITE);

        // Attach listeners for user interactions
        addMouseListener(this);

        // Window closing adapter to handle clicking the 'X' button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Automatically runs whenever the AWT engine processes repaint()
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        // Draw an oval at the updated state coordinates
        g.fillOval(mouseX - 20, mouseY - 20, 40, 40);

        g.setColor(Color.BLACK);
        g.drawString("Click anywhere to trigger repaint()", 20, 50);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //  Capture the new coordinate state
        mouseX = e.getX();
        mouseY = e.getY();

        //  Request a screen refresh to reflect new coordinates
        repaint();
    }

    public static void main(String[] args) {
        Newdemo app = new Newdemo();
        app.setLocationRelativeTo(null); 
        app.setVisible(true); 
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
