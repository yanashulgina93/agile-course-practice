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
}
