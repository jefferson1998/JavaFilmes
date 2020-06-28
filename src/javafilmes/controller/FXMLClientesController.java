package javafilmes.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafilmes.entity.Cliente;
import javafilmes.repositories.ClienteSQLite;
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

public class FXMLClientesController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private TableView<Cliente> tableView;

    @FXML
    private TableColumn<Cliente, String> tableColummCPF;

    @FXML
    private TableColumn<Cliente, String> tableColummNome;

    @FXML
    private Label textRegister;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSobrenome;

    @FXML
    private Label labelCpf;

    @FXML
    private Label labelApelido;

    @FXML
    private Label labelEndereco;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonRemover;
    ClienteSQLite database = null;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        database = new ClienteSQLite();

        carregarTableViewCliente();
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));

    }

    public void carregarTableViewCliente() {
        tableColummNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColummCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = database.all();
        observableListCliente = FXCollections.observableArrayList(listClientes);
        tableView.setItems(observableListCliente);
    }

    public void selecionarItemTableViewClientes(Cliente cliente) {
        if (cliente != null) {
            labelNome.setText(String.valueOf(cliente.getNome()));
            labelSobrenome.setText(cliente.getSobrenome());
            labelCpf.setText(cliente.getCpf());
            labelApelido.setText(cliente.getApelido());
        } else {
            labelNome.setText("");
            labelSobrenome.setText("");
            labelCpf.setText("");
            labelApelido.setText("");
        }

    }

    @FXML
    public void handleButtonInserir() throws IOException {
        Cliente cliente = new Cliente();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
        if (buttonConfirmarClicked) {
            database.create(cliente);
            carregarTableViewCliente();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosClientesDialog(cliente);
            if (buttonConfirmarClicked) {
                database.update(cliente);
                carregarTableViewCliente();
            }
        } else {
            Messages.messageAlertChooseClient();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        Cliente cliente = tableView.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            database.remove(cliente);
            carregarTableViewCliente();
        } else {
            Messages.messageAlertChooseClient();
        }
    }

    public boolean showFXMLAnchorPaneCadastrosClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLClientesDialogController.class.getResource("/javafilmes/pages/FXMLClienteCadastro.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
}
