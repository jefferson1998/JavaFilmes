/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafilmes.entity.Cliente;

/**
 *
 * @author Jefferson
 */
public class ClienteSQLite extends SQLiteConnection {

    public ClienteSQLite() throws SQLException {

        open();
        try {
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Clientes ("
                    + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + "nome VARCHAR(80),"
                    + "sobrenome VARCHAR(80),"
                    + "apelido VARCHAR(80),"
                    + "cpf VARCHAR(80),"
                    + "ehAdmin INTEGER );");
            stm.executeQuery();
        } catch (SQLException ex) {
            ex.getMessage();
        } finally {
            close();
        }

    }

    public void create(Cliente c) throws SQLException {
        open();
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Clientes VALUES(?,?,?,?,?,?);");
            stm.setString(2, c.getNome());
            stm.setString(3, c.getSobrenome());
            stm.setString(4, c.getApelido());
            stm.setString(5, c.getCpf());
            int flag = (c.isEhAdmin() == true) ? 1 : 0;
            stm.setInt(6, flag);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            close();
        }
    }

    public List<Cliente> all() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();

        open();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Clientes ORDER BY id ASC;");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setSobrenome(rs.getString(3));
                c.setApelido(rs.getString(4));
                c.setCpf(rs.getString(5));
                boolean flag2 = (rs.getInt(6) == 1) ? true : false;
                c.setEhAdmin(flag2);

                clientes.add(c);
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            close();
        }

        return clientes;
    }
}
