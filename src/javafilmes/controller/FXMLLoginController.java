/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafilmes.entity.Cliente;
import javafilmes.repositories.ClienteSQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jefferson
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private AnchorPane conteudo;

    @FXML
    private TextField labelUsernam;

    @FXML
    private PasswordField labelPassoword;

    @FXML
    private Button buttonLogin;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void handerLogin(ActionEvent event) throws IOException {
        Parent myNewScene = null;
        Stage stage = new Stage();
        System.out.println("javafilmes.controller.FXMLLoginController.handerLogin()");

        ClienteSQLite database = new ClienteSQLite();
        Cliente c = database.checkLoginPassword(labelUsernam.getText(), labelPassoword.getText());
        System.out.println(c.isEhAdmin());
        if (c.isEhAdmin()) {
            
            myNewScene = FXMLLoader.load(getClass().getResource("/javafilmes/pages/FXMLMenuController.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("Menu");
            stage.show();
        } else if (!c.isEhAdmin()) {
            myNewScene = FXMLLoader.load(getClass().getResource("/javafilmes/pages/FXMLRentMovies.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("Alugar Filmes");
            stage.show();
        }
    }

}
