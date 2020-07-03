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
import javafilmes.entity.Filme;
import javafx.scene.image.Image;

public class FilmeSQLite extends SQLiteConnection {

    public void FilmeSQLite() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS Filmes ("
                + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + "nome VARCHAR(80),"
                + "descricao VARCHAR(80),"
                + "duracao VARCHAR(80),"
                + "disponivel INTEGER);";

        try (Connection conn = open();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(Filme f) throws SQLException {

        String sql = "INSERT INTO Filmes(nome,descricao,duracao, disponivel) VALUES(?,?,?,?)";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, f.getNome());
            stm.setString(2, f.getDescricao());
            stm.setString(3, f.getDuracao());
            int flag = (f.isDisponivel() == true) ? 1 : 0;
            stm.setInt(4, flag);

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Filme f) throws SQLException {

        String sql = "UPDATE Filmes SET nome=?, descricao=?, duracao=?, disponivel=? WHERE id=?";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, f.getNome());
            stm.setString(2, f.getDescricao());
            stm.setString(3, f.getDuracao());
            int flag = (f.isDisponivel() == true) ? 1 : 0;
            stm.setInt(4, flag);
            stm.setInt(5, f.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean remove(Filme f) throws SQLException {

        String sql = "DELETE FROM Filmes WHERE id=?";
        try (Connection conn = open();
                PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, f.getId());
            stm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Filme> all() {
        ArrayList<Filme> filmes = new ArrayList<>();

        String sql = "SELECT * FROM Filmes ORDER BY id ASC;";

        try {
            Connection conn = open();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Filme f = new Filme();
                f.setId(rs.getInt(1));
                f.setNome(rs.getString(2));
                f.setDescricao(rs.getString(3));
                f.setDuracao(rs.getString(4));
                if (rs.getInt(5) == 1) {
                    f.setImagem(new Image("/javafilmes/resource/visto.png"));
                } else {
                    f.setImagem(new Image("/javafilmes/resource/nao_visto.png"));
                }
                filmes.add(f);
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return filmes;
    }

    public Filme getId(String nome) throws SQLException {
        String sql = "SELECT * FROM Filmes WHERE nome=?;";
        Filme f = new Filme();
        try {
            Connection conn = open();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nome);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                f.setId(rs.getInt(1));
                f.setNome(rs.getString(2));
                f.setDescricao(rs.getString(3));
                f.setDuracao(rs.getString(4));
                boolean flag2 = (rs.getInt(5) == 1) ? true : false;
                f.setDisponivel(flag2);

            }
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }
    
    public void rent(int id_cliente, int id_filme) throws SQLException {

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
