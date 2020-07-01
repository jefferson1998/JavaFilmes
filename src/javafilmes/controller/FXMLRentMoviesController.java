/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafilmes.entity.Filme;
import javafilmes.repositories.FilmeSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Jefferson
 */
public class FXMLRentMoviesController implements Initializable {

    @FXML
    private TableView<Filme> tableView;

    @FXML
    private TableColumn<Filme, String> tableColummNome;

    @FXML
    private TableColumn<Filme, Image> tableColummDisponivel;

    @FXML
    private Label textRegister;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelDescricao;

    @FXML
    private Label labelDuracao;

    @FXML
    private Button buttonRent;
    FilmeSQLite database = null;

    private List<Filme> listFilmes;
    private ObservableList<Filme> observableListFilme;

    @FXML
    void handleButtonRent(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        database = new FilmeSQLite();

        carregarTableViewFilme();
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFilmes(newValue));
    }

    public void carregarTableViewFilme() {
        tableColummNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColummDisponivel.setCellFactory(param -> {
            ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);
            TableCell<Filme, Image> cell = new TableCell<Filme, Image>() {
                @Override
                public void updateItem(Image i, boolean empty) {
                    if (i != null) {
                        System.out.println(".updateItem()");
                        imageview.setImage(i);
                    }
                }
            };
            cell.setGraphic(imageview);
            return cell;
        });
        listFilmes = database.all();
        observableListFilme = FXCollections.observableArrayList(listFilmes);
        tableColummDisponivel.setCellValueFactory(new PropertyValueFactory<Filme, Image>("filme"));
        tableView.setItems(observableListFilme);
    }

    public void selecionarItemTableViewFilmes(Filme filme) {
        if (filme != null) {
            labelNome.setText(String.valueOf(filme.getNome()));
            labelDescricao.setText(filme.getDescricao());
            labelDuracao.setText(filme.getDuracao());

        } else {
            labelNome.setText("");
            labelDescricao.setText("");
            labelDuracao.setText("");

        }

    }

}
