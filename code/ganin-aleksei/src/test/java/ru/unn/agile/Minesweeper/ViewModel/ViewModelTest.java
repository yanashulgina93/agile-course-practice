package ru.unn.agile.Minesweeper.ViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.Minesweeper.Model.Model;

public class ViewModelTest {

    private ViewModel minesweeper;
    @Before
    public void before() {
        minesweeper = new ViewModel();
    }

    @Test
    public void defaultSmileIconIsSmileIcon() {
        assertEquals(minesweeper.smileIcon, minesweeper.getSmileIcon());
    }

    @Test
    public void whenWin_SmileIconIsWinnerIcon(){
        minesweeper.boardClear();
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.winnerIcon, minesweeper.getSmileIcon());
    }

    @Test
    public void whenLost_SmileIconIsDeadIcon(){
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.deadIcon, minesweeper.getSmileIcon());
    }

    @Test
    public void defaultCellIconIsCloseIcon(){
        assertEquals(minesweeper.closeIcon, minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenOnceMarkCell_CellIconIsFlagIcon(){
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.flagIcon, minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenTwiceMarkedCell_CellIconIsIssueIcon(){
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.issueIcon, minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenThreesMarkedCell_CellIconIsCloseIcon(){
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.closeIcon, minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenOpenedMine_CellIconIsMineIcon(){
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.mineIcon, minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenOpenedFreeCellAnEmptyBoard_CellIconIsVal0Icon(){
        minesweeper.boardClear();
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val0Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo1Mine_CellIconIsVal1Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val1Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo2Mine_CellIconIsVal2Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val2Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo3Mine_CellIconIsVal3Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val3Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo4Mine_CellIconIsVal4Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(1, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val4Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenEmptyBoard_MineCounterString0(){
        minesweeper.boardClear();
        assertEquals("0", minesweeper.getMineCounter());
    }

    @Test
    public void whenOneMineOnBoard_MineCounterString1(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        assertEquals("1", minesweeper.getMineCounter());
    }

    @Test
    public void whenNewGame_SmileIconIsSmileIcon(){
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        minesweeper.newGame();
        assertEquals(minesweeper.smileIcon, minesweeper.getSmileIcon());
    }

    @Test
    public void getBoardHeightTransmitsValueOfModel(){
        Model model = new Model();
        assertEquals(model.getBoardHeight(), minesweeper.getBoardHeight());
    }

    @Test
    public void getBoardWidthTransmitsValueOfModel(){
        Model model = new Model();
        assertEquals(model.getBoardWidth(), minesweeper.getBoardWidth());
    }
}
