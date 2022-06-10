package ch.aelgict.dbinput.controller;

import ch.aelgict.dbinput.logic.DatabaseConnection;
import ch.aelgict.dbinput.model.Table;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Starter extends Application {

    private Stage stage;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    public DatabaseConnection getDatabaseConnection(){
        return this.databaseConnection;
    }
    public Stage getStage(){
        return stage;
    }
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Connection.fxml"));
        Parent root = fxmlLoader.load();
        ConnectionController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        controller.initData(this, databaseConnection);
        this.stage.setTitle("Connection to Database");
        this.stage.setScene(scene);
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(500);
        this.stage.show();
    }
    public void showTableView(Stage stage, DatabaseConnection databaseConnection) {
        try {
            this.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableView.fxml"));
            Parent root = null;

            root = fxmlLoader.load();

            TableViewController controller = fxmlLoader.getController();
            controller.initData(this, databaseConnection);
            Scene scene = new Scene(root);
            this.stage.setTitle("Tables of Database");
            this.stage.setScene(scene);
            this.stage.setMinHeight(600);
            this.stage.setMinWidth(500);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showColumnView(Stage stage, DatabaseConnection databaseConnection, Table selected) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableInformationView.fxml"));
        Parent root = fxmlLoader.load();
        TableInformationViewController controller = fxmlLoader.getController();
        controller.initData(this, databaseConnection, selected);
        Scene scene = new Scene(root);
        this.stage.setTitle("Table Information - "+selected.getTableName());
        this.stage.setScene(scene);
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(500);
        this.stage.show();
    }

    public void showRowAddView(Stage stage, DatabaseConnection databaseConnection, Table selected) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddRowView.fxml"));
        Parent root = fxmlLoader.load();
        AddRowViewController controller = fxmlLoader.getController();
        controller.initData(this, databaseConnection, selected);
        Scene scene = new Scene(root);
        this.stage.setTitle("Add Row");
        this.stage.setScene(scene);
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(500);
        this.stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}