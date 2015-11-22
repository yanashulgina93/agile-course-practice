package ru.unn.agile.Minesweeper.viewmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTest extends ViewModel {

    private ViewModel minesweeper;
    @Before
    public void before() {
        minesweeper = new ViewModel();
    }

    @Test
    public void defaultSmileIconIsSmileIcon() {
        assertEquals(minesweeper.getSmileIcon(), minesweeper.getSmileIcon());
    }

    @Test
    public void whenWinSmileIconIsWinnerIcon() {
        minesweeper.boardClear();
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.getWinnerIcon(), minesweeper.getSmileIcon());
    }

    @Test
    public void whenLostSmileIconIsDeadIcon() {
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.getDeadIcon(), minesweeper.getSmileIcon());
    }

    @Test
    public void defaultCellIconIsCloseIcon() {
        assertEquals(minesweeper.getCloseIcon(), minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenOnceMarkCellCellIconIsFlagIcon() {
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.getFlagIcon(), minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenTwiceMarkedCellCellIconIsIssueIcon() {
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.getIssueIcon(), minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenThreesMarkedCellCellIconIsCloseIcon() {
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.getCloseIcon(), minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenOpenedMineCellIconIsMineIcon() {
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.getMineIcon(), minesweeper.getCellIcon(0, 0));
    }

    @Test
    public void whenOpenedFreeCellAnEmptyBoardCellIconIsVal0Icon() {
        minesweeper.boardClear();
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal0Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo1MineCellIconIsVal1Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal1Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo2MineCellIconIsVal2Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal2Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo3MineCellIconIsVal3Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal3Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo4MineCellIconIsVal4Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(1, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal4Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo5MineCellIconIsVal5Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(1, 2);
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(1, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal5Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo6MineCellIconIsVal6Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 2);
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(1, 0);
        minesweeper.setMine(1, 2);
        minesweeper.setMine(2, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal6Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo7MineCellIconIsVal7Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(1, 0);
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(1, 2);
        minesweeper.setMine(2, 0);
        minesweeper.setMine(2, 1);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal7Icon(), minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo8MineCellIconIsVal1Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 0);
        minesweeper.setMine(1, 0);
        minesweeper.setMine(1, 2);
        minesweeper.setMine(2, 0);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(2, 2);
        minesweeper.setMine(2, 1);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.getVal8Icon(), minesweeper.getCellIcon(1, 1));
    }
    @Test
    public void whenEmptyBoardMineCounterString0() {
        minesweeper.boardClear();
        assertEquals("0", minesweeper.getMineCounter());
    }

    @Test
    public void whenOneMineOnBoardMineCounterString1() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        assertEquals("1", minesweeper.getMineCounter());
    }

    @Test
    public void whenNewGameSmileIconIsSmileIcon() {
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        minesweeper.newGame();
        assertEquals(minesweeper.getSmileIco(), minesweeper.getSmileIcon());
    }
}
