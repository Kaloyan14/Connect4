package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private int radius;
    private Color background;
    private double factor;
    private JLabel lbl;
    public RoundedButton(String text, int radius, Color background) {
        this.radius = radius;
        this.background = background;
        this.factor = 0.9;
        this.lbl = new JLabel(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(radius, new Color(0x777777)));
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lbl, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lbl, 0, SpringLayout.VERTICAL_CENTER, this);
        add(lbl);
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

    public RoundedButton(JLabel lbl, int radius, Color background) {
        this.radius = radius;
        this.background = background;
        this.factor = 0.9;
        this.lbl = lbl;
        setOpaque(false);
        setBorder(new RoundedBorder(radius, new Color(0x777777)));
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lbl, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lbl, 0, SpringLayout.VERTICAL_CENTER, this);
        add(lbl);
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

        g2.dispose();
    }

    @Override
    public void setFont(Font font) {
        if(lbl != null) {
            this.lbl.setFont(font);
            repaint();
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

    public void setText(JLabel lbl) {
        this.lbl = lbl;
    }
}
