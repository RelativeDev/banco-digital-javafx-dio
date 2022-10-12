package gui.util;

import javafx.scene.control.TextField;

public class Constraints {

    public static void setTextFieldInteger(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null  && !newValue.matches("\\d{0,6}")) {
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldMaxLength(TextField txt, int max) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue.length() > max) {
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldDouble(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d{0,12}(\\.\\d{0,2})?")) {
                txt.setText(oldValue);
            }
        });
    }

    public static void setTextFieldCpf(TextField txt) {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d{0,11}")) {
                txt.setText(oldValue);
            }
        });
    }

}
