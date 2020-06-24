/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafilmes.model.entity.Cliente;

/**
 *
 * @author Jefferson
 */
public class ClienteSQLite extends SQLiteBase{
    
    public ClienteSQLite() throws SQLException{
        
        open();      
        try {
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Clientes (" + 
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "sobrenome TEXT," +
                    "apelido TEXT," +
                    "cpf TEXT," +
                    "ehAdmin boolean);" );
            stm.executeQuery();
        } catch (SQLException ex) {
            ex.getMessage();
        } finally{
            close();
        }
        
    }
    
    public void create(Cliente c) throws SQLException{
        open();      
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Clientes VALUES(?,?,?,?,?,?);");
            stm.setString(2, c.getNome());
            stm.setString(3, c.getSobrenome());
            stm.setString(4, c.getApelido());
            stm.setString(5, c.getCpf());
            stm.setBoolean(6, c.isEhAdmin());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        } finally{
            close();
        }
    }
    
    public List<Cliente> all() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        open();      
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Clientes ORDER BY id ASC;");
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setSobrenome(rs.getString(3));
                c.setApelido(rs.getString(4));
                c.setCpf(rs.getString(5));
                c.setEhAdmin(rs.getBoolean(6));
               
                clientes.add(c);
            }
            stm.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        } finally{
            close();
        }
        
        return clientes;
    }
}
