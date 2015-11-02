package ru.unn.agile.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Minesweeper {

    private static ImageIcon smileIcon = new ImageIcon("images/smile_50x50.png");
    private static ImageIcon deadIcon = new ImageIcon("images/dead_50x50.png");
    private static ImageIcon winnerIcon = new ImageIcon("images/winner_50x50.png");

    private final  Board board;
    private final  JLabel smile;
    private final  JLabel mineCounter;

    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 700;

    private static final int BOARD_HEIGHT = 32;
    private static final int BOARD_WIDTH = 32;


    private static final int SMILE_POSITION_X = 640;
    private static final int SMILE_POSITION_Y = 0;
    private static final int SMILE_HEIGHT = 50;
    private static final int SMILE_WIDTH = 50;

    private static final int MINE_COUNTER_POSITION_X = 640;
    private static final int MINE_COUNTER_POSITION_Y = 70;
    private static final int MINE_COUNTER_HEIGHT = 20;
    private static final int MINE_COUNTER_WIDTH = 50;

    private static final int DEFAULT_MINES = 100;


    private boolean isEndV = false;

    public Minesweeper() {
        JFrame frame = new JFrame("Сапер");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        board = new Board(this, BOARD_HEIGHT, BOARD_WIDTH);


        smile = new JLabel() {
            @Override
            protected void paintComponent(final Graphics g) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        smile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    newGame();
                }
            }

            @Override
            public void mousePressed(final MouseEvent es) {
                /* empty */

            }

            @Override
            public void mouseReleased(final MouseEvent es) {
                /* empty */
            }

            @Override
            public void mouseEntered(final MouseEvent es) {
                /* empty */
            }

            @Override
            public void mouseExited(final MouseEvent es) {
                /* empty */
            }
        });
        smile.setIcon(smileIcon);
        smile.setBounds(SMILE_POSITION_X, SMILE_POSITION_Y, SMILE_WIDTH, SMILE_HEIGHT);

        mineCounter = new JLabel();
        mineCounter.setBounds(
                MINE_COUNTER_POSITION_X,
                MINE_COUNTER_POSITION_Y,
                MINE_COUNTER_WIDTH,
                MINE_COUNTER_HEIGHT
        );


        frame.add(smile);
        frame.add(mineCounter);
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.setVisible(true);

        newGame();
    }

    public static void main(final String[] args) {
        new Minesweeper();
    }

    public final void newGame() {
        smile.setIcon(smileIcon);
        isEndV = false;
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public boolean isEnd() {
        return isEndV;
    }

    public void end(final boolean isWinner) {
        isEndV = true;
        smile.setIcon(isWinner ? winnerIcon : deadIcon);
    }

    public void setMineCounter(final int count) {
        mineCounter.setText(String.valueOf(count));
    }
}
