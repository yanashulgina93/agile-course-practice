package ru.unn.agile.Minesweeper;

public class Minesweeper {


    private final  Board board;

    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_WIDTH = 700;

    private static final int BOARD_HEIGHT = 32;
    private static final int BOARD_WIDTH = 32;


    private static final int SMILE_POSITION_X = 640;
    private static final int SMILE_POSITION_Y = 0;
    private static final int SMILE_HEIGHT = 50;
    private static final int SMILE_WIDTH = 50;

    private static final int MINE_COUNTER_POSITION_X = 640;
    private static final int MINE_COUNTER_POSITION_Y = 70;
    private static final int MINE_COUNTER_HEIGHT = 20;
    private static final int MINE_COUNTER_WIDTH = 50;

    private static final int DEFAULT_MINES = 100;

    public Minesweeper() {

        board = new Board(BOARD_HEIGHT, BOARD_WIDTH);
        newGame();
    }

    public static void main(final String[] args) {
        new Minesweeper();
    }

    public final void newGame() {
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public boolean isEnd() {
        return board.isEnd();
    }
}
