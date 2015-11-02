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

    private final Board board;
    private final int positionY;
    private final int positionX;

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

    private static final int CELL_VALUE_0 = 0;
    private static final int CELL_VALUE_1 = 1;
    private static final int CELL_VALUE_2 = 2;
    private static final int CELL_VALUE_3 = 3;
    private static final int CELL_VALUE_4 = 4;
    private static final int CELL_VALUE_5 = 5;
    private static final int CELL_VALUE_6 = 6;
    private static final int CELL_VALUE_7 = 7;
    private static final int CELL_VALUE_8 = 8;

    public Cell(final Board boardA, final int positionYA, final int positionXA) {

        board = boardA;
        positionY = positionYA;
        positionX = positionXA;

        setIcon(closeIcon);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (!isOpen() && !board.isLost() && isFlag()) {
                        board.unsetFlag(positionY, positionX);
                        setIssue();
                    } else if (!isOpen() && !board.isLost() && isIssue()) {
                        unsetIssue();
                    } else if (!isOpen() && !board.isLost()) {
                        board.setFlag(positionY, positionX);
                    }
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    board.openCell(positionY, positionX);
                }
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                /* empty */
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                /* empty */
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                /* empty */
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                /* empty */
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

    public void setValue(final int val) {
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

    private void setIconForValue(final int value) {
        if (value == CELL_VALUE_0) {
            setIcon(val0Icon);
        } else if (value == CELL_VALUE_1) {
            setIcon(val1Icon);
        } else if (value == CELL_VALUE_2) {
            setIcon(val2Icon);
        } else if (value == CELL_VALUE_3) {
            setIcon(val3Icon);
        } else if (value == CELL_VALUE_4) {
            setIcon(val4Icon);
        } else if (value == CELL_VALUE_5) {
            setIcon(val5Icon);
        } else if (value == CELL_VALUE_6) {
            setIcon(val6Icon);
        } else if (value == CELL_VALUE_7) {
            setIcon(val7Icon);
        } else if (value == CELL_VALUE_8) {
            setIcon(val8Icon);
        }
    }

    public void open() {
        openV = true;
        if (isMine()) {
            setIcon(mineIcon);
        } else {
            setIconForValue(getValue());
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
    protected void paintComponent(final Graphics g) {
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
