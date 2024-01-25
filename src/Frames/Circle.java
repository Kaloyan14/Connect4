package Frames;

import jdk.jshell.execution.Util;

import javax.print.attribute.standard.Sides;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static Frames.Utils.Constants.COLOR;
import static Frames.Utils.Constants.SIDE;

public class Circle extends JPanel {
    private Color color;
    boolean isPlaced = true;
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
        isPlaced = false;
        int pos = getY();
        int indent = pos / 20;
        for(int y = 0; y < pos; y = Math.min(y + indent, pos)) {
            System.out.println(y);
            setBounds(getX(), y, getWidth(), getHeight());
           // update(getGraphics());
            repaint();
            Thread.sleep(10);
        }
        isPlaced = true;
        setBounds(getX(), pos, getWidth(), getHeight());
        repaint();
    }
}
