/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafilmes.entity.Filme;
import javafilmes.repositories.FilmeSQLite;
import javafilmes.util.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jefferson
 */
public class FXMLFilmesController implements Initializable {

    @FXML
    private TableView<Filme> tableView;

    @FXML
    private TableColumn<Filme, String> tableColummNome;

    @FXML
    private TableColumn<Filme, String> tableColummDuracao;

    @FXML
    private Label textRegister;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelDescricao;

    @FXML
    private Label labelDuracao;

    @FXML
    private Label labelDisponivel;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonRemover;
    FilmeSQLite database = null;

    private List<Filme> listFilmes;
    private ObservableList<Filme> observableListFilme;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        database = new FilmeSQLite();

        carregarTableViewFilme();
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFilmes(newValue));
    }

    public void carregarTableViewFilme() {
        tableColummNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColummDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));

        listFilmes = database.all();
        observableListFilme = FXCollections.observableArrayList(listFilmes);
        tableView.setItems(observableListFilme);
    }

    public void selecionarItemTableViewFilmes(Filme filme) {
        if (filme != null) {
            labelNome.setText(String.valueOf(filme.getNome()));
            labelDescricao.setText(filme.getDescricao());
            labelDuracao.setText(filme.getDuracao());
            labelDisponivel.setText(filme.isDisponivel() == true ? "sim" : "não");

        } else {
            labelNome.setText("");
            labelDescricao.setText("");
            labelDuracao.setText("");
            labelDisponivel.setText("");

        }

    }

    @FXML
    public void handleButtonInserir() throws IOException, SQLException {
        Filme filme = new Filme();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosFilmesDialog(filme);
        if (buttonConfirmarClicked) {
            database.create(filme);
            carregarTableViewFilme();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, SQLException {
        Filme filme = tableView.getSelectionModel().getSelectedItem();
        if (filme != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosFilmesDialog(filme);
            if (buttonConfirmarClicked) {
                database.update(filme);
                carregarTableViewFilme();
            }
        } else {
            Messages.messageAlertChooseClient();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        Filme filme = tableView.getSelectionModel().getSelectedItem();
        if (filme != null) {
            database.remove(filme);
            carregarTableViewFilme();
        } else {
            Messages.messageAlertChooseClient();
        }
    }

    public boolean showFXMLAnchorPaneCadastrosFilmesDialog(Filme filme) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLFilmesDialogController.class.getResource("/javafilmes/pages/FXMLFilmeCadastro.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Filmes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLFilmesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFilme(filme);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
