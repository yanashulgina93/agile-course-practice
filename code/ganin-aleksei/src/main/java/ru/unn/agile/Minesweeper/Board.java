package ru.unn.agile.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel{
    private static int boardWidth;
    private static int boardHeight;

    private boolean lost = false;
    private Minesweeper minesweeper;
    private int minesCount = 0;
    private int openCellsCount = 0;
    private int flagCellsCount = 0;

    private Cell[][] cells;

    private void setMinesCount(int _minesCount){
        minesCount = _minesCount;
        minesweeper.setMineCounter(_minesCount - flagCellsCount);
    }

    private void setFlagCellsCount(int _flagCellsCount){
        flagCellsCount = _flagCellsCount;
        minesweeper.setMineCounter(minesCount - _flagCellsCount);
    }

    Board(Minesweeper _minesweeper, int _boardHeight, int _boardWidth){
        minesweeper = _minesweeper;
        boardHeight = _boardHeight;
        boardWidth = _boardWidth;
        setLayout(null);

        cells = new Cell[boardHeight][boardWidth];
        for(int positionY = 0; positionY < boardHeight; positionY++){
            cells[positionY] = new Cell[boardWidth];
            for(int positionX = 0; positionX < boardWidth; positionX++){
                Cell cell = new Cell(this, positionY, positionX);
                cell.setBounds(positionY * 20, positionX * 20, 20, 20);
                add(cell);
                cells[positionY][positionX] = cell;
            }
        }
    }

    private int allCellsCount(){
        return  boardWidth * boardHeight;
    }

    private ArrayList<Cell> getNeighboringCells(int positionY, int positionX){
        ArrayList<Cell> neighboringCells = new ArrayList<Cell>();
        for(int y = positionY > 0 ? positionY - 1 : positionY,
            yStop = positionY < boardHeight - 1 ? positionY + 1 : positionY;
            y <= yStop; y++){
            for(int x = positionX > 0 ? positionX - 1 : positionX,
                xStop = positionX < boardWidth - 1 ? positionX + 1 : positionX;
                x <= xStop; x++){
                if(!(y == positionY && x == positionX)){
                    neighboringCells.add(cells[y][x]);
                }
            }

        }
        return  neighboringCells;
    }

    private void incValues(int positionY, int positionX){
        ArrayList<Cell> neighboringCells = getNeighboringCells(positionY, positionX);
        for(int i = 0; i < neighboringCells.size(); i++){
            Cell cell = neighboringCells.get(i);
            cell.setValue(cell.getValue() + 1);
        }
        /*
        for(int y = positionY > 0 ? positionY - 1 : positionY,
            yStop = positionY < boardHeight - 1 ? positionY + 1 : positionY;
            y <= yStop; y++){
            for(int x = positionX > 0 ? positionX - 1 : positionX,
                xStop = positionX < boardWidth - 1 ? positionX + 1 : positionX;
                x <= xStop; x++){
                if(!(y == positionY && x == positionX)){
                    Cell cell = cells[y][x];
                    cell.setValue(cell.getValue() + 1);

                }
            }

        }
        */
    }

    public void setMine(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(!cell.isMine()) {
            cell.setMine();
            incValues(positionY, positionX);
        }
    }

    private void openNeighboringCells(int positionY, int positionX){
        ArrayList<Cell> neighboringCells = getNeighboringCells(positionY, positionX);
        for(int i = 0; i < neighboringCells.size(); i++){
            Cell cell = neighboringCells.get(i);
            if(!cell.isMine()){
                openCell(cell.getPositionY(), cell.getPositionY());
            }
        }

        /*
        for(int y = positionY > 0 ? positionY - 1 : positionY,
            yStop = positionY < boardHeight - 1 ? positionY + 1 : positionY;
            y <= yStop; y++){
            for(int x = positionX > 0 ? positionX - 1 : positionX,
                xStop = positionX < boardWidth - 1 ? positionX + 1 : positionX;
                x <= xStop; x++){
                if(!(y == positionY && x == positionX)){
                    if(!cells[y][x].isMine()){
                        openCell(y, x);
                    }
                }
            }

        }
        */
    }

    public void openCell(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(!lost && !cell.isIssue() && !cell.isFlag() && !cell.isOpen()){
            cell.open();
            openCellsCount++;
            if(cell.isMine()) {
                lost = true;
                minesweeper.end(false);
            } else if(allCellsCount() - openCellsCount == minesCount){
                minesweeper.end(true);
            } else if(cell.getValue() == 0){
                openNeighboringCells(positionY, positionX);
            }
        }
    }

    public boolean isLost(){
        return  lost;
    }

    public void  setFlag(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(!cell.isFlag()) {
            setFlagCellsCount(flagCellsCount + 1);
            cell.setFlag();
        }
    }

    public void  unsetFlag(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(cell.isFlag()){
            setFlagCellsCount(flagCellsCount - 1);
            cell.unsetFlag();
        }
    }

    public int getValue(int positionY, int positionX){
        return cells[positionY][positionX].getValue();
    }

    public void clear(){
        for(int positionY = 0; positionY < boardHeight; positionY++){
            for(int positionX = 0; positionX < boardWidth; positionX++){
                cells[positionY][positionX].clear();
            }
        }
        lost = false;
        setMinesCount(0);
        setFlagCellsCount(0);
    }

    public boolean isMine(int positionY, int positionX){
        return cells[positionY][positionX].isMine();
    }

    public void setMinesRandom(int numMines){
        Random random = new Random();
        for(int i = 0; i < numMines; i++) {
            int positionY = 0;
            int positionX = 0;
            while (isMine(positionY, positionX)){
                positionY = random.nextInt(boardHeight);
                positionX = random.nextInt(boardWidth);
            }
            setMine(positionY, positionX);
        }

        setMinesCount(minesCount + numMines);
    }

    public int findMinesCount(){
        int minesCount = 0;
        for(int positionY = 0; positionY < boardHeight; positionY++){
            for(int positionX = 0; positionX < boardWidth; positionX++){
                if(cells[positionY][positionX].isMine()){
                    minesCount++;
                }
            }
        }
        return  minesCount;
    }
}
