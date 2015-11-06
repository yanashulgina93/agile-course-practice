package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CellTest {

    private Cell cell;
    private int cellPositionX = 12;
    private int cellPositionY = 23;


    @Before
    public void runBeforeEveryTest() {
        cell = new Cell(cellPositionY, cellPositionX);
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

    @Test
    public void openCellWithValue0() {
        cell.setValue(0);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue1() {
        cell.setValue(1);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue2() {
        cell.setValue(2);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue3() {
        cell.setValue(3);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue4() {
        cell.setValue(4);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue5() {
        cell.setValue(5);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue6() {
        cell.setValue(6);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue7() {
        cell.setValue(7);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithValue8() {
        cell.setValue(8);
        cell.open();
        assertTrue(cell.isOpen());
    }

    @Test
    public void openCellWithMine() {
        cell.setMine();
        cell.open();
        assertTrue(cell.isOpen());
    }
}
