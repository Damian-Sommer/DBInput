package ch.aelgict.dbinput.dbinput;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnViewController {
    private Starter starter;
    public void initData(Starter starter){
        this.starter = starter;
    }
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button btnNext;

    @FXML
    void handleButtonNext(ActionEvent event) {
        try {
            starter.start(starter.getStage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'columnView.fxml'.";

    }
}
