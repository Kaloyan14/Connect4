package Frames;

import UI.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton createButton(String text) {
        JButton button = new RoundedButton(text, 20, new Color(0xBBBBBB));
        button.setPreferredSize(new Dimension(527 , 108));
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        return button;
    }

    public MainFrame(int width, int height) {
        setTitle("Connect 4");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);

        JLabel titleLbl = new JLabel("Connect 4");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 80));

        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, titleLbl, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, titleLbl, 72, SpringLayout.NORTH, getContentPane());

        JButton singleplayerBtn = createButton("Singleplayer");
        singleplayerBtn.addActionListener(e -> {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            JFrame frame = new GameFrame(width, height, true);
            frame.setVisible(true);
            dispose();
        });
        JButton multiplayerBtn = createButton("Multiplayer");
        multiplayerBtn.addActionListener(e -> {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            JFrame frame = new GameFrame(width, height, false);
            frame.setVisible(true);
            dispose();
        });
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
}
