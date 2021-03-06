package javaFx;

import engine.enums.HitBoardType;
import exceptions.HittingTargetOutsideTheBoardException;
import exceptions.InitializationFailException;
import exceptions.TileAlreadyBombedException;
import gameInterface.GameInterface;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.util.Objects;

/**
 * Created by Ofer.Spivak on 19/05/2017.
 */
public class JavaFxGameEngine {
    private GameInterface gameInterface;
    private Stage stage;
    private Scene scene;
    private RootController controller;
    private boolean preventPlaying = true;

    private int boardSize;
    private int tileSize;
    private HBox rootMainArea;
    private Player currentPlayer;
    private Player opponentPlayer;

    public JavaFxGameEngine(Stage stage, Scene scene, RootController controller) {
        gameInterface = new GameInterface();
        this.stage = stage;
        this.scene = scene;
        this.controller = controller;
    }

    public void initializeGame() {
        Button loadGameSettingsBtn = (Button) scene.lookup("#loadGameSettings");
        loadGameSettingsBtn.setOnAction((ActionEvent event) -> {
            File file = OpenFileChooser();
            try {
                gameInterface.initializeGame(file);
                boardSize = gameInterface.getBoardSize();
                controller.clearErrorText();
                loadGameSettingsBtn.setDisable(true);
                initializedPlayers();
                initializeRootMainArea(scene);
                preventPlaying = false;
            } catch (InitializationFailException initializationFailException) {
                controller.setErrorText(initializationFailException.toString());
            }
        });
    }

    private File OpenFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }

    private void initializedPlayers() {
        Player player1;
        Player player2;

        tileSize = boardSize < 10 ? 35 : boardSize < 15 ? 30 : boardSize < 18 ? 25 : 20;
        player1 = new Player(new java.util.Date().toString(), boardSize, tileSize);
        player2 = new Player(new java.util.Date().toString() + "2", boardSize, tileSize);

        gameInterface.setCurrentPlayerId(player1.getPlayerId());
        gameInterface.setOpponentPlayerId(player2.getPlayerId());

        player1.initializePlayersBoards(gameInterface.getCurrentPlayerOwnBoard());
        player2.initializePlayersBoards(gameInterface.getOpponentPlayerOwnBoard());

        setGridPaneClickable(player1.getHitBoardGrid());
        setGridPaneClickable(player2.getHitBoardGrid());

        currentPlayer = player1;
        opponentPlayer = player2;
    }

    private void initializeRootMainArea(Scene scene) {
        rootMainArea = (HBox) scene.lookup("#rootMainArea");
        rootMainArea.setSpacing(30);
        // adding Player 1 boards as he is starting
        rootMainArea.getChildren().add(currentPlayer.getHitBoardGrid());
        rootMainArea.getChildren().add(currentPlayer.getOwnBoardGrid());
    }

    private void setGridPaneClickable(GridPane boardGrid) {
        for (int row = 1; row < boardSize + 1; row++) {
            for (int column = 1; column < boardSize + 1; column++) {
                Pane pane = new Pane();
                int finalRow = row;
                int finalColumn = column;
                pane.setOnMouseClicked(e -> {
                    if (preventPlaying) {
                        return;
                    }
                    try {
                        Node node = tileClicked(finalRow, finalColumn);
                        pane.getChildren().add(node);
                        updateStatistics();
                        if (isSwitchPlayersNeeded()) {
                            delayedSwitchPlayers();
                        }
                    } catch (TileAlreadyBombedException e1) {
                        return;
                    }
                });
                boardGrid.add(pane, column, row);
            }
        }
    }

    private Node tileClicked(int row, int column) throws TileAlreadyBombedException {
        HitBoardType hit = bombPoint(row - 1, column - 1);
        Group group = new Group();
        Node node = new Pane();
        switch (hit) {
            case HIT: {
                node = getXDrawing();
                break;
            }
            case MISS: {
                Circle circle = new Circle(tileSize / 2, tileSize / 2, tileSize / 10);
                circle.setFill(Color.RED);
                node = circle;
                break;
            }
        }
        group.getChildren().add(node);
        return group;
    }

    private void updateStatistics() {
        int score = gameInterface.getCurrentPlayerScore();
        int hitCount = gameInterface.getCurrentPlayerHitCount();
        int missCount = gameInterface.getCurrentPlayerMissCount();
        if (gameInterface.getOpponentPlayerId().equals(currentPlayer.getPlayerId())){ //due to game engine switching immediately  between the players and the UI is delayed.
            score = gameInterface.getOpponentPlayerScore();
            hitCount = gameInterface.getOpponentPlayerHitCount();
            missCount = gameInterface.getOpponentPlayerMissCount();
        }
        controller.setScore(Integer.toString(score));
        controller.setHitCount(Integer.toString(hitCount));
        controller.setMissCount(Integer.toString(missCount));
    }

    private Node getXDrawing() {
        Group xGroup = new Group();
        Line line = new Line(5, 5, tileSize - 5, tileSize - 5);
        Line crossLine = new Line(5, tileSize - 5, tileSize - 5, 5);
        line.setStroke(Color.RED);
        crossLine.setStroke(Color.RED);
        line.setStrokeWidth(5);
        crossLine.setStrokeWidth(5);
        line.setStrokeLineJoin(StrokeLineJoin.ROUND);
        crossLine.setStrokeLineJoin(StrokeLineJoin.ROUND);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        crossLine.setStrokeLineCap(StrokeLineCap.ROUND);
        xGroup.getChildren().add(line);
        xGroup.getChildren().add(crossLine);
        return xGroup;
    }

    private HitBoardType bombPoint(int row, int column) throws TileAlreadyBombedException {
        HitBoardType hit = null;
        try {
            hit = gameInterface.bombPoint(row, column);
        } catch (HittingTargetOutsideTheBoardException hittingTargetOutsideTheBoardException) {
            controller.setErrorText(hittingTargetOutsideTheBoardException.toString());
        }
        return hit;
    }

    private boolean isSwitchPlayersNeeded() {
        return !(currentPlayer.getPlayerId().equals(gameInterface.getCurrentPlayerId()));
    }

    private void delayedSwitchPlayers() {
        preventPlaying = true;
        controller.setInfoText("Switching players !!!");
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                switchPlayers();
                controller.clearInfoText();
                preventPlaying = false;
            }
        });
        new Thread(sleeper).start();
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
        rootMainArea.getChildren().removeAll(opponentPlayer.getHitBoardGrid(), opponentPlayer.getOwnBoardGrid());
        rootMainArea.getChildren().addAll(currentPlayer.getHitBoardGrid(), currentPlayer.getOwnBoardGrid());
        updateStatistics();
    }
}
