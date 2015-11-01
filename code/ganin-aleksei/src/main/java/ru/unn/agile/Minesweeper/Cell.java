package ru.unn.agile.Minesweeper;

/**
 * Created by aleksei on 01.11.15.
 */
public class Cell {
    boolean bomb = false;
    char value = 0;

    public void setBomb(){
        bomb = true;
    }
    public boolean isBomb(){
        return  bomb;
    }
}
