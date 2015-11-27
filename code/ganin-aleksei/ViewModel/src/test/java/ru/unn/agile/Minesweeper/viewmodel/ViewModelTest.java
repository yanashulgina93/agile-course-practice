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
    public void emptyTest() {
        assertTrue(true);
    }

    @Test
    public void defaultSmileIconIsSmileIcon() {
        assertEquals(minesweeper.getSmileTex(), minesweeper.getSmileText());
    }

    @Test
    public void whenWinSmileIconIsWinnerIcon() {
        minesweeper.boardClear();
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.getWinnerText(), minesweeper.getSmileText());
    }

    @Test
    public void whenLostSmileIconIsDeadIcon() {
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.getDeadText(), minesweeper.getSmileText());
    }

    @Test
    public void defaultCellIconIsCloseIcon() {
        assertEquals(minesweeper.getCloseText(), minesweeper.getCellText(0, 0));
    }

    @Test
    public void whenOnceMarkCellCellIconIsFlagIcon() {
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.getFlagText(), minesweeper.getCellText(0, 0));
    }

    @Test
    public void whenTwiceMarkedCellCellIconIsIssueIcon() {
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.getIssueText(), minesweeper.getCellText(0, 0));
    }

    @Test
    public void whenThreesMarkedCellCellIconIsCloseIcon() {
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertEquals(minesweeper.getCloseText(), minesweeper.getCellText(0, 0));
    }

    @Test
    public void whenOpenedMineCellIconIsMineIcon() {
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertEquals(minesweeper.getMineText(), minesweeper.getCellText(0, 0));
    }

    @Test
    public void whenOpenedFreeCellCloseTo1MineCellIconIsVal1Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.openCell(1, 1);
        assertEquals("1", minesweeper.getCellText(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo2MineCellIconIsVal2Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.openCell(1, 1);
        assertEquals("2", minesweeper.getCellText(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo3MineCellIconIsVal3Icon() {
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.openCell(1, 1);
        assertEquals("3", minesweeper.getCellText(1, 1));
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
        assertEquals("8", minesweeper.getCellText(1, 1));
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
        assertEquals(minesweeper.getSmileTex(), minesweeper.getSmileText());
    }

    @Test
    public void getBoardHeightMastBeGreaterZero() {
        assertTrue(minesweeper.getBoardHeight() > 0);
    }

    @Test
    public void getBoardWidthMastBeGreaterZero() {
        assertTrue(minesweeper.getBoardWidth() > 0);
    }
}
