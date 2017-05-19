package javaFx;

import exceptions.InitializationFailException;
import gameInterface.GameInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Ofer.Spivak on 06/05/2017.
 */
public class RootController {
    @FXML
    public HBox rootMainArea;
    @FXML
    public Text errorTextArea;
    public Button loadGameSettings;

    private Stage stage;
    private GameInterface gameInterface;

    public void setStage (Stage stage){
        this.stage = stage;
    }

    public void setGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }

    public void setErrorText (String text){
        errorTextArea.setText(text);
    }
}
