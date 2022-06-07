package ch.aelgict.dbinput.logic;

import ch.aelgict.dbinput.model.Column;
import ch.aelgict.dbinput.model.Table;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private Connection conn;
    private String host;
    private int port;
    private String user;
    private String pwd;
    private String dbname;
    public String createConnection(String host, int port, String user, String pwd, String dbname) {
        this.host = host;
        this.port = port;
        this.pwd = pwd;
        this.dbname = dbname;
        this.user = user;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://"+host+":"+String.valueOf(port)+"/"+dbname;
            this.conn = DriverManager.getConnection(url,user, pwd);
            Statement st = conn.createStatement();
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


    /**
     * This Method returns a list of all Tables in a Database
     * @param conn
     * @return ArrayList<Table>
     * @throws SQLException
     */
    public ArrayList<Table> getTablesList(Connection conn)
            throws SQLException {

        ArrayList<Table> listOfTable = new ArrayList<>();
/*
        DatabaseMetaData md = conn.getMetaData();

        ResultSet rs = md.getTables(null, null, "%", null);
*/
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Show tables");
        while (rs.next()) {
            System.out.println(rs.getString(1));
            listOfTable.add((new Table(rs.getString(1), getNumberOfColumns(rs.getString(1)))));
            /*if (rs.getString(4).equalsIgnoreCase("TABLE")) {
                listOfTable.add(new Table(rs.getString(3), getNumberOfColumns(rs.getString(3))));
            }*/
        }
        return listOfTable;
    }

    public int getNumberOfColumns(String tableName){
        int numberOfCol = 0;
        try {
            Statement st = null;

            st = conn.createStatement();

            ResultSet rs = st.executeQuery("Select Count(*) From "+tableName);

            while (rs.next()){
                numberOfCol = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfCol;
    }

    public ArrayList<Column> getColumnListOfTable(Table table){
        ArrayList<Column> listOfColumns = new ArrayList<>();
        try {
            Statement statement = null;

            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("Select * From "+table.getTableName());

            ResultSetMetaData md = rs.getMetaData();

            int columnCount = md.getColumnCount();
            System.out.println(columnCount);
            for(int i = 1; i <= columnCount; i++){
                System.out.println(md.getColumnName(i));
                listOfColumns.add(new Column(md.getColumnName(i),i, table.getTableName(), md.getColumnTypeName(i)));
                System.out.println(md.getColumnTypeName(i));
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

    public Connection getConn() {
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    public boolean openConnection() {
        createConnection(this.host, this.port, this.user, this.pwd, this.dbname);
        return isConnectionStable();
    }
}
