/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafilmes.entity.Cliente;
import javafilmes.repositories.ClienteSQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Jefferson
 */
public class FXMLClientesController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private Label textRegister;

    @FXML
    private Label textRegister1;

    @FXML
    private TextField textName;

    @FXML
    private Label textRegister11;

    @FXML
    private TextField textSecondName;

    @FXML
    private Label textRegister12;

    @FXML
    private TextField textApelido;

    @FXML
    private Label textRegister13;

    @FXML
    private TextField textCpf;

    @FXML
    private Label textRegister14;

    @FXML
    private TextField textEhAdmin;

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        Cliente c = new Cliente("jefferson", "arruda", "cabeça", "1234", false);

        ClienteSQLite database = new ClienteSQLite();

        database.create(c);

        System.out.println(database.all());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
