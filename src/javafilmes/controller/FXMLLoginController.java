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
import javafilmes.util.Messages;
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

    private Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void handerLogin(ActionEvent event) throws IOException {
        Parent myNewScene = null;

        ClienteSQLite database = new ClienteSQLite();
        Cliente c = database.checkLoginPassword(getLabelUsernam().getText(), labelPassoword.getText());
        if (c.getId() != null) {
            FXMLRentMoviesController.pegarUser(database.getId(labelUsernam.getText()).getId());
            if (c.isEhAdmin()) {
                myNewScene = FXMLLoader.load(getClass().getResource("/javafilmes/pages/FXMLMenuController.fxml"));
                Scene scene = new Scene(myNewScene);
                getStage().setScene(scene);
                getStage().setTitle("Menu");
                getStage().show();
            } else if (!c.isEhAdmin()) {
                myNewScene = FXMLLoader.load(getClass().getResource("/javafilmes/pages/FXMLRentMovies.fxml"));
                Scene scene = new Scene(myNewScene);
                getStage().setScene(scene);
                getStage().setTitle("Alugar Filmes");
                getStage().show();
            }
        } else {
            Messages.messageAlertNoHaveUser();
        }

    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the labelUsernam
     */
    public TextField getLabelUsernam() {
        return labelUsernam;
    }

    /**
     * @param labelUsernam the labelUsernam to set
     */
    public void setLabelUsernam(TextField labelUsernam) {
        this.labelUsernam = labelUsernam;
    }

}
