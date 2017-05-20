package javaFx;

import exceptions.InitializationFailException;
import gameInterface.GameInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    public Text bottomTextArea;
    public Button loadGameSettings;
    public TextField scoreField;
    public TextField missCount;
    public TextField hitCount;

    public TextField getScoreField() {
        return scoreField;
    }

    public void setErrorText (String text){
        bottomTextArea.setFill(Color.RED);
        bottomTextArea.setText(text);
    }

    public void setInfoText (String text){
        bottomTextArea.setFill(Color.BLUEVIOLET);
        bottomTextArea.setText(text);
    }

    public void clearInfoText(){
        bottomTextArea.setText("");
    }

    public void clearErrorText(){
        bottomTextArea.setText("");
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
