import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final int width;
    private final int height;

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(527 , 108));
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        button.setBorder(new RoundedBorder(20));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }

    public MainFrame(int width, int height) {
        this.width = width;
        this.height = height;
        setTitle("Connect 4");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);

        JLabel titleLbl = new JLabel("Connect 4");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 80));

        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLbl, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        layout.getConstraints(titleLbl).setY(Spring.constant(72));

        JButton singleplayerBtn = createButton("Singleplayer");
        JButton multiplayerBtn = createButton("Multiplayer");
        JButton analysisBtn = createButton("Analysis");
        JButton quitBtn = createButton("Quit");
        quitBtn.addActionListener(e -> {
            dispose();
        });

        layout.putConstraint(SpringLayout.NORTH, singleplayerBtn, 144, SpringLayout.SOUTH, titleLbl);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, singleplayerBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, multiplayerBtn, 72, SpringLayout.SOUTH, singleplayerBtn);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, multiplayerBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, analysisBtn, 72, SpringLayout.SOUTH, multiplayerBtn);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, analysisBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, quitBtn, 72, SpringLayout.SOUTH, analysisBtn);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, quitBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());

        getContentPane().setLayout(layout);
        getContentPane().add(titleLbl);
        getContentPane().add(singleplayerBtn);
        getContentPane().add(multiplayerBtn);
        getContentPane().add(analysisBtn);
        getContentPane().add(quitBtn);

    }

    private record RoundedBorder(int radius) implements Border {


        public Insets getBorderInsets(Component c) {
                return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
            }


            public boolean isBorderOpaque() {
                return true;
            }


            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            }
        }
}
