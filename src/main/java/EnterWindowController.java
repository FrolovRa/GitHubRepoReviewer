import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EnterWindowController {
    private int a = 0;

    @FXML private static PasswordField password;

    @FXML private static TextField login;

    @FXML private Button submit;

    @FXML public void click(ActionEvent actionEvent) {
        a++;
        submit.setText("" + a);
    }

    public static String getPassword() {
        return password.getText();
    }

    public static String getLogin() {
        return login.getText();
    }
}
