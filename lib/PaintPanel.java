import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


class PaintPanel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape = null;
    private Color currentColor = Color.BLACK;
    private String currentMode = "Pencil"; 
    private boolean isSolid = false; 
    private boolean isDotted = false; 
    private int fontSize = 12; 

    public PaintPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            shapes.clear();
            repaint();
        });

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
                repaint();
            }
        });

        JButton pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(e -> currentMode = "Pencil");

        JButton lineButton = new JButton("Line");
        lineButton.addActionListener(e -> currentMode = "Line");

        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(e -> currentMode = "Rectangle");

        JButton ovalButton = new JButton("Oval");
        ovalButton.addActionListener(e -> currentMode = "Oval");

        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(e -> currentMode = "Eraser");

        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(e -> {
            Color chosenColor = JColorChooser.showDialog(null, "Choose a color", currentColor);
            if (chosenColor != null) {
                currentColor = chosenColor;
            }
        });

        JCheckBox solidCheckBox = new JCheckBox("Solid");
        solidCheckBox.addActionListener(e -> isSolid = solidCheckBox.isSelected());

        JCheckBox dottedCheckBox = new JCheckBox("Dotted");
        dottedCheckBox.addActionListener(e -> isDotted = dottedCheckBox.isSelected());

        
        controlPanel.add(clearButton);
        controlPanel.add(undoButton);
        controlPanel.add(pencilButton);
        controlPanel.add(lineButton);
        controlPanel.add(rectangleButton);
        controlPanel.add(ovalButton);
        controlPanel.add(eraserButton);
        controlPanel.add(colorButton);
        controlPanel.add(solidCheckBox);
        controlPanel.add(dottedCheckBox);

        add(controlPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentShape = new Shape(currentMode, e.getX(), e.getY(), currentColor, isSolid, isDotted, fontSize);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentShape != null) {
            currentShape.update(e.getX(), e.getY());
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentShape != null) {
            currentShape.update(e.getX(), e.getY());
            repaint();
        }
    }

    // Unused mouse events
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
