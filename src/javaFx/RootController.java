package javaFx;

import exceptions.InitializationFailException;
import gameInterface.GameInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    public TextField scoreField;
    public TextField missCount;
    public TextField hitCount;

    public TextField getScoreField() {
        return scoreField;
    }

    public void setErrorText (String text){
        errorTextArea.setText(text);
    }

    public void setScore(String text) {
        scoreField.setText(text);
    }

    public void setMissCount(String text) {
        missCount.setText(text);
    }

    public void setHitCount(String text) {
        hitCount.setText(text);
    }
}
