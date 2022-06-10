package ch.aelgict.dbinput.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ch.aelgict.dbinput.logic.DatabaseConnection;
import ch.aelgict.dbinput.model.Column;
import ch.aelgict.dbinput.model.Table;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sql.rowset.*;
import javax.sql.rowset.serial.SerialBlob;

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

    @FXML
    private Button btnSubmit;

    @FXML
    void handleButtonCancel(ActionEvent event) {

    }
    @FXML
    void handleButtonSubmit(ActionEvent event) throws SQLException, UnsupportedEncodingException {
        Connection connection = databaseConnection.getConnection();
        String query = "INSERT INTO "+selected.getTableName()+"(";
        for (int i = 0; i < columns.size(); i++){
            query += columns.get(i).getColumnName();
        }
        query += ") VALUES (";
        for(int i = 0; i < columns.size(); i++){
            query += "?";
        }
        query += ")";
        PreparedStatement pstm = connection.prepareStatement(query);
        for(int i = 0; i < columns.size(); i++){
            if(inputColumn.getChildren().get(i).getId().substring(0, 1).equals("tx")){
                TextField tx = (TextField) inputColumn.getChildren().get(i);
                pstm.setString(i, tx.getText());
            } else if(inputColumn.getChildren().get(i).getId().substring(0, 1).equals("int")){
                TextField tx = (TextField) inputColumn.getChildren().get(i);
                int input = Integer.parseInt(tx.getText());
                pstm.setInt(i, input);
            }else if(inputColumn.getChildren().get(i).getId().substring(0, 1).equals("lt")){
                TextField tx = (TextField) inputColumn.getChildren().get(i);
                pstm.setString(i, tx.getText());
            }else if(inputColumn.getChildren().get(i).getId().substring(0, 1).equals("dp")){
                DatePicker tx = (DatePicker) inputColumn.getChildren().get(i);
                pstm.setDate(i, Date.valueOf(tx.getValue()));
            }else if(inputColumn.getChildren().get(i).getId().substring(0, 1).equals("longblob")){
                TextField tx = (TextField) inputColumn.getChildren().get(i);
                byte[] data = tx.getText().getBytes("UTF-8");
                Blob blob = new SerialBlob(data);
                pstm.setBlob(i, blob);
            }
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    public void initData(Starter starter, DatabaseConnection databaseConnection, Table selected) {
        this.starter = starter;
        this.databaseConnection = databaseConnection;
        this.selected = selected;
        columns = databaseConnection.getColumnListOfTable(this.selected);
        createFXMLElementsAccordingColumnsOfTable(columns);
    }

    public void createFXMLElementsAccordingColumnsOfTable(ArrayList<Column> columns) {
        for (int i = 0; i < columns.size(); i++) {
            switch (columns.get(i).getColumnDataType()) {
                case "INT":
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    TextField textField2 = new TextField();
                    textField2.setId("int"+columns.get(i).getColumnName());
                    inputColumn.getChildren().add(textField2);
                case "VARCHAR":
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    TextField textField = new TextField();
                    textField.setId("tx"+columns.get(i).getColumnName());
                    inputColumn.getChildren().add(textField);
                    break;
                case "LONGTEXT":
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    TextArea textArea = new TextArea();
                    textArea.setMinHeight(100);
                    textArea.setMinWidth(200);
                    textArea.setId("lt"+columns.get(i).getColumnName());
                    inputColumn.getChildren().add(textArea);
                    break;
                case "Date":
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    DatePicker datePicker = new DatePicker();
                    datePicker.setMaxHeight(50);
                    datePicker.setId("dp"+columns.get(i).getColumnName());
                    inputColumn.getChildren().add(datePicker);
                case "LONGBLOB":
                    nameColumn.getChildren().add(new Label(columns.get(i).getColumnName()));
                    //open image
                    FileChooser fileChooser = new FileChooser();
                    TextField textField1 = new TextField();
                    textField1.setId("longblob"+columns.get(i).getColumnName());
                    Button fileButton = new Button("Chose Image");
                    fileButton.setId("btn" + columns.get(i).getColumnName());
                    fileButton.setOnAction(
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(final ActionEvent e) {
                                    FileChooser fileChooser = new FileChooser();
                                    File file = fileChooser.showOpenDialog(new Stage());
                                    textField1.setText(file.getAbsolutePath());
                                }
                            });
                    HBox hBox = new HBox(textField1, fileButton);
                    inputColumn.getChildren().add(hBox);
                    break;
            }
        }
    }
}