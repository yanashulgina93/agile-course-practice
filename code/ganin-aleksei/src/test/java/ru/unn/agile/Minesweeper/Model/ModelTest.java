package ru.unn.agile.Minesweeper.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {

    private Model minesweeper;

    @Before
    public void before() {
        minesweeper = new Model();
    }

    @Test
    public void whenEmptyBoard_MineCounter0(){
        minesweeper.boardClear();
        assertEquals(0, minesweeper.getMineCounter());
    }

    @Test
    public void whenSetOnceMineOnEmptyBoard_MineCounter1(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        assertEquals(1, minesweeper.getMineCounter());
    }


    @Test
    public void defaultIsGameEndFalse() {
        assertFalse(minesweeper.isGameEnd());
    }

    @Test
    public void defaultIsLostFalse() {
        assertFalse(minesweeper.isLost());
    }

    @Test
    public void defaultIsCellFlagFalse(){
        assertFalse(minesweeper.isCellFlag(0, 0));
    }

    @Test
    public void defaultIsCellIssueFalse(){
        assertFalse(minesweeper.isCellIssue(0, 0));
    }

    @Test
    public void whenOnceMarkCell_isCellFlagIsTrue(){
        minesweeper.markCell(0, 0);
        assertTrue(minesweeper.isCellFlag(0, 0));
    }

    @Test
    public void whenTwiceMarkCell_isCellIssueIsTrue(){
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertTrue(minesweeper.isCellIssue(0, 0));
    }

    @Test
    public void whenOpenMine_isGameEndIsTrue(){
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertTrue(minesweeper.isGameEnd());
    }

    @Test
    public void whenOpenMine_isLostIsTrue(){
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        assertTrue(minesweeper.isLost());
    }

    @Test
    public void whenOpenAllFreeCells_isGameEndIsTrue(){
        minesweeper.boardClear();
        minesweeper.openCell(0, 0);
        assertTrue(minesweeper.isGameEnd());
    }

    @Test
    public void whenOpenAllFreeCells_isLostIsFalse(){
        minesweeper.boardClear();
        minesweeper.openCell(0, 0);
        assertFalse(minesweeper.isLost());
    }

    @Test
    public void defaultIsCellCloseIsTrue(){
        assertTrue(minesweeper.isCellClose(0, 0));
    }

    @Test
    public void whenOpenCell_isCellCloseIsFalse(){
        minesweeper.openCell(0, 0);
        assertFalse(minesweeper.isCellClose(0, 0));
    }

    @Test
    public void whenSetMine_isCellMineIsTrue(){
        minesweeper.setMine(0, 0);
        assertTrue(minesweeper.isCellMine(0, 0));
    }

    @Test
    public void cellValueOnFreeBoard0(){
        minesweeper.boardClear();
        assertEquals(0, minesweeper.getCellValue(0, 0));
    }

    @Test
    public void whenOpenedFreeCellCloseTo1Mine_CellValueIs1(){
        minesweeper.boardClear();
        minesweeper.setMine(1, 1);
        assertEquals(1, minesweeper.getCellValue(0, 0));
    }

    @Test
    public void whenThreesMarkedCell_isCellIssueIsFalse(){
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        minesweeper.markCell(0, 0);
        assertFalse(minesweeper.isCellIssue(0, 0));
    }

    @Test
    public void whenOnceMarkedCellPostEndGame_isCellFlagIsFalse(){
        minesweeper.setMine(0, 0);
        minesweeper.openCell(0, 0);
        minesweeper.markCell(0, 0);
        assertFalse(minesweeper.isCellFlag(0, 0));
    }

    /*
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
    public void whenOpenedFreeCellCloseTo5Mine_CellIconIsVal5Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(1, 0);
        minesweeper.setMine(1, 2);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val5Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo6Mine_CellIconIsVal6Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(1, 0);
        minesweeper.setMine(1, 2);
        minesweeper.setMine(2, 0);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val6Icon, minesweeper.getCellIcon(1, 1));
    }

    @Test
    public void whenOpenedFreeCellCloseTo7Mine_CellIconIsVal7Icon(){
        minesweeper.boardClear();
        minesweeper.setMine(0, 0);
        minesweeper.setMine(0, 1);
        minesweeper.setMine(0, 2);
        minesweeper.setMine(1, 0);
        minesweeper.setMine(1, 2);
        minesweeper.setMine(2, 0);
        minesweeper.setMine(2, 1);
        minesweeper.openCell(1, 1);
        assertEquals(minesweeper.val7Icon, minesweeper.getCellIcon(1, 1));
    }*/

    @Test
    public void getBoardHeight(){
        assertEquals(minesweeper.BOARD_HEIGHT, minesweeper.getBoardHeight());
    }

    @Test
    public void getBoardWidth(){
        Model model = new Model();
        assertEquals(minesweeper.BOARD_WIDTH, minesweeper.getBoardWidth());
    }

}
