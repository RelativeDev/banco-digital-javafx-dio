package gui;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Banco;
import model.entities.Cliente;
import model.services.ContaCorrente;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    private List<Banco> bank;

    public Banco OutroBanco;

    @FXML
    public Button btCancelar;

    @FXML
    public Button btSalvar;

    @FXML
    public TextField fildSaldo;

    @FXML
    public TextField fildCpf;

    @FXML
    public TextField fildAgencia;

    @FXML
    public TextField fildCliente;

    @FXML
    public TextField fildBanco;

    public List<Banco> getBank() {
        return bank;
    }

    public void onButtonSalvarAction(ActionEvent event){
        try {
            if (fildBanco.getText() == "" || fildCliente.getText() == ""){
                throw new NumberFormatException();
            }
            String[] array = fildBanco.getText().split(" ");
            String[] array2 = fildCliente.getText().split(" ");
            if (array.length < 1 || array2.length < 1){
                throw new NumberFormatException();
            }
            String banco = fildBanco.getText();
            String cliente = fildCliente.getText();
            int agencia = Integer.parseInt(fildAgencia.getText());
            if (fildCpf.getText().length() < 11 && fildCpf.getText().length() >= 1){
                throw new Exception("Error");
            }
            double saldo = Double.parseDouble(fildSaldo.getText());
            String cpf = String.format("%011d", Long.parseLong(fildCpf.getText()));
            OutroBanco = new Banco(banco, Arrays.asList(new ContaCorrente(agencia, cpf, String.format("%.2f", saldo).replace(',', '.'), new Cliente(cliente))));
            Stage stage = (Stage) btSalvar.getScene().getWindow(); //Obtendo a janela atual
            stage.close();
        } catch(NumberFormatException e){
            Alerts.showAlert("Error", "Campo Vazio! Preencha todos os campos!", null, Alert.AlertType.ERROR);
        } catch (Exception e) {
            Alerts.showAlert("Error", "O CPF deve conter 11 dígitos!", null, Alert.AlertType.ERROR);
        }
    }

    public void onButtonCancelarAction(ActionEvent event){
        Stage stage = (Stage) btCancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldInteger(fildAgencia);
        Constraints.setTextFieldMaxLength(fildBanco, 25);
        Constraints.setTextFieldMaxLength(fildCliente, 35);
        Constraints.setTextFieldDouble(fildSaldo);
        Constraints.setTextFieldCpf(fildCpf);
    }
}
