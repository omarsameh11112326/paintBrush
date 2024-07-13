import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
class Shape {
    private String mode;
    private int x1, y1, x2, y2;
    private Color color;
    private boolean isSolid;
    private boolean isDotted;
    private int fontSize;
    private ArrayList<Point> points;

    public Shape(String mode, int x1, int y1, Color color, boolean isSolid, boolean isDotted, int fontSize) {
        this.mode = mode;
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
        this.isSolid = isSolid;
        this.isDotted = isDotted;
        this.fontSize = fontSize;
        if (mode.equals("Pencil") || mode.equals("Eraser")) {
            points = new ArrayList<>();
            points.add(new Point(x1, y1));
        }
    }

    public void update(int x2, int y2) {
        if (mode.equals("Pencil") || mode.equals("Eraser")) {
            points.add(new Point(x2, y2));
        } else {
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (mode.equals("Eraser")) {
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(color);
        }

        if (isDotted) {
            float[] dash = {2f, 0f, 2f};
            BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
            g2.setStroke(bs);
        } else {
            g2.setStroke(new BasicStroke());
        }

        g2.setFont(new Font("Default", Font.PLAIN, fontSize));

        switch (mode) {
            case "Pencil":
            case "Eraser":
                for (int i = 1; i < points.size(); i++) {
                    Point p1 = points.get(i - 1);
                    Point p2 = points.get(i);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
                break;
            case "Line":
                g2.drawLine(x1, y1, x2, y2);
                break;
            case "Rectangle":
                if (isSolid) {
                    g2.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                } else {
                    g2.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                }
                break;
            case "Oval":
                if (isSolid) {
                    g2.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                } else {
                    g2.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                }
                break;
        }
    }
}
