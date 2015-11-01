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

    private boolean _isEnd = false;

    public Minesweeper(){
        JFrame frame = new JFrame("Сапер");
        frame.setSize(700, 700);

        board = new Board(this, 32, 32);


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
        smile.setBounds(640, 0, 50, 50);

        mineCounter = new JLabel();
        mineCounter.setBounds(640, 70, 50, 20);


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

    public void newGame(){
        smile.setIcon(smileIcon);
        _isEnd = false;
        board.clear();
        board.setMinesRandom(100);
    }

    public boolean isEnd(){
        return _isEnd;
    }

    public void end(boolean isWinner){
        _isEnd = true;
        smile.setIcon(isWinner ? winnerIcon : deadIcon);
    }

    public void setMineCounter(int count){
        mineCounter.setText(String.valueOf(count));
    }
}
