package javaFx;

import exceptions.InitializationFailException;
import gameInterface.GameInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Main extends Application {
    private GameInterface gameInterface;

    private int boardSize = 10;
    private int tileSize = 30;
    private HBox rootMainArea;
    private int currentPlayer = 1;

    private GridPane player1HitBoardGrid;
    private GridPane player1OwnBoardGrid;
    private GridPane player2HitBoardGrid;
    private GridPane player2OwnBoardGrid;


    @Override
    public void start(Stage primaryStage) throws Exception {
        gameInterface = new GameInterface();

        //setting the primary stage.
        primaryStage.setTitle("Battle Ship gameInterface.GameInterface");

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("/javaFx/resources/root.fxml");
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load();

        RootController rootController = fxmlLoader.getController();
        rootController.setStage(primaryStage);
        rootController.setGameInterface(gameInterface);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        initializeGame(primaryStage, scene, rootController);
    }

    private void initializeGame(Stage stage, Scene scene, RootController controller) {
        Button loadGameSettingsBtn = (Button) scene.lookup("#loadGameSettings");
        loadGameSettingsBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(stage);
            try {
                gameInterface.initializeGame(file);
                controller.setErrorText("");
                loadGameSettingsBtn.setDisable(true);
                letsPlay(scene);
            } catch (InitializationFailException initializationFailException) {
                controller.setErrorText(initializationFailException.toString());
            }
        });
    }

    private Stage initializeGetFilePath() throws java.io.IOException {
        Parent readFileStage = FXMLLoader.load(getClass().getResource("resources/readFilePath.fxml"));
        Stage secondaryStage = new Stage();
        Scene filePathScene = new Scene(readFileStage);

        secondaryStage.setTitle("XML file path");
        secondaryStage.setResizable(false);
        secondaryStage.setScene(filePathScene);

        setOnSubmitReadFilePath(filePathScene);

        return secondaryStage;
    }

    private void setOnSubmitReadFilePath(Scene scene) {
        Button submitBtn = (Button) scene.lookup("#xmlFileSubmitBtn");
        TextField filePathString = (TextField) scene.lookup("#xmlFilePathText");
        Text filePathErrorArea = (Text) scene.lookup("#xmlFileErrorArea");
        submitBtn.setOnAction(event -> initializeDal(filePathString.getText(), filePathErrorArea));
    }

    private void initializeDal(String path, Text errorTextField) {
        if (!path.isEmpty()) {
            // todo - add Dal creation
            //fileValid = true;
            //readXMLStage.close();
        } else {
            errorTextField.setText("Please insert the path in the right format - c:\\FileDirectory\\File.xml");
            //fileValid = false;
        }
    }

    private void letsPlay(Scene scene) {
        initializePlayersBoards(scene);
        initializeRootMainArea(scene);
    }

    private void switchPlayers() {
        if (currentPlayer == 1) {
            rootMainArea.getChildren().removeAll(player1HitBoardGrid, player1OwnBoardGrid);
            rootMainArea.getChildren().addAll(player2HitBoardGrid, player2OwnBoardGrid);
            currentPlayer = 2;
        } else {
            rootMainArea.getChildren().removeAll(player2HitBoardGrid, player2OwnBoardGrid);
            rootMainArea.getChildren().addAll(player1HitBoardGrid, player1OwnBoardGrid);
            currentPlayer = 1;
        }
    }

    private void initializeRootMainArea(Scene scene) {
        rootMainArea = (HBox) scene.lookup("#rootMainArea");
        rootMainArea.setSpacing(30);
        // adding player 1 boards as he is starting
        rootMainArea.getChildren().add(player1HitBoardGrid);
        rootMainArea.getChildren().add(player1OwnBoardGrid);
    }

    private void initializePlayersBoards(Scene scene) {
        player1HitBoardGrid = drawBoard();
        player1OwnBoardGrid = drawBoard();
        setGridPaneClickable(player1HitBoardGrid);

        player2HitBoardGrid = drawBoard();
        player2OwnBoardGrid = drawBoard();
        setGridPaneClickable(player2HitBoardGrid);
    }

    private Node tileClicked(int x, int y) {
        Group group = new Group();
        Circle circle = new Circle(tileSize / 2, tileSize / 2, tileSize / 4);
        circle.setFill(Color.RED);
        group.getChildren().add(circle);
        return group;
    }

    private GridPane drawBoard() {
        GridPane boardGrid = new GridPane();
        for (int i = 0; i < boardSize + 1; i++) {
            ColumnConstraints column = new ColumnConstraints(tileSize);
            boardGrid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < boardSize + 1; i++) {
            RowConstraints row = new RowConstraints(tileSize);
            boardGrid.getRowConstraints().add(row);
        }

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

    private void setGridPaneClickable(GridPane boardGrid) {
        for (int i = 1; i < boardSize + 1; i++) {
            for (int j = 1; j < boardSize + 1; j++) {
                Pane pane = new Pane();
                int finalI1 = i;
                int finalJ1 = j;
                pane.setOnMouseClicked(e -> {
                    pane.getChildren().add(tileClicked(finalI1, finalJ1));
                });
                boardGrid.add(pane, i, j);
            }
        }
    }

    private void onSubmitFilePath() {

    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        this.tileSize = boardSize < 10 ? 35 : boardSize < 15 ? 30 : boardSize < 18 ? 25 : 20;
    }

    public static void main(String[] args) {

        launch(args);
    }
}
