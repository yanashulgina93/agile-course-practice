package ru.unn.agile.Minesweeper.Model;

import java.util.ArrayList;
import java.util.Random;

public class Model {

    public static final int BOARD_HEIGHT = 32;
    public static final int BOARD_WIDTH = 32;

    private static final int DEFAULT_MINES = 100;

    private boolean lost;
    private boolean end;

    private int mineCounter;

    private final BoardModel board = new BoardModel(BOARD_HEIGHT, BOARD_WIDTH);

    private class BoardModel {

        private final int boardWidth;
        private final int boardHeight;

        public static final int CELL_SIZE = 20;

        private int minesCount;
        private int openCellsCount;
        private int flagCellsCount;

        private final CellModel[][] cells;

        private class CellModel {
            private boolean mine;
            private boolean issue;
            private boolean flag;
            private boolean openV;
            private int value;

            private final int y;
            private final int x;

            public CellModel(final int y, final int x) {
                this.y = y;
                this.x = x;
            }

            public void setMine() {
                mine = true;
            }
            public boolean isMine() {
                return mine;
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
                return flag;
            }

            public void unsetFlag() {
                flag = false;
            }

            public void open() {
                openV = true;
            }

            public boolean isOpen() {
                return openV;
            }

            public void clear() {
                unsetIssue();
                unsetFlag();
                setValue(0);
                openV = false;
                mine = false;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }
        }

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
                cell.setFlag();
            }
        }

        public void  unsetFlag(final int y, final int x) {
            CellModel cell = cells[y][x];
            if (cell.isFlag()) {
                setFlagCellsCount(flagCellsCount - 1);
                cell.unsetFlag();
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

    public Model() {
        mineCounter = DEFAULT_MINES;
        lost = false;
        end = false;
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public int getMineCounter() {
        return mineCounter;
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
            cell.setIssue();
        } else if (!cell.isOpen() && cell.isIssue()) {
            cell.unsetIssue();
        } else if (!cell.isOpen()) {
            board.setFlag(y, x);
            mineCounter--;
        }
    }

    public void newGame() {
        mineCounter = DEFAULT_MINES;
        lost = false;
        end = false;
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public void boardClear() {
        mineCounter = 0;
        board.clear();
    }

    public void setMine(final int x, final int y) {
        if (!board.isMine(y, x)) {
            mineCounter++;
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
