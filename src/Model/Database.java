/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
/**
 *
 * @author Sayid
 */
public class Database {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/tubes";
    static final String USER = "root";
    static final String PASS = "";
    
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    
    public Database() throws SQLException{
        getConnection();
        stmt = conn.createStatement();
    }
    
    public void getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet query(String sql) throws SQLException{
        getConnection();
        ResultSet s = stmt.executeQuery(sql); //execute query
        return s;
    }
    
    public void update(String sql) throws SQLException{
        getConnection();
        stmt.executeUpdate(sql); //execute query
    }
}