package ru.unn.agile.Minesweeper.Model;


public class Model {
    public static final int BOARD_HEIGHT = 32;
    public static final int BOARD_WIDTH = 32;
}

/*
import java.util.ArrayList;
import java.util.Random;

public class Model {

    public static final int BOARD_HEIGHT = 32;
    public static final int BOARD_WIDTH = 32;

    private static final int DEFAULT_MINES = 100;

    private boolean lost;
    private boolean end;

    //private int mineCounter;

    private final BoardModel board = new BoardModel(BOARD_HEIGHT, BOARD_WIDTH);



    public Model() {
        //mineCounter = DEFAULT_MINES;
        lost = false;
        end = false;
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public int getMineCounter() {
        board.getMineCounter();
        //return mineCounter;
    }

    public void openCell(final int x, final int y) {
        if (!isGameEnd()) {
            board.openCell(x, y);
        }
    }

    public void markCell(final int x, final int y) {
        if (isGameEnd()) {
            return;
        }
        BoardModel.CellModel cell = board.cells[y][x];
        if (!cell.isOpen() && cell.isFlag()) {
            mineCounter++;
            board.unsetFlag(y, x);
            cell.setIssue(true);
        } else if (!cell.isOpen() && cell.isIssue()) {
            cell.setIssue(false);
        } else if (!cell.isOpen()) {
            board.setFlag(y, x);
            mineCounter--;
        }
    }

    public void newGame() {
        //mineCounter = DEFAULT_MINES;
        lost = false;
        end = false;
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public void boardClear() {
        //mineCounter = 0;
        board.clear();
    }

    public void setMine(final int x, final int y) {
        if (!board.isMine(y, x)) {
            //mineCounter++;
        }
        board.setMine(y, x);
    }

    public boolean isCellFlag(final int x, final int y) {
        return board.cells[y][x].isFlag();
    }

    public boolean isCellIssue(final int x, final int y) {
        return board.cells[y][x].isIssue();
    }

    public boolean isCellClose(final int x, final int y) {
        return !board.cells[y][x].isOpen();
    }

    public boolean isCellMine(final int x, final int y) {
        return board.cells[y][x].isMine();
    }

    public int getCellValue(final int x, final int y) {
        return board.cells[y][x].getValue();
    }

    public boolean isGameEnd() {
        return end;
    }

    public boolean isLost() {
        return lost;
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }
}
*/