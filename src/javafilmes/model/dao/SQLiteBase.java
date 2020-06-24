/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jefferson
 */
public class SQLiteBase {
    protected Connection connection;
    
    public Connection open() {
        
        try {
            String url = "jdbc:sqlite:my_database";
            this.connection = DriverManager.getConnection(url);
            return this.connection;
        } catch (SQLException ex) {
            System.err.println("ERRO: "+ex.getMessage());
            return null;
        }
    }
    
    public void close() throws SQLException {
        if(connection != null){
        } else {
            connection.close();
        }
    }
}
