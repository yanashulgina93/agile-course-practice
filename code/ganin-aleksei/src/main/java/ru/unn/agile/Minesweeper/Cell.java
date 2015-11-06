package ru.unn.agile.Minesweeper;

public class Cell {
    private boolean mine = false;
    private boolean issue = false;
    private boolean flag = false;
    private boolean openV = false;
    private int value = 0;

    private final int positionY;
    private final int positionX;

    private static final int CELL_VALUE_0 = 0;
    private static final int CELL_VALUE_1 = 1;
    private static final int CELL_VALUE_2 = 2;
    private static final int CELL_VALUE_3 = 3;
    private static final int CELL_VALUE_4 = 4;
    private static final int CELL_VALUE_5 = 5;
    private static final int CELL_VALUE_6 = 6;
    private static final int CELL_VALUE_7 = 7;
    private static final int CELL_VALUE_8 = 8;

    public Cell(final int positionYA, final int positionXA) {
        positionY = positionYA;
        positionX = positionXA;
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
        issue = true;
    }

    public boolean isIssue() {
        return issue;
    }

    public void unsetIssue() {
        issue = false;
    }

    public void setFlag() {
        flag = true;
    }

    public boolean isFlag() {
        return  flag;
    }

    public void unsetFlag() {
        flag = false;
    }

    public void open() {
        openV = true;
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

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
