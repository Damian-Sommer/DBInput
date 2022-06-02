package ch.aelgict.dbinput.controller;

import ch.aelgict.dbinput.logic.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController {

    private Starter starter;
    private DatabaseConnection databaseConnection;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML
    private TextField txtHost;

    @FXML // fx:id="txtDBName"
    private TextField txtDBName; // Value injected by FXMLLoader

    @FXML // fx:id="txtPort"
    private TextField txtPort; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsername"
    private TextField txtUsername; // Value injected by FXMLLoader


    @FXML
    private PasswordField txtPasswort;

    @FXML // fx:id="txtTable"
    private TextField txtTable; // Value injected by FXMLLoader

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader

    @FXML // fx:id="btnChancel"
    private Button btnChancel; // Value injected by FXMLLoader

    @FXML
    void handleButtonCancel(ActionEvent event) {

    }

    public void initData(Starter starter, DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
        this.starter = starter;
    }

    @FXML
    void handleButtonNext(ActionEvent event) {

        System.out.println(this.databaseConnection.createConnection(txtHost.getText(), Integer.valueOf(txtPort.getText()),txtUsername.getText(), txtPasswort.getText(), txtDBName.getText()));
        System.out.println(this.databaseConnection.isConnectionStable());
        starter.showTableView(starter.getStage(),this.databaseConnection);
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtDBName != null : "fx:id=\"txtDBName\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtPort != null : "fx:id=\"txtPort\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtPasswort != null : "fx:id=\"txtPasswort\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtTable != null : "fx:id=\"txtTable\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnChancel != null : "fx:id=\"btnChancel\" was not injected: check your FXML file 'hello-view.fxml'.";

    }
}
