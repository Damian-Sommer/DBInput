package ch.aelgict.dbinput.controller;

import ch.aelgict.dbinput.logic.DatabaseConnection;
import ch.aelgict.dbinput.model.Column;
import ch.aelgict.dbinput.model.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableInformationViewController {
    private Starter starter;
    private Table selectedTable;
    private DatabaseConnection databaseConnection;

    public void initData(Starter starter, DatabaseConnection databaseConnection, Table selected) {
        this.starter = starter;
        this.selectedTable = selected;
        this.databaseConnection = databaseConnection;
        if (this.databaseConnection.isConnectionStable()) {
            ArrayList columns = this.databaseConnection.getColumnListOfTable(selectedTable);
        }
        tableView.getColumns().addAll(columnTableName, columnDataType, columnColumnIndex);
        columnTableName.setMinWidth(200);
        columnColumnIndex.setMinWidth(50);
        columnDataType.setMinWidth(100);
        columnTableName.setCellValueFactory(new PropertyValueFactory<>("columnName"));
        columnColumnIndex.setCellValueFactory(new PropertyValueFactory<>("columnIndex"));
        columnDataType.setCellValueFactory(new PropertyValueFactory<>("columnDataType"));
        this.tableView.setItems(getColumns());
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button btnNext;

    @FXML
    private TableView<Column> tableView;

    @FXML
    private TableColumn<Column, String> columnTableName = new TableColumn<Column, String>("ColumnName");

    @FXML
    private TableColumn<Column, Integer> columnColumnIndex = new TableColumn<Column, Integer>("ColumnIndex");

    @FXML
    private TableColumn<Column, String> columnDataType = new TableColumn<Column, String>("ColumnDataType");

    @FXML
    private Button btnChancel;

    @FXML
    private Button btnAdd;

    @FXML
    void handleButtonAdd(ActionEvent event) {

    }

    @FXML
    void handleButtonCancel(ActionEvent event) {
        starter.showTableView(starter.getStage(), this.databaseConnection);
    }

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
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'TableInformationView.fxml'.";
    }

    public ObservableList<Column> getColumns() {
        ObservableList<Column> columnObservableList = FXCollections.observableArrayList();
        if (this.databaseConnection.isConnectionStable()) {
            ArrayList<Column> columnList = this.databaseConnection.getColumnListOfTable(selectedTable);
            for (int i = 0; i < columnList.size(); i++) {
                columnObservableList.add(new Column(columnList.get(i).getColumnName(), i, selectedTable.getTableName(), columnList.get(i).getColumnDataType()));
            }
        } else {
            System.out.println("connection failed");
        }

        return columnObservableList;
    }
}
