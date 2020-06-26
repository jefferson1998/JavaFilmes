/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafilmes.entity.Filme;
import javafilmes.utils.Messages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Jefferson
 */
public class FXMLFilmesDialogController implements Initializable {

    @FXML
    private TextField textNome;

    @FXML
    private TextField textDescricao;

    @FXML
    private TextField textDuracao;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Filme filme;

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void handleButtonConfirmar(ActionEvent event) {
        if (validarEntradaDeDados()) {

            filme.setNome(textNome.getText());
            filme.setDescricao(textDescricao.getText());
            filme.setDuracao(textDuracao.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmarClicked
     */
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    /**
     * @param buttonConfirmarClicked the buttonConfirmarClicked to set
     */
    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    /**
     * @return the filme
     */
    public Filme getFilme() {
        return filme;
    }

    /**
     * @param filme the filme to set
     */
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    private boolean validarEntradaDeDados() {
        boolean flag = true;
        if (textNome.getText() == null || textNome.getText().length() == 0) {
            Messages.messageAlertNameIncorret();
            flag = false;
        }
        if (textDescricao.getText() == null || textDescricao.getText().length() == 0) {
            Messages.messageAlertDescricaoIncorret();
            flag = false;
        }
        if (textDuracao.getText() == null || textDuracao.getText().length() == 0) {
            Messages.messageAlertDuracaoIncorret();
            flag = false;
        }
        return flag;
    }
}
