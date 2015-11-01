package ru.unn.agile.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Minesweeper {

    private static ImageIcon smileIcon = new ImageIcon("images/smile_50x50.png");
    private static ImageIcon deadIcon = new ImageIcon("images/dead_50x50.png");
    private static ImageIcon winnerIcon = new ImageIcon("images/winner_50x50.png");

    private Board board;
    private JLabel smile;
    private JLabel mineCounter;

    private final int windowHeight = 700;
    private final int windowWidth = 700;

    private final int boardHeight = 32;
    private final int boardWidth = 32;


    private final int smilePositionX = 640;
    private final int smilePositionY = 0;
    private final int smileHeigth = 50;
    private final int smileWidth = 50;

    private final int mineCounterPositionX = 640;
    private final int mineCounterPositionY = 70;
    private final int mineCounterHeigth = 20;
    private final int mineCounterWidth = 50;

    private final int defaultMines = 100;


    private boolean isEndV = false;

    public Minesweeper(){
        JFrame frame = new JFrame("Сапер");
        frame.setSize(windowWidth, windowHeight);

        board = new Board(this, boardHeight, boardWidth);


        smile = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        smile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    newGame();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        smile.setIcon(smileIcon);
        smile.setBounds(smilePositionX, smilePositionY, smileWidth, smileHeigth);

        mineCounter = new JLabel();
        mineCounter.setBounds(mineCounterPositionX, mineCounterPositionX, mineCounterWidth, mineCounterHeigth);


        frame.add(smile);
        frame.add(mineCounter);
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        newGame();
    }

    public static void main(String[] args) {
        new Minesweeper();
    }

    public void newGame() {
        smile.setIcon(smileIcon);
        isEndV = false;
        board.clear();
        board.setMinesRandom(defaultMines);
    }

    public boolean isEnd() {
        return isEndV;
    }

    public void end(boolean isWinner) {
        isEndV = true;
        smile.setIcon(isWinner ? winnerIcon : deadIcon);
    }

    public void setMineCounter(int count) {
        mineCounter.setText(String.valueOf(count));
    }
}
