package ru.unn.agile.Minesweeper.Model;

import ru.unn.agile.Minesweeper.Model_OLD.CellModel;

import java.util.ArrayList;
import java.util.Random;

class BoardModel {

    private final int boardWidth;
    private final int boardHeight;

    public static final int CELL_SIZE = 20;

    //private int mineCounter;
    private int minesCount;
    private int openCellsCount;
    private int flagCellsCount;

    private final CellModel[][] cells;

    BoardModel(final int boardHeight, final int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        cells = new CellModel[boardHeight][boardWidth];
        for (int y = 0; y < boardHeight; y++) {
            cells[y] = new CellModel[boardWidth];
            for (int x = 0; x < boardWidth; x++) {
                CellModel cell = new CellModel(y, x);
                cells[y][x] = cell;
            }
        }
    }

    private void setMinesCount(final int minesCount) {
        this.minesCount = minesCount;
    }

    private void setFlagCellsCount(final int flagCellsCount) {
        this.flagCellsCount = flagCellsCount;
    }

    private int allCellsCount() {
        return boardWidth * boardHeight;
    }

    private ArrayList<CellModel> getNeighboringCells(final int y, final int x) {
        ArrayList<CellModel> neighboringCells = new ArrayList<CellModel>();
        for (int currentY = y > 0 ? y - 1 : y,
             yStop = y < boardHeight - 1 ? y + 1 : y;
             currentY <= yStop; currentY++) {
            for (int currentX = x > 0 ? x - 1 : x,
                 xStop = x < boardWidth - 1 ? x + 1 : x;
                 currentX <= xStop; currentX++) {
                if (!(currentY == y && currentX == x)) {
                    neighboringCells.add(cells[currentY][currentX]);
                }
            }

        }
        return neighboringCells;
    }

    private void incValues(final int y, final int x) {
        ArrayList<CellModel> neighboringCells = getNeighboringCells(y, x);
        for (int i = 0; i < neighboringCells.size(); i++) {
            CellModel cell = neighboringCells.get(i);
            cell.setValue(cell.getValue() + 1);
        }
    }

    public void setMine(final int y, final int x) {
        CellModel cell = cells[y][x];
        if (!cell.isMine()) {
            cell.setMine();
            incValues(y, x);
        }
    }

    private void openNeighboringCells(final int y, final int x) {
        ArrayList<CellModel> neighboringCells = getNeighboringCells(y, x);
        for (int i = 0; i < neighboringCells.size(); i++) {
            CellModel cell = neighboringCells.get(i);
            if (!cell.isMine()) {
                openCell(cell.getX(), cell.getY());
            }
        }
    }

    private void openAllMines() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                CellModel cell = cells[y][x];
                if (cell.isMine()) {
                    cell.open();
                }
            }
        }
    }

    public void openCell(final int x, final int y) {
        CellModel cell = cells[y][x];
        if (!lost && !cell.isIssue() && !cell.isFlag() && !cell.isOpen()) {
            cell.open();
            openCellsCount++;
            if (cell.isMine()) {
                lost = true;
                end = true;
                openAllMines();
            } else if (allCellsCount() - openCellsCount == minesCount) {
                lost = false;
                end = true;
            } else if (cell.getValue() == 0) {
                openNeighboringCells(y, x);
            }
        }
    }

    public void  setFlag(final int y, final int x) {
        CellModel cell = cells[y][x];
        if (!cell.isFlag()) {
            setFlagCellsCount(flagCellsCount + 1);
            cell.setFlag(true);
        }
    }

    public void  unsetFlag(final int y, final int x) {
        CellModel cell = cells[y][x];
        if (cell.isFlag()) {
            setFlagCellsCount(flagCellsCount - 1);
            cell.setFlag(false);
        }
    }

    public void clear() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                cells[y][x].clear();
            }
        }
        lost = false;
        setMinesCount(0);
        setFlagCellsCount(0);
        openCellsCount = 0;
    }

    public boolean isMine(final int y, final int x) {
        return cells[y][x].isMine();
    }

    public void setMinesRandom(final int numMines) {
        Random random = new Random();
        for (int i = 0; i < numMines; i++) {
            int y = 0;
            int x = 0;
            while (isMine(y, x)) {
                y = random.nextInt(boardHeight);
                x = random.nextInt(boardWidth);
            }
            setMine(y, x);
        }

        setMinesCount(minesCount + numMines);
    }
}