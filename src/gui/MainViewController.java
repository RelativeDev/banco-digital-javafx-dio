package gui;

import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Banco;
import model.entities.Cliente;
import model.services.ContaCorrente;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainViewController implements Initializable {

    @FXML
    public TableColumn<Banco, String> tableColumnCliente;

    @FXML
    public TableColumn<Banco, Integer> tableColumnAgencia;

    @FXML
    public TableColumn<Banco, String> tableColumnCPF;

    @FXML
    public TableColumn<Banco, Double> tableColumnSaldo;

    @FXML
    public Button CriarConta;

    @FXML
    public Button RemoverConta;

    @FXML
    public Button Depositar;

    @FXML
    public Button Transferir;

    @FXML
    public Button Sacar;

    @FXML
    public TableView<Banco> tableView;

    @FXML
    private TableColumn<Banco, String> tableColumnBanco;

    public List<Banco> lista = new ArrayList<>();

    public ObservableList<Banco> lista2;

    public MainViewController controller4;

    public MainViewController getController4() {
        return controller4;
    }

    public void setLista(List<Banco> lista) {
        this.lista = lista;
    }

    public void setController4(MainViewController controller4) {
        this.controller4 = controller4;
    }

    @FXML
    public void onBtNewAction(ActionEvent evento) {
        Stage parentStage = Utils.currentStage(evento);
        createDialogForm("CreateAccount.fxml", parentStage);
    }

    public void carregarTableView(Banco bank){
        tableColumnBanco.setCellValueFactory((new PropertyValueFactory<>("nome")));
        tableColumnAgencia.setCellValueFactory((cellData -> new SimpleObjectProperty(cellData.getValue().getContas().get(0).getAgencia())));
        tableColumnCliente.setCellValueFactory((cellData -> new SimpleObjectProperty(cellData.getValue().getContas().get(0).getCliente().getNome())));
        tableColumnCPF.setCellValueFactory((cellData -> new SimpleObjectProperty(cellData.getValue().getContas().get(0).getCPF())));
        tableColumnSaldo.setCellValueFactory((cellData -> new SimpleObjectProperty(cellData.getValue().getContas().get(0).getSaldo())));
        lista.add(bank);
        lista2 = FXCollections.observableArrayList(lista);
        tableView.setItems(lista2);
    }

    public void updateTableView(){
        lista2 = FXCollections.observableArrayList(lista);
        tableView.setItems(lista2);
    }

    private synchronized void createDialogForm(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            ScrollPane pane = loader.load();
            CreateAccountController controller2 = loader.getController();
            Stage dialogStage = new Stage();
            Image imagem = new Image("C:\\Users\\Administrator\\Desktop\\3.png");
            dialogStage.getIcons().add(imagem);
            dialogStage.setTitle("Criar Conta");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
            if (controller2.OutroBanco != null) {
                lista.add(controller2.OutroBanco);
            }
            updateTableView();
        }
        catch (IOException e) {
            Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void onBtRemoveAction(ActionEvent evento){
        Stage parentStage = Utils.currentStage(evento);
        FormRemove("RemoveAccount.fxml", parentStage);
    }

    public void onBtDepositarAction(ActionEvent evento){
        Stage parentStage = Utils.currentStage(evento);
        FormRemove2("DepositarView.fxml", parentStage);
    }

    public void printList(ActionEvent evento){
        for ( Banco ban:
             lista) {
            System.out.println(ban.toString());
        }
    }

    private synchronized void FormRemove(String absoluteName, Stage parentStage) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
                Pane pane = loader.load();
                loader.<RemoveAccountController>getController().controller3 = controller4;
                Stage dialogStage = new Stage();
                Image imagem = new Image("C:\\Users\\Administrator\\Desktop\\3.png");
                dialogStage.getIcons().add(imagem);
                dialogStage.setTitle("Remover Conta");
                dialogStage.setScene(new Scene(pane));
                dialogStage.setResizable(false);
                dialogStage.initOwner(parentStage);
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.showAndWait();
            } catch (Exception e) {
                Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
            }
    }

    private synchronized void FormRemove2(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            loader.<DepositarViewController>getController().controller5 = controller4;
            Stage dialogStage = new Stage();
            Image imagem = new Image("C:\\Users\\Administrator\\Desktop\\3.png");
            dialogStage.getIcons().add(imagem);
            dialogStage.setTitle("Depositar");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (Exception e) {
            Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void onBtTransferir(ActionEvent evento){
        Stage parentStage = Utils.currentStage(evento);
        FormRemove3("TransferirView.fxml", parentStage);
    }

    private synchronized void FormRemove3(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            loader.<TransferirViewController>getController().controller6 = controller4;
            Stage dialogStage = new Stage();
            Image imagem = new Image("C:\\Users\\Administrator\\Desktop\\3.png");
            dialogStage.getIcons().add(imagem);
            dialogStage.setTitle("Transferir");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (Exception e) {
            Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void onBtSacar(ActionEvent evento){
        Stage parentStage = Utils.currentStage(evento);
        FormRemove4("SacarView.fxml", parentStage);
    }

    private synchronized void FormRemove4(String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            loader.<SacarViewController>getController().controller7 = controller4;
            Stage dialogStage = new Stage();
            Image imagem = new Image("C:\\Users\\Administrator\\Desktop\\3.png");
            dialogStage.getIcons().add(imagem);
            dialogStage.setTitle("Sacar");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (Exception e) {
            Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableView(new Banco("Santander", Arrays.asList(new ContaCorrente(19245,"08423781365", "12200.00", new Cliente("Minha Conta")))));
        carregarTableView(new Banco("DioBank", Arrays.asList(new ContaCorrente(26583,"65842366545", "25562200.00", new Cliente("Digital Innovation One")))));
    }
}
