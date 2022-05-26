/**
 * Sample Skeleton for 'hello-view.fxml' Controller Class
 */

package ch.aelgict.dbinput.dbinput;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtDBName"
    private TextField txtDBName; // Value injected by FXMLLoader

    @FXML // fx:id="txtPort"
    private TextField txtPort; // Value injected by FXMLLoader

    @FXML // fx:id="txtUsername"
    private TextField txtUsername; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassword"
    private TextField txtPassword; // Value injected by FXMLLoader

    @FXML // fx:id="txtTable"
    private TextField txtTable; // Value injected by FXMLLoader

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader

    @FXML // fx:id="btnChancel"
    private Button btnChancel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtDBName != null : "fx:id=\"txtDBName\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtPort != null : "fx:id=\"txtPort\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert txtTable != null : "fx:id=\"txtTable\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert btnChancel != null : "fx:id=\"btnChancel\" was not injected: check your FXML file 'hello-view.fxml'.";

    }
}
