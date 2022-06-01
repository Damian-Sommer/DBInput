package ch.aelgict.dbinput.dbinput;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TableViewController {
    private Starter starter;
    public void initData(Starter starter){
        this.starter = starter;
    }
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader

    @FXML
    void handleButtonNext(ActionEvent event) {
        try {
            starter.showColumnView(starter.getStage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'tableView.fxml'.";

    }
}
