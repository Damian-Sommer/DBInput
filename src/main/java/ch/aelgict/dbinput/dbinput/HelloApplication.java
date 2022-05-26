package ch.aelgict.dbinput.dbinput;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
/*
public static ArrayList<String> getTablesList(Connection conn)
            throws SQLException {

        ArrayList<String> listofTable = new ArrayList<String>();

        DatabaseMetaData md = conn.getMetaData();

        ResultSet rs = md.getTables(null, null, "%", null);

        while (rs.next()) {
            if (rs.getString(4).equalsIgnoreCase("TABLE")) {
                listofTable.add(rs.getString(3));
            }
        }
        return listofTable;
    }
 */