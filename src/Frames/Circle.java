package Frames;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private Color color;
    public Circle(int width, int height, Color color) {
        this.color = color;
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(color != Color.WHITE) {
            g.setColor(color);
            g.fillOval(-3, -3, getWidth() + 6, getHeight() + 6);
        }
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
