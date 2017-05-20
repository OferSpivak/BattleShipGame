package javaFx;

import engine.enums.TileState;
import engine.player.Tile;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by Ofer.Spivak on 19/05/2017.
 */
public class Player {
    private int boardSize;
    private int tileSize;

    private String playerId;
    private GridPane hitBoardGrid;
    private GridPane ownBoardGrid;

    public Player(String playerId, int boardSize, int tileSize) {
        this.boardSize = boardSize;
        this.playerId = playerId;
        this.tileSize = tileSize;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void initializePlayersBoards(Tile[][] playerOwnBoard) {
        hitBoardGrid = createBoard();
        ownBoardGrid = createBoard();
        setOwnBoard(playerOwnBoard);
        //setGridPaneClickable(getHitBoardGrid());
    }

    public GridPane getHitBoardGrid() {
        return hitBoardGrid;
    }

    public GridPane getOwnBoardGrid() {
        return ownBoardGrid;
    }

    private GridPane createBoard() {
        GridPane boardGrid = new GridPane();
        for (int i = 0; i < boardSize + 1; i++) {
            ColumnConstraints column = new ColumnConstraints(tileSize);
            boardGrid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < boardSize + 1; i++) {
            RowConstraints row = new RowConstraints(tileSize);
            boardGrid.getRowConstraints().add(row);
        }

        Line line = new Line(0, 0, tileSize, tileSize);
        boardGrid.add(line, 0, 0);

        for (int i = 1; i <= boardSize; i++) {
            //column numbering
            Text columnNumberText = new Text(Integer.toString(i));
            GridPane.setHalignment(columnNumberText, HPos.CENTER);
            GridPane.setValignment(columnNumberText, VPos.CENTER);
            boardGrid.add(columnNumberText, i, 0);
            //row numbering
            Text rowNumberText = new Text(Integer.toString(i));
            GridPane.setHalignment(rowNumberText, HPos.CENTER);
            GridPane.setValignment(rowNumberText, VPos.CENTER);
            boardGrid.add(rowNumberText, 0, i);
        }

        boardGrid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        boardGrid.setId("hitBoard");
        boardGrid.setAlignment(Pos.CENTER);
        boardGrid.setMaxWidth(tileSize * boardSize);
        boardGrid.setMaxHeight(tileSize * boardSize);

        return boardGrid;
    }

    private void setOwnBoard(Tile[][] playerOwnBoard) {
        for (int row = 1; row < boardSize + 1; row++) {
            for (int column = 1; column < boardSize + 1; column++) {
                Tile tile = playerOwnBoard[row - 1][column - 1];
                if (tile.getState() == TileState.FULL) {
                    Pane pane = new Pane();
                    Rectangle rectangle = new Rectangle(tileSize * 0.05, tileSize * 0.05, tileSize * 0.9, tileSize * 0.9);
                    rectangle.setFill(Color.BLUE);
                    pane.getChildren().add(rectangle);
                    ownBoardGrid.add(pane, column, row);
                }
            }
        }
    }
}
