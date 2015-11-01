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

    private void incValues(int positionY, int positionX){
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
    }

    public void setMine(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(!cell.isMine()) {
            cell.setMine();
            incValues(positionY, positionX);
        }
    }

    public void openCell(int positionY, int positionX){
        Cell cell = cells[positionY][positionX];
        if(!lost && !cell.isIssue() && !cell.isFlag()){
            cell.open();
            lost = cell.isMine();
        }
    }

    public boolean isLost(){
        return  lost;
    }

    public void  setIssue(int positionY, int positionX){
        cells[positionY][positionX].setIssue();
    }

    public void setFlag(int positionY, int positionX){
        cells[positionY][positionX].setFlag();
    }

    public int getValue(int positionY, int positionX){
        return cells[positionY][positionX].getValue();
    }
}
