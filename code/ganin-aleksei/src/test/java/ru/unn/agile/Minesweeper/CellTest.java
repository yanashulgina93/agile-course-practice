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
    public  void  set_bomb(){
        cell.setMine();
        assertEquals(true, cell.isMine());
    }

    @Test
    public  void  set_value(){
        char value = 8;
        cell.setValue(value);
        assertEquals(value, cell.getValue());
    }

    @Test
    public void set_issue(){
        cell.setIssue();
        assertEquals(true, cell.isIssue());
    }

    @Test
    public void unset_issue(){
        cell.unsetIssue();
        assertEquals(false, cell.isIssue());
    }

    @Test
    public void set_flag(){
        cell.setFlag();
        assertEquals(true, cell.isFlag());
    }

    @Test
    public void unset_flag(){
        cell.unsetFlag();
        assertEquals(false, cell.isFlag());
    }

    @Test
    public void open_cell(){
        cell.open();
        assertEquals(true, cell.isOpen());
    }

    @Test
    public void when_clear_mine_unset(){
        cell.setMine();
        cell.clear();
        assertEquals(false, cell.isMine());
    }

    @Test
    public void get_cell_position_x(){
        assertEquals(cellPositionX, cell.getPositionX());
    }

    @Test
    public void get_cell_position_y(){
        assertEquals(cellPositionY, cell.getPositionY());
    }
}
