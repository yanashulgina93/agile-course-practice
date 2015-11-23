package ru.unn.agile.Minesweeper.viewmodel;

import javax.swing.*;
import java.awt.*;

import ru.unn.agile.Minesweeper.Model.Model;

public class ViewModel {

    private final Model minesweeperModel = new Model();

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

    private static ImageIcon smileIcon = new ImageIcon("images/smile_50x50.png");
    private static ImageIcon deadIcon = new ImageIcon("images/dead_50x50.png");
    private static ImageIcon winnerIcon = new ImageIcon("images/winner_50x50.png");

    private static final int CELL_VALUE_0 = 0;
    private static final int CELL_VALUE_1 = 1;
    private static final int CELL_VALUE_2 = 2;
    private static final int CELL_VALUE_3 = 3;
    private static final int CELL_VALUE_4 = 4;
    private static final int CELL_VALUE_5 = 5;
    private static final int CELL_VALUE_6 = 6;
    private static final int CELL_VALUE_7 = 7;
    private static final int CELL_VALUE_8 = 8;

    public ViewModel() {
        /* empty */
    }

    public void newGame() {
        minesweeperModel.newGame();
    }

    public void openCell(final int x, final int y) {
        minesweeperModel.openCell(x, y);
    }

    public void markCell(final int x, final int y) {
        minesweeperModel.markCell(x, y);
    }

    public ImageIcon getCellIcon(final int x, final int y) {
        if (minesweeperModel.isCellClose(x, y)) {
            if (minesweeperModel.isCellFlag(x, y)) {
                return flagIcon;
            } else if (minesweeperModel.isCellIssue(x, y)) {
                return issueIcon;
            } else {
                return closeIcon;
            }
        } else if (minesweeperModel.isCellMine(x, y)) {
            return mineIcon;
        } else {
            int value = minesweeperModel.getCellValue(x, y);
            if (value == CELL_VALUE_0) {
                return val0Icon;
            } else if (value == CELL_VALUE_1) {
                return val1Icon;
            } else if (value == CELL_VALUE_2) {
                return val2Icon;
            } else if (value == CELL_VALUE_3) {
                return val3Icon;
            } else if (value == CELL_VALUE_4) {
                return val4Icon;
            } else if (value == CELL_VALUE_5) {
                return val5Icon;
            } else if (value == CELL_VALUE_6) {
                return val6Icon;
            } else if (value == CELL_VALUE_7) {
                return val7Icon;
            } else {
                return val8Icon;
            }
        }
    }

    public ImageIcon getSmileIcon() {
        if (minesweeperModel.isGameEnd()) {
            if (minesweeperModel.isLost()) {
                return deadIcon;
            } else {
                return winnerIcon;
            }
        }
        return smileIcon;
    }

    public void boardClear() {
        minesweeperModel.boardClear();
    }

    public void setMine(final int x, final int y) {
        minesweeperModel.setMine(x, y);
    }

    public String getMineCounter() {
        return Integer.toString(minesweeperModel.getMineCounter());
    }

    public int getBoardHeight() {
        return minesweeperModel.getBoardHeight();
    }

    public int getBoardWidth() {
        return minesweeperModel.getBoardWidth();
    }

    public ImageIcon getFlagIcon() {
        return flagIcon;
    }

    public ImageIcon getCloseIcon() {
        return closeIcon;
    }

    public ImageIcon getIssueIcon() {
        return issueIcon;
    }

    public ImageIcon getMineIcon() {
        return mineIcon;
    }

    public ImageIcon getVal0Icon() {
        return val0Icon;
    }

    public ImageIcon getVal1Icon() {
        return val1Icon;
    }

    public ImageIcon getVal2Icon() {
        return val2Icon;
    }

    public ImageIcon getVal3Icon() {
        return val3Icon;
    }

    public ImageIcon getVal4Icon() {
        return val4Icon;
    }

    public ImageIcon getVal5Icon() {
        return val5Icon;
    }

    public ImageIcon getVal6Icon() {
        return val6Icon;
    }

    public ImageIcon getVal7Icon() {
        return val7Icon;
    }

    public ImageIcon getVal8Icon() {
        return val8Icon;
    }

    public ImageIcon getSmileIco() {
        return smileIcon;
    }

    public ImageIcon getDeadIcon() {
        return deadIcon;
    }

    public ImageIcon getWinnerIcon() {
        return winnerIcon;
    }
}
