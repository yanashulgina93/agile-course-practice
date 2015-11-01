package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CellTest {

    Cell cell;

    @Before
    public void runBeforeEveryTest() {
        cell = new Cell();
    }

    @Test
    public  void  set_bomb_to_cell(){
        cell.setBomb();
        assertEquals(true, cell.isBomb());
    }

    @Test
    public  void  set_value_to_cell(){
        char value = 8;
        cell.setValue(value);
        assertEquals(value, cell.getValue());
    }
}
