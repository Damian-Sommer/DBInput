package ch.aelgict.dbinput.model;

public class Table {
    private String tableName ;
    private Integer columnCount;

    public Table(String tableName, Integer columnCount){
        this.tableName = tableName;
        this.columnCount =columnCount;
    }
    public String getTableName(){
        return this.tableName;
    }
    public Integer getColumnCount(){
        return this.columnCount;
    }
    public void setTableName(String tableName){
        this.tableName= tableName;
    }
    public void setColumnCount(int columnCount){
        this.columnCount = columnCount;
    }/*
    public StringProperty getTableName(){
        return tableName;
    }
    public IntegerProperty getColumnCount(){
        return columnCount;
    }*/
}
