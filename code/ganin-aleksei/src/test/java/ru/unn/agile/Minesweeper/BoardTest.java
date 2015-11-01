package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    Board board;

    @Before
    public void runBeforeEveryTest() {
        board = new Board(new Minesweeper(), 32, 32);
    }

    @Test
    public void open_mine(){
        board.setMine(0, 0);
        board.openCell(0, 0);
        assertEquals(true, board.isLost());
    }

    /*
    @Test
    public void  when_set_issue_open_mine(){
        board.setMine(0,0);
        board.setIssue(0, 0);
        board.openCell(0, 0);
        assertEquals(false, board.isLost());
    }
    */

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

    @Test
    public void when_neighboring_cells_set_two_mines_cell_set_value_2(){
        board.setMine(0, 0);
        board.setMine(2, 2);
        assertEquals(2, board.getValue(1, 1));
    }

    @Test
    public void when_around_mines_cell_set_value_8(){
        board.setMine(0, 0);
        board.setMine(0, 1);
        board.setMine(0, 2);
        board.setMine(1, 0);
        board.setMine(1, 2);
        board.setMine(2, 0);
        board.setMine(2, 1);
        board.setMine(2, 2);
        assertEquals(8, board.getValue(1, 1));
    }

    @Test
    public void when_clear_mine_unset(){
        board.setMine(0, 0);
        board.clear();
        assertEquals(false, board.isMine(0, 0));
    }

    @Test
    public void when_clear_lost_unset(){
        board.setMine(0, 0);
        board.openCell(0, 0);
        board.clear();
        assertEquals(false, board.isLost());
    }

    @Test
    public void set_random_100_mines(){
        board.setMinesRandom(100);
        assertEquals(100, board.findMinesCount());
    }
}
