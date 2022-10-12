package gui;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Banco;

import java.io.InvalidObjectException;
import java.net.URL;
import java.util.ResourceBundle;

public class SacarViewController implements Initializable {
    public Button btCancelar;
    public Button btSacar;
    public TextField fildValor;
    public TextField fildSacar;

    public MainViewController controller7;

    public void onBtSacar(){
        try {
            boolean d = false;
            if (fildSacar.getText().length() < 11 && fildSacar.getText().length() >= 1) {
                throw new Exception();
            }
            if (fildSacar.getText() == "") {
                throw new NumberFormatException();
            }
            String cpfRemove2 = fildSacar.getText();
            Double saldo3 = Double.parseDouble(fildValor.getText());
            for (Banco banco : controller7.lista) {
                if (cpfRemove2.equals(banco.getContas().get(0).getCPF())) {
                    if ((banco.getContas().get(0).getSaldo2() - saldo3 < 0)){
                        throw new InvalidObjectException("");
                    }
                    banco.getContas().get(0).sacar(saldo3);
                    d = true;
                }
            }
            if (!d) {
                throw new RuntimeException();
            }
            controller7.tableView.refresh();
            Stage stage = (Stage) btSacar.getScene().getWindow(); //Obtendo a janela atual
            stage.close();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Error", "Campo Vazio! Preencha todos os campos!", null, Alert.AlertType.ERROR);
        }catch (InvalidObjectException e) {
            Alerts.showAlert("Error", "Saldo Insuficiente!", null, Alert.AlertType.ERROR);
        } catch (RuntimeException e){
            Alerts.showAlert2("Error", "CPF não cadastrado", null, Alert.AlertType.ERROR);
        }catch (Exception e){
            Alerts.showAlert("Error", "O CPF deve conter 11 dígitos!", "Por favor, tente novamente!", Alert.AlertType.ERROR);
    }
    }

    public void onBtCancelar(){
        Stage stage = (Stage) btCancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldCpf(fildSacar);
        Constraints.setTextFieldDouble(fildValor);
    }
}
