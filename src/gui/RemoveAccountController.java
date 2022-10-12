package gui;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveAccountController implements Initializable {

    public String cpfRemove;

    public MainViewController controller3;

    @FXML
    public TextField fildCPF;
    @FXML
    public Button btRemover;
    @FXML
    public Button btCancelar;

    public void onButtonRemover(){
        try {
            if (fildCPF.getText().length() < 11 && fildCPF.getText().length() >= 1) {
                throw new Exception();
            }
            if (fildCPF.getText() == "") {
                throw new NumberFormatException();
            }
            cpfRemove = fildCPF.getText();
            boolean bo = controller3.lista.removeIf(s -> s.getContas().get(0).getCPF().equalsIgnoreCase(cpfRemove));
            if (bo) {
                controller3.updateTableView();
            } else {
                throw new RuntimeException();
            }
            Stage stage = (Stage) btCancelar.getScene().getWindow(); //Obtendo a janela atual
            stage.close();
        } catch (NumberFormatException e) {
            Alerts.showAlert("Error", "Campo Vazio! Digite o cpf!", null, Alert.AlertType.ERROR);
        }catch (RuntimeException e){
            Alerts.showAlert2("Error", "CPF não cadastrado", null, Alert.AlertType.ERROR);
        } catch (Exception e){
            Alerts.showAlert("Error", "O CPF deve conter 11 dígitos!", "Por favor, tente novamente!", Alert.AlertType.ERROR);
        }
    }

    public void onButtonCancel(){
        Stage stage = (Stage) btCancelar.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldCpf(fildCPF);
    }
}
