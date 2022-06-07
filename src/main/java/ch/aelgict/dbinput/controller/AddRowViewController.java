package ch.aelgict.dbinput.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ch.aelgict.dbinput.logic.DatabaseConnection;
import ch.aelgict.dbinput.model.Column;
import ch.aelgict.dbinput.model.Table;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddRowViewController {

    private Starter starter;
    private DatabaseConnection databaseConnection;
    private Table selected;

    private ArrayList<Column> columns = new ArrayList<>();

    @FXML
    private VBox nameColumn;

    @FXML
    private VBox inputColumn;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    public void initData(Starter starter, DatabaseConnection databaseConnection, Table selected) {
        this.starter = starter;
        this.databaseConnection = databaseConnection;
        this.selected = selected;
        columns = databaseConnection.getColumnListOfTable(selected);
        createFXMLElementsAccordingColumnsOfTable(columns);
    }
    public void createFXMLElementsAccordingColumnsOfTable(ArrayList<Column> columns){
        for (int i = 0; i < columns.size(); i++) {
            switch (columns.get(i).getColumnDataType()) {
                case "INT" :
                case "VARCHAR" :
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    TextField tx = new TextField();
                    tx.setId(columns.get(i).getColumnName());
                    inputColumn.getChildren().add(tx);
                    break;
                case "LONGTEXT" :
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    TextArea textArea = new TextArea();
                    textArea.setMinHeight(100);
                    textArea.setMinWidth(200);
                    textArea.setId(columns.get(i).getColumnName());
                    inputColumn.getChildren().add(textArea);
                    break;
                case "Date" :
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    DatePicker datePicker = new DatePicker();
                    datePicker.setMaxHeight(50);
                    datePicker.setId(columns.get(i).getColumnName());
                    inputColumn.getChildren().add(datePicker);
                case "LONGBLOB" :
                    if(columns.get(i).getColumnName().equals("image")){
                        nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                        //open image
                    }
                case "String" :
            }
            if(columns.get(i).getColumnDataType() == "INT"){

            }
        }
    }
}