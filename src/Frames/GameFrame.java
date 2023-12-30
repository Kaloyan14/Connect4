package Frames;

import UI.RoundedBorder;
import UI.RoundedButton;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static Frames.Utils.printBoard;

public class GameFrame extends JFrame {
    private final boolean singleplayer;
    private static GameManager gameManager;
    public GameFrame(int width, int height, boolean singleplayer) {
        this.singleplayer = singleplayer;
        gameManager = new GameManager();
        setTitle("Connect 4");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);


        ImageIcon boardIcon = new ImageIcon("resources/board.png");
        JLabel boardLbl = new JLabel(boardIcon);
        layout.putConstraint(SpringLayout.NORTH, boardLbl, 150, SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.EAST, boardLbl, -264, SpringLayout.EAST, getContentPane());

        JPanel evalPnl = new JPanel();
        SpringLayout layoutPnl = new SpringLayout();
        evalPnl.setLayout(layoutPnl);
        evalPnl.setPreferredSize(new Dimension(414, boardIcon.getIconHeight()));
        evalPnl.setBorder(new RoundedBorder(20, new Color(0x000)));
        layout.putConstraint(SpringLayout.NORTH, evalPnl, 150, SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.WEST, evalPnl, 38, SpringLayout.WEST, getContentPane());

        JLabel depthLbl = new JLabel("Depth: ");
        depthLbl.setFont(new Font("Arial", Font.PLAIN, 32));
        JLabel nodeLbl = new JLabel("Nodes: ");
        nodeLbl.setFont(new Font("Arial", Font.PLAIN, 32));
        JLabel evaluationLbl = new JLabel("Evaluation: ");
        evaluationLbl.setFont(new Font("Arial", Font.PLAIN, 32));

        layoutPnl.putConstraint(SpringLayout.NORTH, depthLbl, 36, SpringLayout.SOUTH, getContentPane());
        layoutPnl.putConstraint(SpringLayout.NORTH, nodeLbl, 72, SpringLayout.SOUTH, depthLbl);
        layoutPnl.putConstraint(SpringLayout.NORTH, evaluationLbl, 72, SpringLayout.SOUTH, nodeLbl);

        JLabel titleLbl = new JLabel("Connect 4");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 80));

        layout.putConstraint(SpringLayout.EAST, titleLbl, -570, SpringLayout.EAST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, titleLbl, 26, SpringLayout.NORTH, getContentPane());

        ImageIcon optionsIcon = new ImageIcon("resources/options.png");
        JLabel optionsLbl = new JLabel(optionsIcon);
        JButton optionsBtn = new RoundedButton(optionsLbl, 10, new Color(0xFFFFFF));
        optionsBtn.setPreferredSize(new Dimension(optionsIcon.getIconWidth() + 5, optionsIcon.getIconHeight() + 5));

        layout.putConstraint(SpringLayout.NORTH, optionsBtn, 36, SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.EAST, optionsBtn, -64, SpringLayout.EAST, getContentPane());

        JLabel turnLbl = new JLabel("Green's turn");
        turnLbl.setFont(new Font("Arial", Font.BOLD, 36));
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, turnLbl, 0, SpringLayout.HORIZONTAL_CENTER, evalPnl);
        layout.putConstraint(SpringLayout.SOUTH, turnLbl, -38, SpringLayout.NORTH, evalPnl);

        evalPnl.add(depthLbl);
        evalPnl.add(nodeLbl);
        evalPnl.add(evaluationLbl);

        add(boardLbl);
        add(evalPnl);
        add(titleLbl);
        add(optionsBtn);
        add(turnLbl);

        JButton[] triggers = new JButton[42];
        for(int i = 0; i < 42; i++) {
            triggers[i] = new JButton();
            triggers[i].setPreferredSize(new Dimension(boardIcon.getIconWidth() / 7, boardIcon.getIconHeight() / 6));
            triggers[i].setBorderPainted(false);
            triggers[i].setOpaque(false);
            triggers[i].setFocusPainted(false);
            triggers[i].setContentAreaFilled(false);
            if(i % 6 == 0) {
                layout.putConstraint(SpringLayout.NORTH, triggers[i], 0, SpringLayout.NORTH, boardLbl);
            } else {
                layout.putConstraint(SpringLayout.NORTH, triggers[i], 0, SpringLayout.SOUTH, triggers[i - 1]);
            }

            if(i / 6 == 0) {
                layout.putConstraint(SpringLayout.WEST, triggers[i], 0, SpringLayout.WEST, boardLbl);
            } else {
                layout.putConstraint(SpringLayout.WEST, triggers[i], 0, SpringLayout.EAST, triggers[i - 6]);
            }
            System.out.println(i);
            int id = i;
            triggers[i].addActionListener(e -> {
                if(gameManager != null) {
                    gameManager.makeMove(id / 6);
                    printBoard(gameManager.board);
                }

            });
            add(triggers[i]);
        }


    }


}
