package UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private int radius;
    private Color background;
    private double factor;
    private String text;
    public RoundedButton(String text, int radius, Color background) {
        super(text);
        this.radius = radius;
        this.background = background;
        this.factor = 0.9;
        this.text = text;
        setOpaque(false);
        setBorder(new RoundedBorder(radius, new Color(0x777777)));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int r = background.getRed();
                int g = background.getGreen();
                int b = background.getBlue();

                r = (int) (r * factor);
                g = (int) (g * factor);
                b = (int) (b * factor);

                setBackground(new Color(r, g, b));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int r = background.getRed();
                int g = background.getGreen();
                int b = background.getBlue();

                setBackground(new Color(r, g, b));
                repaint();
            }

        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(background);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.drawString(text, x, y);

        g2.dispose();
    }

    private record RoundedBorder(int radius, Color color) implements Border {


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
}
