package Frames;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private Color color;
    boolean isPlaced;
    public Circle(int width, int height, Color color) {
        this.color = color;
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("Here");
        if(color != Color.WHITE) {
            g.setColor(color);
            if(isPlaced) {
                g.fillOval(-3, -3, getWidth() + 6, getHeight() + 6);
            }
            else {
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        }
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public void animate() throws InterruptedException {
        int pos = getY();
        int indent = pos / 20;
        for(int y = 0; y < pos; y = Math.min(y + indent, pos)) {
            System.out.println(y);
            setBounds(getX(), y, getWidth(), getHeight());
            update(getGraphics());
            Thread.sleep(10);
        }
        isPlaced = true;
        setBounds(getX(), pos, getWidth(), getHeight());
        repaint();
    }
}
