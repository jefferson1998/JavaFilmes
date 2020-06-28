/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.util;

import javafx.scene.control.Alert;

/**
 *
 * @author Jefferson
 */
public class Messages {

    public static void messageAlertChooseClient() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Por favor, escolha um cliente na Tabela!");
        alert.show();
    }

    public static void messageAlertCpfInvalid() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Por favor, digite um CPF válido!");
        alert.show();
    }

    public static void messageAlertSurnameIncorret() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no sobrenome");
        alert.show();
    }

    public static void messageAlertNameIncorret() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no nome");
        alert.show();
    }

    public static void messageAlertDuracaoIncorret() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no Duração");
        alert.show();
    }

    public static void messageAlertDescricaoIncorret() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no Descricao");
        alert.show();
    }

    public static void messageAlertSenhaIncorret() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro na senha");
        alert.show();
    }

}
