package ru.unn.agile.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JLabel {
    private boolean mine = false;
    private boolean issue = false;
    private boolean flag = false;
    private boolean openV = false;
    private int value = 0;

    private Board board;
    private int positionY;
    private int positionX;

    private static ImageIcon flagIcon = new ImageIcon("images/flag_20x20.png");
    private static ImageIcon closeIcon = new ImageIcon("images/close_20x20.png");
    private static ImageIcon issueIcon = new ImageIcon("images/issue_20x20.png");
    private static ImageIcon mineIcon = new ImageIcon("images/mine_20x20.png");

    private static ImageIcon val0Icon = new ImageIcon("images/val_0_20x20.png");
    private static ImageIcon val1Icon = new ImageIcon("images/val_1_20x20.png");
    private static ImageIcon val2Icon = new ImageIcon("images/val_2_20x20.png");
    private static ImageIcon val3Icon = new ImageIcon("images/val_3_20x20.png");
    private static ImageIcon val4Icon = new ImageIcon("images/val_4_20x20.png");
    private static ImageIcon val5Icon = new ImageIcon("images/val_5_20x20.png");
    private static ImageIcon val6Icon = new ImageIcon("images/val_6_20x20.png");
    private static ImageIcon val7Icon = new ImageIcon("images/val_7_20x20.png");
    private static ImageIcon val8Icon = new ImageIcon("images/val_8_20x20.png");

    public Cell(Board boardA, int positionYA, int positionXA) {

        board = boardA;
        positionY = positionYA;
        positionX = positionXA;

        setIcon(closeIcon);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    if(!isOpen() && !board.isLost()) {
                        if (isFlag()) {
                            board.unsetFlag(positionY, positionX);
                            setIssue();
                        } else if (isIssue()) {
                            unsetIssue();
                        } else {
                            board.setFlag(positionY, positionX);
                        }
                    }
                }
                if(SwingUtilities.isLeftMouseButton(e)) {
                    board.openCell(positionY, positionX);
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
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setMine() {
        mine = true;
    }
    public boolean isMine() {
        return  mine;
    }

    public void setValue(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

    public void setIssue() {
        setIcon(issueIcon);
        issue = true;
    }

    public boolean isIssue() {
        return issue;
    }

    public void unsetIssue() {
        setIcon(closeIcon);
        issue = false;
    }

    public void setFlag() {
        setIcon(flagIcon);
        flag = true;
    }

    public boolean isFlag() {
        return  flag;
    }

    public void unsetFlag() {
        setIcon(closeIcon);
        flag = false;
    }

    public void open() {
        openV = true;
        if(isMine()) {
            setIcon(mineIcon);
        } else {
            switch (getValue()) {
                case 0:  setIcon(val0Icon);
                    break;
                case 1:  setIcon(val1Icon);
                    break;
                case 2:  setIcon(val2Icon);
                    break;
                case 3:  setIcon(val3Icon);
                    break;
                case 4:  setIcon(val4Icon);
                    break;
                case 5:  setIcon(val5Icon);
                    break;
                case 6:  setIcon(val6Icon);
                    break;
                case 7:  setIcon(val7Icon);
                    break;
                case 8:  setIcon(val8Icon);
                    break;
            }
        }
    }

    public boolean isOpen() {
        return  openV;
    }

    public void clear() {
        unsetIssue();
        unsetFlag();
        setValue(0);
        mine = false;
        openV = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
