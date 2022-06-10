package ch.aelgict.dbinput.controller;

import ch.aelgict.dbinput.logic.DatabaseConnection;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableViewController {
    private Starter starter;
    private DatabaseConnection databaseConnection;
    private Table selected;
    public void initData(Starter starter, DatabaseConnection databaseConnection){
        this.starter = starter;
        this.databaseConnection = databaseConnection;
        tableView.getColumns().addAll(columnTableName, columnColumnCount);
        columnTableName.setMinWidth(200);
        columnColumnCount.setMinWidth(50);
        columnTableName.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        columnColumnCount.setCellValueFactory(new PropertyValueFactory<>("columnCount"));
        this.tableView.setItems(getTables());
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader


    @FXML
    private TableView<Table> tableView;

    @FXML
    private TableColumn<Table, String> columnTableName = new TableColumn<Table, String>("TabellenName");

    @FXML
    private TableColumn<Table, Integer> columnColumnCount = new TableColumn<Table, Integer>("AnzahlSpalten");

    @FXML
    private Button btnChancel;

    @FXML
    private Button btnAdd;

    @FXML
    void handleButtonNext(ActionEvent event) {
        try {
            selected = tableView.getSelectionModel().getSelectedItem();
            starter.showColumnView(starter.getStage(), this.databaseConnection, selected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleButtonCancel(ActionEvent event) {
        try {
            starter.start(starter.getStage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleButtonAdd(ActionEvent event) {
        try {
            selected = tableView.getSelectionModel().getSelectedItem();
            starter.showRowAddView(starter.getStage(), this.databaseConnection, this.selected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public ObservableList<Table> getTables() {
        ObservableList<Table> tableObservableList = FXCollections.observableArrayList();
        try {
            if(this.databaseConnection.isConnectionStable()){
                ArrayList<Table> tableList = this.databaseConnection.getTablesList(this.databaseConnection.getConn());
                for(int i = 0; i < tableList.size(); i++){
                    tableObservableList.add(new Table(tableList.get(i).getTableName(),tableList.get(i).getColumnCount()));
                }
            }else {
                System.out.println("connection failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableObservableList;
    }

}
