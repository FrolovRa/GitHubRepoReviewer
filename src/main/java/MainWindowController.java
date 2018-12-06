import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MainWindowController {

    @FXML private ImageView avatar;

    @FXML public Label userName;

    @FXML public Label login;

    @FXML public TextField searchField;

    @FXML public Button exitButton;

    private static User user;

    public static void setUser(User u) {
        MainWindowController.user = u;
    }

}
