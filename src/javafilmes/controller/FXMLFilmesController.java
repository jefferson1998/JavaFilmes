/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jefferson
 */
public class FXMLFilmesController implements Initializable {

    @FXML
    private Label textRegister;

    @FXML
    private Label textRegister1;

    @FXML
    private TextField textName;

    @FXML
    private Label textRegister11;

    @FXML
    private TextField textDescrition;

    @FXML
    private Label textRegister12;

    @FXML
    private TextField textDuraction;

    @FXML
    private Button button;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
