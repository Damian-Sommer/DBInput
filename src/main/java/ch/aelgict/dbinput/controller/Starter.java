package ch.aelgict.dbinput.controller;

import ch.aelgict.dbinput.controller.ColumnViewController;
import ch.aelgict.dbinput.controller.ConnectionController;
import ch.aelgict.dbinput.controller.TableViewController;
import ch.aelgict.dbinput.logic.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connection.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tableView.fxml"));
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

    public void showColumnView(Stage stage, DatabaseConnection databaseConnection) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("columnView.fxml"));
        Parent root = fxmlLoader.load();
        ColumnViewController controller = fxmlLoader.getController();
        controller.initData(this, databaseConnection);
        Scene scene = new Scene(root);
        this.stage.setTitle("Columns of Table");
        this.stage.setScene(scene);
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(500);
        this.stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}