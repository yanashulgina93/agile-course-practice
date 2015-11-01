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
}
