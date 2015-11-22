package ru.unn.agile.Minesweeper.ViewModel;

import ru.unn.agile.Minesweeper.Model.Model;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModel {

    private final Model minesweeperModel = new Model();
    private final SimpleStringProperty mineCounter = new SimpleStringProperty();

    public static Image flagIcon = new Image("file:images/flag_20x20.png");
    public static Image closeIcon = new Image("file:images/close_20x20.png");
    public static Image issueIcon = new Image("file:images/issue_20x20.png");
    public static Image mineIcon = new Image("file:images/mine_20x20.png");

    public static Image val0Icon = new Image("file:images/val_0_20x20.png");
    public static Image val1Icon = new Image("file:images/val_1_20x20.png");
    public static Image val2Icon = new Image("file:images/val_2_20x20.png");
    public static Image val3Icon = new Image("file:images/val_3_20x20.png");
    public static Image val4Icon = new Image("file:images/val_4_20x20.png");
    public static Image val5Icon = new Image("file:images/val_5_20x20.png");
    public static Image val6Icon = new Image("file:images/val_6_20x20.png");
    public static Image val7Icon = new Image("file:images/val_7_20x20.png");
    public static Image val8Icon = new Image("file:images/val_8_20x20.png");

    public static Image smileIcon = new Image("file:images/smile_50x50.png");
    public static Image deadIcon = new Image("file:images/dead_50x50.png");
    public static Image winnerIcon = new Image("file:images/winner_50x50.png");

    private static final int CELL_VALUE_0 = 0;
    private static final int CELL_VALUE_1 = 1;
    private static final int CELL_VALUE_2 = 2;
    private static final int CELL_VALUE_3 = 3;
    private static final int CELL_VALUE_4 = 4;
    private static final int CELL_VALUE_5 = 5;
    private static final int CELL_VALUE_6 = 6;
    private static final int CELL_VALUE_7 = 7;
    private static final int CELL_VALUE_8 = 8;


    public ViewModel(){}

    public void newGame(){
        minesweeperModel.newGame();
    }

    public void openCell(int x, int y){
        minesweeperModel.openCell(x, y);
    }

    public void markCell(int x, int y){
        minesweeperModel.markCell(x, y);
    }

    public Image getCellIcon(int x, int y){
        if(minesweeperModel.isCellClose(x, y)){
            if(minesweeperModel.isCellFlag(x, y)){
                return flagIcon;
            } else if (minesweeperModel.isCellIssue(x, y)){
                return issueIcon;
            } else {
                return closeIcon;
            }
        } else if(minesweeperModel.isCellMine(x, y)){
            return mineIcon;
        } else {
            int value = minesweeperModel.getCellValue(x, y);
            if (value == CELL_VALUE_0) {
                return val0Icon;
            } else if (value == CELL_VALUE_1) {
                return val1Icon;
            } else if (value == CELL_VALUE_2) {
                return val2Icon;
            } else if (value == CELL_VALUE_3) {
                return val3Icon;
            } else if (value == CELL_VALUE_4) {
                return val4Icon;
            } else if (value == CELL_VALUE_5) {
                return val5Icon;
            } else if (value == CELL_VALUE_6) {
                return val6Icon;
            } else if (value == CELL_VALUE_7) {
                return val7Icon;
            } else {
                return val8Icon;
            }
        }
    }

    public Image getSmileIcon(){
        if(minesweeperModel.isGameEnd()){
            if(minesweeperModel.isLost()){
                return deadIcon;
            } else {
                return winnerIcon;
            }
        }
        return smileIcon;
    }

    public void boardClear(){
        minesweeperModel.boardClear();
    }

    public void setMine(int x, int y){
        minesweeperModel.setMine(x, y);
    }

    public String getMineCounter(){
        return Integer.toString(minesweeperModel.getMineCounter());
    }

    public int getBoardHeight(){
        return minesweeperModel.getBoardHeight();
    }

    public int getBoardWidth(){
        return minesweeperModel.getBoardWidth();
    }
}