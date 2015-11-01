package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperTest {

    private Minesweeper minesweeper;
    @Before
    public void runBeforeEveryTest() {
        minesweeper = new Minesweeper();
    }

    @Test
    public void post_end_is_end_set(){
        minesweeper.end(true);
        assertEquals(true, minesweeper.isEnd());
    }

    @Test
    public void post_end_new_game_is_end_unset(){
        minesweeper.end(true);
        minesweeper.newGame();
        assertEquals(false, minesweeper.isEnd());
    }
}
