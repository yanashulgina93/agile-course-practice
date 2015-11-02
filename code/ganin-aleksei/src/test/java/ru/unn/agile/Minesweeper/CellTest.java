package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CellTest {

    private Cell cell;
    private int cellPositionX = 12;
    private int cellPositionY = 23;


    @Before
    public void runBeforeEveryTest() {
        cell = new Cell(new Board(new Minesweeper(), 32, 32), cellPositionY, cellPositionX);
    }

    @Test
    public  void  setMine() {
        cell.setMine();
        assertEquals(true, cell.isMine());
    }

    @Test
    public  void  setValue() {
        int value = 8;
        cell.setValue(value);
        assertEquals(value, cell.getValue());
    }

    @Test
    public void setIssue() {
        cell.setIssue();
        assertEquals(true, cell.isIssue());
    }

    @Test
    public void unsetIssue() {
        cell.unsetIssue();
        assertEquals(false, cell.isIssue());
    }

    @Test
    public void setFlag() {
        cell.setFlag();
        assertEquals(true, cell.isFlag());
    }

    @Test
    public void unsetFlag() {
        cell.unsetFlag();
        assertEquals(false, cell.isFlag());
    }

    @Test
    public void openCell() {
        cell.open();
        assertEquals(true, cell.isOpen());
    }

    @Test
    public void whenClearMineUnset() {
        cell.setMine();
        cell.clear();
        assertEquals(false, cell.isMine());
    }

    @Test
    public void getCellPositionX() {
        assertEquals(cellPositionX, cell.getPositionX());
    }

    @Test
    public void getCellPositionY() {
        assertEquals(cellPositionY, cell.getPositionY());
    }
}
