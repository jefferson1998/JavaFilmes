package javafilmes.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafilmes.entity.Cliente;
import javafilmes.utils.Exceptions;
import javafilmes.utils.Messages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLClientesDialogController implements Initializable {

    @FXML
    private Label labelApelido;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textSobrenome;

    @FXML
    private TextField textCpf;

    @FXML
    private TextField textApelido;

    @FXML
    private TextField textEndereco;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.textNome.setText(cliente.getNome());
        this.textSobrenome.setText(cliente.getSobrenome());
        this.textCpf.setText(cliente.getCpf());
        this.textApelido.setText(cliente.getApelido());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {

            cliente.setNome(textNome.getText());
            cliente.setCpf(Exceptions.imprimeCPF(textCpf.getText()));
            cliente.setSobrenome(textSobrenome.getText());
            cliente.setApelido(textApelido.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    private boolean validarEntradaDeDados() {
        boolean flag = true;
        if (textNome.getText() == null || textNome.getText().length() == 0) {
            Messages.messageAlertNameIncorret();
            flag = false;
        }
        if (textCpf.getText() == null || !Exceptions.isCPF(textCpf.getText())) {
            Messages.messageAlertCpfInvalid();
            flag = false;
        }
        if (textSobrenome.getText() == null || textSobrenome.getText().length() == 0) {
            Messages.messageAlertSurnameIncorret();
            flag = false;
        }
        return flag;
    }

}
