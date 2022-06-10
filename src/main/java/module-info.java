module ch.aelgict.dbinput {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;


    opens ch.aelgict.dbinput.controller to javafx.fxml;
    opens ch.aelgict.dbinput.model to javafx.fxml;
    exports ch.aelgict.dbinput.model;
    exports ch.aelgict.dbinput.controller;
}