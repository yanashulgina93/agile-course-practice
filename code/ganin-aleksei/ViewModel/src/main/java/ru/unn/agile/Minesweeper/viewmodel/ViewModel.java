package ru.unn.agile.Minesweeper.viewmodel;

import javax.swing.*;

import ru.unn.agile.Minesweeper.Model.Model;

public class ViewModel {

    private final Model minesweeperModel = new Model();

    private static String flagText = new String ("!");
    private static String closeText = new String("#");
    private static String issueText = new String("?");
    private static String mineText = new String("*");

    private static String smileText = new String("Живой");
    private static String deadText = new String("Мертвый");
    private static String winnerText = new String("Выиграл");

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

    public String getCellText(final int x, final int y) {
        if (minesweeperModel.isCellClose(x, y)) {
            if (minesweeperModel.isCellFlag(x, y)) {
                return flagText;
            } else if (minesweeperModel.isCellIssue(x, y)) {
                return issueText;
            } else {
                return closeText;
            }
        } else if (minesweeperModel.isCellMine(x, y)) {
            return mineText;
        } else {
            int value = minesweeperModel.getCellValue(x, y);
            if(value == 0) {
                return "";
            } else {
                return Integer.toString(minesweeperModel.getMineCounter());
            }
        }
    }

    public String getSmileText() {
        if (minesweeperModel.isGameEnd()) {
            if (minesweeperModel.isLost()) {
                return deadText;
            } else {
                return winnerText;
            }
        }
        return smileText;
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

    public String getFlagText() {
        return flagText;
    }

    public String getCloseText() {
        return closeText;
    }

    public String getIssueText() {
        return issueText;
    }

    public String getMineText() {
        return mineText;
    }

    public String getSmileTex() {
        return smileText;
    }

    public String getDeadText() {
        return deadText;
    }

    public String getWinnerText() {
        return winnerText;
    }
}
