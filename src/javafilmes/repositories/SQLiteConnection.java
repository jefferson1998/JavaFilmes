/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

    protected Connection connection;

    public Connection open() {
        try {
            String url = "jdbc:sqlite:my_database.db";
//            if (this.connection == null) {
            this.connection = DriverManager.getConnection(url);
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.connection;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }

    }
}
