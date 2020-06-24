/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafilmes.model.dao.ClienteSQLite;
import javafilmes.model.entity.Cliente;
import javafilmes.model.entity.Endereco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Jefferson
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        Cliente c = new Cliente("jefferson", "arruda", "cabeça", "1234", false);
        
        ClienteSQLite database = new ClienteSQLite();
        
        database.create(c);
        
        System.out.println(database.all());
        
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
