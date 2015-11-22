package ru.unn.agile.Minesweeper.View;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.scene.image.*;
import javafx.scene.Group;
import javafx.scene.control.Label;

import ru.unn.agile.Minesweeper.viewmodel.ViewModel;

public class View extends Application {

    private final ViewModel minesweeperViewModel = new ViewModel();

    public static final int CELL_SIZE = 20;
    public static final int SIDEBAR_SIZE = 60;

    private static final int SMILE_POSITION_Y = 0;
    private static final int MINE_COUNTER_POSITION_Y = 70;

    private final BoardView board = new BoardView(
            minesweeperViewModel.getBoardWidth(),
            minesweeperViewModel.getBoardHeight()
    );

    private final Label smile;
    private final Label mineCounter;


    public class BoardView extends Group {

        public class CellView extends Label {
            public CellView(final int x, final int y){
                setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            minesweeperViewModel.openCell(x, y);
                        }

                        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            minesweeperViewModel.markCell(x, y);
                        }
                        binding();
                    }
                });
            }
        }

        private final CellView[][] cells;


        public BoardView(final int boardWidth, final int boardHeight){
            cells = new CellView[boardHeight][boardWidth];
            for (int y = 0; y < boardHeight; y++) {
                cells[y] = new CellView[boardWidth];
                for (int x = 0; x < boardWidth; x++) {
                    CellView cell = new CellView(x, y);
                    cell.setLayoutX(x * getCellSize());
                    cell.setLayoutY(y * getCellSize());
                    getChildren().add(cell);
                    cells[y][x] = cell;
                }
            }
        }
    }


    public View(){
        smile = new Label();
        mineCounter = new Label();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override public void start(Stage primaryStage) {

        primaryStage.setTitle("Сапер");

        Group root = new Group();
        Scene scene = new Scene(
                root,
                getWindowWidth(),
                getWindowHeight()
        );

        smile.setLayoutX(getSmilePositionX());
        smile.setLayoutY(getSmilePositionY());
        smile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                minesweeperViewModel.newGame();
                binding();
            }
        });
        root.getChildren().add(smile);

        mineCounter.setLayoutX(getMineCounterPositionX());
        mineCounter.setLayoutY(getMineCounterPositionY());
        root.getChildren().add(mineCounter);

        root.getChildren().add(board);

        primaryStage.setScene(scene);
        primaryStage.show();

        binding();
    }

    private void binding(){

        for(int y = 0; y < minesweeperViewModel.getBoardHeight(); y++){
            for(int x = 0; x < minesweeperViewModel.getBoardWidth(); x++){
                board.cells[y][x].setGraphic(new ImageView(minesweeperViewModel.getCellIcon(x, y)));
            }
        }

        mineCounter.setText(minesweeperViewModel.getMineCounter());

        smile.setGraphic(new ImageView(minesweeperViewModel.getSmileIcon()));
    }

    public int getCellSize(){
        return CELL_SIZE;
    }

    public int getWindowWidth(){
        return  minesweeperViewModel.getBoardWidth() * CELL_SIZE + SIDEBAR_SIZE;
    }

    public int getWindowHeight(){
        return minesweeperViewModel.getBoardHeight() * CELL_SIZE;
    }

    public int getSmilePositionX(){
        return minesweeperViewModel.getBoardWidth() * CELL_SIZE;
    }

    public int getSmilePositionY(){
        return SMILE_POSITION_Y;
    }

    public int getMineCounterPositionX(){
        return minesweeperViewModel.getBoardWidth() * CELL_SIZE;
    }

    public int getMineCounterPositionY(){
        return MINE_COUNTER_POSITION_Y;
    }
}