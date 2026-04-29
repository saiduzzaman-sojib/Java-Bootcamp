package som.sojib.sojibsir;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    public TextField nameTextField;

    @FXML
    public TextField emailTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public void nameTextFieldPrintEvent() {
        System.out.println("--- Registration Info ---");

        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }
}