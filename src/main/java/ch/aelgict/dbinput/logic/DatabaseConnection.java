package ch.aelgict.dbinput.logic;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private Connection conn;
    private Statement st;
    private String tableName;
    public String createConnection(String host, int port, String user, String pwd, String dbname, String tableName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+host+":"+String.valueOf(port)+"/"+dbname;
            this.conn = DriverManager.getConnection(url,user, pwd);
            Statement ps = conn.createStatement();
            this.tableName = tableName;
            return "Connecion successful";
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Connection failed";
    }

    /**
     * This Connection returns if the already created Connection is valid or not.
     * @return true, false
     */
    public boolean isConnectionStable(){
        try {
            if(!this.conn.isClosed()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection getConnection(){
        return this.conn;
    }

    public ArrayList<String> getTablesList(Connection conn)
            throws SQLException {

        ArrayList<String> listOfTable = new ArrayList<String>();

        DatabaseMetaData md = conn.getMetaData();

        ResultSet rs = md.getTables(null, null, "%", null);

        while (rs.next()) {
            if (rs.getString(4).equalsIgnoreCase("TABLE")) {
                listOfTable.add(rs.getString(3));
            }
        }
        return listOfTable;
    }
    public ArrayList<Column> getColumnOfTable(String table){
        ArrayList<Column> listOfColumns = new ArrayList<>();
        try {
            Statement statement = null;

            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("Select * From "+table);

            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

            for(int i = 0; i < columnCount; i++){
                listOfColumns.add(new Column(md.getColumnName(i),i,table));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfColumns;
    }
    public String getDatatypeOfColumn(Column column){
        String columnDataType = "";
        try {
            Statement st = null;

            st = conn.createStatement();

            ResultSet rs = st.executeQuery("Select * From"+column.getTableName());
            ResultSetMetaData rsmt = rs.getMetaData();
            columnDataType = rsmt.getColumnTypeName(column.getColumnIndex());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnDataType;
    }
}
