package ch.aelgict.dbinput.dbinput;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {

    private Stage stage;

    public Stage getStage(){
        return stage;
    }
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connection.fxml"));
        Parent root = fxmlLoader.load();
        ConnectionController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        controller.initData(this);
        this.stage.setTitle("Connection to Database");
        this.stage.setScene(scene);
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(500);
        this.stage.show();
    }
    public void showTableView(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tableView.fxml"));
        Parent root = fxmlLoader.load();
        TableViewController controller = fxmlLoader.getController();
        controller.initData(this);
        Scene scene = new Scene(root);
        this.stage.setTitle("Tables of Database");
        this.stage.setScene(scene);
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(500);
        this.stage.show();
    }

    public void showColumnView(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("columnView.fxml"));
        Parent root = fxmlLoader.load();
        ColumnViewController controller = fxmlLoader.getController();
        controller.initData(this);
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