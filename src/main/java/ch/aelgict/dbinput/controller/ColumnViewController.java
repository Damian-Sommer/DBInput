package ch.aelgict.dbinput.controller;

import ch.aelgict.dbinput.logic.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColumnViewController {
    private Starter starter;
    private DatabaseConnection databaseConnection;
    public void initData(Starter starter, DatabaseConnection databaseConnection){
        this.starter = starter;
        this.databaseConnection = databaseConnection;
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
