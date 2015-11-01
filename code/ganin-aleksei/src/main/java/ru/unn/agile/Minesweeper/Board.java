package ru.unn.agile.Minesweeper;

/**
 * Created by aleksei on 01.11.15.
 */
public class Board {
    private static int boardWidth = 64;
    private static int boardHeight = 64;

    private boolean lost = false;

    private Cell[][] cells;

    Board(){
        cells = new Cell[boardHeight][boardWidth];
        for(int positionY = 0; positionY < boardHeight; positionY++){
            cells[positionY] = new Cell[boardWidth];
            for(int positionX = 0; positionX < boardWidth; positionX++){
                cells[positionY][positionX] = new Cell();
            }
        }
    }

    public void setMine(int positionY, int positionX){
        cells[positionY][positionX].setMine();
    }

    public void openCell(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(!lost){
            cell.open();
            lost = cell.isMine();
        }
    }

    public boolean isLost(){
        return  lost;
    }
}
