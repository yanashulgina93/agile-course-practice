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
    public void postEndIsEndSet() {
        minesweeper.end(true);
        assertEquals(true, minesweeper.isEnd());
    }

    @Test
    public void postEndNewGameIsEndUnset() {
        minesweeper.end(true);
        minesweeper.newGame();
        assertEquals(false, minesweeper.isEnd());
    }
}
