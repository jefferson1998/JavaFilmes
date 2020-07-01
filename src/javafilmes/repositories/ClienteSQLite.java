/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafilmes.entity.Cliente;
import javafilmes.util.Exceptions;

/**
 *
 * @author Jefferson
 */
public class ClienteSQLite extends SQLiteConnection {

    public void ClienteSQLite() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS Clientes ("
                + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + "nome VARCHAR(80),"
                + "sobrenome VARCHAR(80),"
                + "apelido VARCHAR(80),"
                + "cpf VARCHAR(80),"
                + "ehAdmin INTEGER,"
                + "senha VARCHAR(80));";

        try (Connection conn = open();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(Cliente c) {

        String sql = "INSERT INTO Clientes(nome,sobrenome,apelido,cpf,ehAdmin,senha) VALUES(?,?,?,?,?,?)";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, c.getNome());
            stm.setString(2, c.getSobrenome());
            stm.setString(3, c.getApelido());
            stm.setString(4, c.getCpf());
            int flag = (c.isEhAdmin() == true) ? 1 : 0;
            stm.setInt(5, flag);
            stm.setString(6, c.getSenha());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Cliente c) {

        String sql = "UPDATE Clientes SET nome=?, sobrenome=?, apelido=?, cpf=?, ehAdmin=?, senha=? WHERE id=?";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, c.getNome());
            stm.setString(2, c.getSobrenome());
            stm.setString(3, c.getApelido());
            stm.setString(4, c.getCpf());
            int flag = (c.isEhAdmin() == true) ? 1 : 0;
            stm.setInt(5, flag);
            stm.setString(6, c.getSenha());
            stm.setInt(7, c.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean remove(Cliente c) {

        String sql = "DELETE FROM Clientes WHERE id=?";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, c.getId());
            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Cliente> all() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM Clientes ORDER BY id ASC;";

        try {
            Connection conn = open();
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setSobrenome(rs.getString(3));
                c.setApelido(rs.getString(4));
                c.setCpf(Exceptions.imprimeCPF(rs.getString(5)));
                boolean flag2 = (rs.getInt(6) == 1) ? true : false;
                c.setEhAdmin(flag2);
                c.setSenha(rs.getString(7));

                clientes.add(c);
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clientes;
    }

    public Cliente checkLoginPassword(String cpf, String senha) {
        String sql = "SELECT * FROM Clientes WHERE cpf=? AND senha=? ;";
        Cliente c = new Cliente();
        try {
            Connection conn = open();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, cpf);
            stm.setString(2, senha);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setSobrenome(rs.getString(3));
                c.setApelido(rs.getString(4));
                c.setCpf(rs.getString(5));
                boolean flag2 = (rs.getInt(6) == 1) ? true : false;
                c.setEhAdmin(flag2);
                c.setSenha(rs.getString(7));

            }
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return c;
    }
}
