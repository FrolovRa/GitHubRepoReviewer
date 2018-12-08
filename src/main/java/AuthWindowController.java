import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import okhttp3.Credentials;

public class AuthWindowController {

    @FXML private PasswordField password;

    @FXML private TextField login;

    @FXML private Button submit;

    @FXML public void click(){
       Main.setCredential(Credentials.basic(login.getText(), password.getText()));
       Main.getConnection().getUser(Main.getCredential());
    }

}
