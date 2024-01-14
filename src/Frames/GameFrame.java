package Frames;

import UI.RoundedBorder;
import UI.RoundedButton;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static Frames.Evaluate.alphaBetaSoft;
import static Frames.Evaluate.validMoves;
import static Frames.Utils.Constants.*;
import static Frames.Utils.*;

public class GameFrame extends JFrame {
    private static GameManager gameManager;
    public GameFrame(int width, int height, boolean singleplayer) {
        final int DEPTH = 10;
        gameManager = new GameManager();
        setTitle("Connect 4");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        JPanel boardPnl = new JPanel();
        boardPnl.setLayout(null);
        ImageIcon boardIcon = new ImageIcon("resources/board.png");
        JLabel boardLbl = new JLabel(boardIcon);
        layout.putConstraint(SpringLayout.NORTH, boardPnl, 0, SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.EAST, boardPnl, -264, SpringLayout.EAST, getContentPane());
        boardPnl.setPreferredSize(new Dimension(boardIcon.getIconWidth(), height));
        boardLbl.setBounds(0, 150, boardIcon.getIconWidth(), boardIcon.getIconHeight());
        boardPnl.add(boardLbl);
        boardPnl.setComponentZOrder(boardLbl, 0);

        JPanel evalPnl = new JPanel();
        SpringLayout layoutPnl = new SpringLayout();
        evalPnl.setLayout(layoutPnl);
        evalPnl.setPreferredSize(new Dimension(414, boardIcon.getIconHeight()));
        evalPnl.setBorder(new RoundedBorder(20, new Color(0x000)));
        layout.putConstraint(SpringLayout.NORTH, evalPnl, 150, SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.WEST, evalPnl, 38, SpringLayout.WEST, getContentPane());

        JLabel depthLbl = new JLabel("Depth: " + DEPTH);
        depthLbl.setFont(new Font("Arial", Font.PLAIN, 32));
        JLabel nodeLbl = new JLabel("Nodes: 0");
        nodeLbl.setFont(new Font("Arial", Font.PLAIN, 32));
        JLabel evaluationLbl = new JLabel("Evaluation: 0");
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

        add(boardPnl);
        add(evalPnl);
        add(titleLbl);
        add(optionsBtn);
        add(turnLbl);



        Circle[] panels = new Circle[42];
        for(int i = 0; i < 42; i++) {
            panels[i] = new Circle(boardIcon.getIconWidth() / 7, boardIcon.getIconHeight() / 6, Color.WHITE);
            panels[i].setBounds((i / 6) * boardIcon.getIconWidth() / 7, 150 + (i % 6) * boardIcon.getIconHeight() / 6, boardIcon.getIconWidth() / 7, boardIcon.getIconHeight() / 6);
            boardPnl.add(panels[i]);
        }

        boardLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int id = (y * 6 / boardIcon.getIconHeight()) + 6 * (7 * x / boardIcon.getIconWidth());
                System.out.println(id);
                int position = -1;
                if(gameManager != null) {
                    position = gameManager.makeMove(id / 6);
                }
                if(position >= 0) {
                    panels[position].setColor(Utils.Constants.COLOR[(int)(1 - gameManager.board[SIDE])]);
                    //panels[position].animate();
                    panels[position].repaint();
                    if(singleplayer) {
                        if (checkWin(gameManager.board, (int) (1 - gameManager.board[SIDE]))) {
                            JOptionPane.showMessageDialog(getContentPane(), (1 - gameManager.board[SIDE]) + " wins");
                            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                            MainFrame frame = new MainFrame(width, height);
                            frame.setVisible(true);
                            dispose();
                        }
                        else if(checkDraw(gameManager.board)) {
                            JOptionPane.showMessageDialog(getContentPane(), "Draw");
                            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                            MainFrame frame = new MainFrame(width, height);
                            frame.setVisible(true);
                            dispose();
                        }
                        else {
                            Pair<Integer, Integer> mv = alphaBetaSoft(Arrays.copyOf(gameManager.board, 3), DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                            int move = gameManager.makeMove(mv.second);
                            System.out.println("Move: " + move);
                            evaluationLbl.setText("Evaluation: " + mv.first);
                            nodeLbl.setText("Nodes: " + Evaluate.nodes);
                            Evaluate.nodes = 0;
                            panels[move].setColor(Utils.Constants.COLOR[(int) (1 - gameManager.board[SIDE])]);
                            panels[move].repaint();
                        }
                    }
                    //printBoard(gameManager.board);
                }

                if(gameManager != null) {
                    if (checkWin(gameManager.board, (int) (1 - gameManager.board[SIDE]))) {
                        JOptionPane.showMessageDialog(getContentPane(), (1 - gameManager.board[SIDE]) + " wins");
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        MainFrame frame = new MainFrame(width, height);
                        frame.setVisible(true);
                        dispose();
                    }
                    if(checkDraw(gameManager.board)) {
                        JOptionPane.showMessageDialog(getContentPane(), "Draw");
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        MainFrame frame = new MainFrame(width, height);
                        frame.setVisible(true);
                        dispose();
                    }
                }
            }
        });


    }


}
