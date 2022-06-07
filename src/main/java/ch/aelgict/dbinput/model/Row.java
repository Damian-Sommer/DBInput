package ch.aelgict.dbinput.model;

import java.util.ArrayList;

public class Row {
    private ArrayList<Column> columns = new ArrayList<>();
    public Row(ArrayList<Column> columns){
        this.columns = columns;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
}
