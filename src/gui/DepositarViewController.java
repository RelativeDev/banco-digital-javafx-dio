package gui;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Banco;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class DepositarViewController implements Initializable {

    public TextField fildCPF2;
    public TextField fildValor2;
    public Button btCancel;
    public Button btDepositar;

    public MainViewController controller5;

    public void onButtonDepositar(){
        try {
            boolean b = false;
            if (fildCPF2.getText().length() < 11 && fildCPF2.getText().length() >= 1) {
                throw new Exception();
            }
            if (fildCPF2.getText() == "") {
                throw new NumberFormatException();
            }
            String cpfRemove2 = fildCPF2.getText();
            Double saldo3 = Double.parseDouble(fildValor2.getText());
            for (Banco banco: controller5.lista) {
                if (cpfRemove2.equals(banco.getContas().get(0).getCPF())) {
                    banco.getContas().get(0).depositar(saldo3);
                    b = true;
                }
            }
            if (!b) {
                throw new RuntimeException();
            }
            controller5.tableView.refresh();
            Stage stage = (Stage) btDepositar.getScene().getWindow(); //Obtendo a janela atual
            stage.close();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Error", "Campo Vazio! Preencha todos os campos!", null, Alert.AlertType.ERROR);
        }catch (RuntimeException e){
            Alerts.showAlert2("Error", "CPF não cadastrado", null, Alert.AlertType.ERROR);
        } catch (Exception e){
            Alerts.showAlert("Error", "O CPF deve conter 11 dígitos!", "Por favor, tente novamente!", Alert.AlertType.ERROR);
        }
    }

    public void cancelButton(ActionEvent evento){
        Stage stage = (Stage) btCancel.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldCpf(fildCPF2);
        Constraints.setTextFieldDouble(fildValor2);
    }
}
