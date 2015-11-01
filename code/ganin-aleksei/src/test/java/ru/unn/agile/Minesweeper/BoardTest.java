package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    Board board;

    @Before
    public void runBeforeEveryTest() {
        board = new Board();
    }

    @Test
    public void open_mine(){
        board.setMine(0, 0);
        board.openCell(0, 0);
        assertEquals(true, board.isLost());
    }

    @Test
    public void  when_set_issue_open_mine(){
        board.setMine(0,0);
        board.setIssue(0, 0);
        board.openCell(0, 0);
        assertEquals(false, board.isLost());
    }

    @Test
    public void  when_set_flag_open_mine(){
        board.setMine(0,0);
        board.setFlag(0, 0);
        board.openCell(0, 0);
        assertEquals(false, board.isLost());
    }

    @Test
    public void when_set_one_mine_in_neighboring_cell_set_value_1(){
        board.setMine(0, 0);
        assertEquals(1, board.getValue(1, 1));
    }
}
