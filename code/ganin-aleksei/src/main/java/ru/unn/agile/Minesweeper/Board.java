package ru.unn.agile.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel {
    private final int boardWidth;
    private final int boardHeight;

    public static final int CELL_SIZE = 20;

    private boolean lost = false;
    private final Minesweeper minesweeper;

    private int minesCount;
    private int openCellsCount;
    private int flagCellsCount;


    private final Cell[][] cells;

    Board(final Minesweeper minesweeper, final int boardHeight, final int boardWidth) {
        minesCount = 0;
        openCellsCount = 0;
        flagCellsCount = 0;

        this.minesweeper = minesweeper;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setLayout(null);

        cells = new Cell[boardHeight][boardWidth];
        for (int positionY = 0; positionY < boardHeight; positionY++) {
            cells[positionY] = new Cell[boardWidth];
            for (int positionX = 0; positionX < boardWidth; positionX++) {
                Cell cell = new Cell(this, positionY, positionX);
                cell.setBounds(positionY * CELL_SIZE, positionX * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                add(cell);
                cells[positionY][positionX] = cell;
            }
        }
    }

    private void setMinesCount(final int minesCount) {
        this.minesCount = minesCount;
        minesweeper.setMineCounter(minesCount - flagCellsCount);
    }

    private void setFlagCellsCount(final int flagCellsCount) {
        this.flagCellsCount = flagCellsCount;
        minesweeper.setMineCounter(minesCount - flagCellsCount);
    }

    private int allCellsCount() {
        return  boardWidth * boardHeight;
    }

    private ArrayList<Cell> getNeighboringCells(final int positionY, final int positionX) {
        ArrayList<Cell> neighboringCells = new ArrayList<Cell>();
        for (int y = positionY > 0 ? positionY - 1 : positionY,
            yStop = positionY < boardHeight - 1 ? positionY + 1 : positionY;
            y <= yStop; y++) {
            for (int x = positionX > 0 ? positionX - 1 : positionX,
                xStop = positionX < boardWidth - 1 ? positionX + 1 : positionX;
                x <= xStop; x++) {
                if (!(y == positionY && x == positionX)) {
                    neighboringCells.add(cells[y][x]);
                }
            }

        }
        return  neighboringCells;
    }

    private void incValues(final int positionY, final int positionX) {
        ArrayList<Cell> neighboringCells = getNeighboringCells(positionY, positionX);
        for (int i = 0; i < neighboringCells.size(); i++) {
            Cell cell = neighboringCells.get(i);
            cell.setValue(cell.getValue() + 1);
        }
    }

    public void setMine(final int positionY, final int positionX) {
        Cell cell = cells[positionY][positionX];
        if (!cell.isMine()) {
            cell.setMine();
            incValues(positionY, positionX);
        }
    }

    private void openNeighboringCells(final int positionY, final int positionX) {
        ArrayList<Cell> neighboringCells = getNeighboringCells(positionY, positionX);
        for (int i = 0; i < neighboringCells.size(); i++) {
            Cell cell = neighboringCells.get(i);
            if (!cell.isMine()) {
                openCell(cell.getPositionY(), cell.getPositionY());
            }
        }
    }

    public void openCell(final int positionY, final int positionX) {
        Cell cell = cells[positionY][positionX];
        if (!lost && !cell.isIssue() && !cell.isFlag() && !cell.isOpen()) {
            cell.open();
            openCellsCount++;
            if (cell.isMine()) {
                lost = true;
                minesweeper.end(false);
            } else if (allCellsCount() - openCellsCount == minesCount) {
                minesweeper.end(true);
            } else if (cell.getValue() == 0) {
                openNeighboringCells(positionY, positionX);
            }
        }
    }

    public boolean isLost() {
        return  lost;
    }

    public void  setFlag(final int positionY, final int positionX) {
        Cell cell = cells[positionY][positionX];
        if (!cell.isFlag()) {
            setFlagCellsCount(flagCellsCount + 1);
            cell.setFlag();
        }
    }

    public void  unsetFlag(final int positionY, final int positionX) {
        Cell cell = cells[positionY][positionX];
        if (cell.isFlag()) {
            setFlagCellsCount(flagCellsCount - 1);
            cell.unsetFlag();
        }
    }

    public int getValue(final int positionY, final int positionX) {
        return cells[positionY][positionX].getValue();
    }

    public void clear() {
        for (int positionY = 0; positionY < boardHeight; positionY++) {
            for (int positionX = 0; positionX < boardWidth; positionX++) {
                cells[positionY][positionX].clear();
            }
        }
        lost = false;
        setMinesCount(0);
        setFlagCellsCount(0);
    }

    public boolean isMine(final int positionY, final int positionX) {
        return cells[positionY][positionX].isMine();
    }

    public void setMinesRandom(final int numMines) {
        Random random = new Random();
        for (int i = 0; i < numMines; i++) {
            int positionY = 0;
            int positionX = 0;
            while (isMine(positionY, positionX)) {
                positionY = random.nextInt(boardHeight);
                positionX = random.nextInt(boardWidth);
            }
            setMine(positionY, positionX);
        }

        setMinesCount(minesCount + numMines);
    }

    public int findMinesCount() {
        int minesCount = 0;
        for (int positionY = 0; positionY < boardHeight; positionY++) {
            for (int positionX = 0; positionX < boardWidth; positionX++) {
                if (cells[positionY][positionX].isMine()) {
                    minesCount++;
                }
            }
        }
        return  minesCount;
    }

    public Cell getCell(final  int positionY, final int positionX) {
        return cells[positionY][positionX];
    }
}
