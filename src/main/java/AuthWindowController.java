import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.Credentials;

public class AuthWindowController {
    private String credential;


    @FXML private PasswordField password;

    @FXML private TextField login;

    @FXML private Button submit;

    @FXML public void click() {
        credential = Credentials.basic(login.getText(), password.getText());

       Main.getConnection().getUser(credential);
       Main.getConnection().getRepos(credential);
    }

}
