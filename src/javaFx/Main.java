package javaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //setting the primary stage.
        primaryStage.setTitle("Battle Ship gameInterface.GameInterface");

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("/javaFx/resources/root.fxml");
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load();

        RootController rootController = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        JavaFxGameEngine gameEngine = new JavaFxGameEngine(primaryStage, scene, rootController);
        gameEngine.initializeGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
