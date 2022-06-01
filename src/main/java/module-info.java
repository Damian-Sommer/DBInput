module ch.aelgict.dbinput.dbinput {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ch.aelgict.dbinput.dbinput to javafx.fxml;
    exports ch.aelgict.dbinput.dbinput;
}