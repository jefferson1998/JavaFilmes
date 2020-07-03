/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafilmes.entity.Filme;
import javafilmes.repositories.FilmeSQLite;
import javafilmes.repositories.RentMoviesSQLite;
import javafilmes.util.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Jefferson
 */
public class FXMLRentMoviesController implements Initializable {

    @FXML
    private TableView<Filme> tableView;

//    @FXML
//    private ListView<Filme> lvFilmesName;
    @FXML
    private HBox hBox;

    @FXML
    private ListView<HBoxCell> lvFilmesDisponivel;

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

    private static int id_cliente;

    private int id_filme;
    FilmeSQLite database = new FilmeSQLite();
    private Filme f;

    private List<Filme> listFilmes;
    private ObservableList<Filme> observableListFilme;

    @FXML
    void handleButtonRent(ActionEvent event) throws SQLException {

        if (f != null) {
            if (f.isDisponivel()) {
                database.rent(getId_cliente(), getId_filme());
                f.setDisponivel(false);
                database.update(f);
            } else {
                Messages.messageAlertFilmeNoSelectIncorret();

            }

        } else {
            Messages.messageAlertFilmeNoSelectIncorret();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        create();
        lvFilmesDisponivel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try {
                selecionarItemTableViewFilmes(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLRentMoviesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public static void pegarUser(int id_user) {
        setId_cliente(id_user);
    }

    public static class HBoxCell extends HBox {

        Label label = new Label();
        ImageView imageView = new ImageView();

        HBoxCell(String labelText, Image image) {
            super();
            imageView.setFitHeight(17);
            imageView.setFitWidth(17);
            imageView.setImage(image);
            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            this.getChildren().addAll(label, imageView);
        }
    }

    public void create() {
        listFilmes = database.all();
        List<HBoxCell> list = new ArrayList<>();

        for (int i = 0; i < listFilmes.size(); i++) {
            list.add(new HBoxCell(listFilmes.get(i).getNome() + "                        ",
                    listFilmes.get(i).getImagem()));
        }

        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        lvFilmesDisponivel.setItems(myObservableList);

    }

    public void selecionarItemTableViewFilmes(HBoxCell filme) throws SQLException {

        if (filme != null) {
            String[] array = String.valueOf(filme.label.getText()).split(" ");
            System.out.println(array[0]);
            f = database.getId(array[0]);
            labelNome.setText(String.valueOf(filme.label.getText()));
            labelDescricao.setText(f.getDescricao());
            labelDuracao.setText(f.getDuracao());
            System.out.println(f.getId());
            setId_filme(f.getId());
        } else {
            labelNome.setText("");
            labelDescricao.setText("");
            labelDuracao.setText("");
        }

    }

    public static int getId_cliente() {
        return id_cliente;
    }

    public static void setId_cliente(int aId_cliente) {
        id_cliente = aId_cliente;
    }

    public int getId_filme() {
        return id_filme;
    }

    public void setId_filme(int aId_filme) {
        id_filme = aId_filme;
    }

}
