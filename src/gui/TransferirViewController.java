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

import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class TransferirViewController implements Initializable {

    @FXML
    public Button btCancelar;
    @FXML
    public Button btTransferir;
    @FXML
    public TextField fildPara;
    @FXML
    public TextField fildDe;

    public MainViewController controller6;
    public TextField fildValor;

    public void onBtTransferir(){
        try {
            if (fildDe.getText().length() < 11 && fildDe.getText().length() >= 1
            || fildPara.getText().length() < 11 && fildPara.getText().length() >= 1) {
                throw new Exception();
            }
            if (fildDe.getText() == "" || fildPara.getText() == "" || fildValor.getText() == "") {
                throw new NumberFormatException();
            }
            String cpfDe = fildDe.getText();
            String cpfPara = fildPara.getText();
            Double Valor = Double.parseDouble(fildValor.getText());
            boolean c = false;
            for (Banco banco4 : controller6.lista){
                if (cpfDe.equals(banco4.getContas().get(0).getCPF())){
                    for (Banco banco6 : controller6.lista) {
                        if (cpfPara.equals(banco6.getContas().get(0).getCPF())){
                            if ((banco4.getContas().get(0).getSaldo2() - Valor) < 0){
                                throw new InvalidObjectException("");
                            }
                            banco4.getContas().get(0).sacar(Valor);
                            banco6.getContas().get(0).depositar(Valor);
                            c = true;
                        }
                    }
                }
            }
            if (!c) {
                throw new RuntimeException();
            }
            controller6.tableView.refresh();
            Stage stage = (Stage) btTransferir.getScene().getWindow(); //Obtendo a janela atual
            stage.close();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Error", "Campo Vazio! Preencha todos os campos!", null, Alert.AlertType.ERROR);
        } catch (InvalidObjectException e) {
            Alerts.showAlert("Error", "Saldo Insuficiente!", null, Alert.AlertType.ERROR);
        } catch (RuntimeException e){
            Alerts.showAlert2("Error", "CPF não cadastrado", "Digite um cpf já registrado!", Alert.AlertType.ERROR);
        } catch (Exception e){
            Alerts.showAlert("Error", "O CPF deve conter 11 dígitos!", "Por favor, tente novamente!", Alert.AlertType.ERROR);
        }
    }

    public void onBtCancelar(){
        Stage stage = (Stage) btCancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldCpf(fildDe);
        Constraints.setTextFieldCpf(fildPara);
        Constraints.setTextFieldDouble(fildValor);
    }
}
