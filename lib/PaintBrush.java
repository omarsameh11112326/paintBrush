import javax.swing.*;


public class PaintBrush extends JFrame {
    public PaintBrush() {
        setTitle("Paint Brush");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new PaintPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        new PaintBrush();
    }
}
