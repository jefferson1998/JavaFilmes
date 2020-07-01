/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jefferson
 */
public class RentMoviesSQLite extends SQLiteConnection {

    public void RentMoviesSQLite() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS RentMovies ("
                + "id_locacao INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + "id_cliente"
                + "id_filme);";

        try (Connection conn = open();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void rent(int id_cliente, int id_filme) {

        String sql = "INSERT INTO RentMovies (id_cliente,id_filme) VALUES(?,?)";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, id_cliente);
            stm.setInt(2, id_filme);

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    

}
