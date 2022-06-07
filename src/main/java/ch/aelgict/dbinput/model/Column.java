package ch.aelgict.dbinput.model;

public class Column{

    private String columnName;
    private String tableName;
    private int columnIndex;
    private String columnDataType;


    public Column(String columnName, int columnIndex, String tableName, String columnDataType){
        this.columnDataType = columnDataType;
        this.columnName = columnName;
        this.columnIndex = columnIndex;
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnDataType() {
        return columnDataType;
    }

    public void setColumnDataType(String columnDataType) {
        this.columnDataType = columnDataType;
    }
}
