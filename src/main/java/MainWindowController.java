import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainWindowController {

    @FXML public TableView<Repository> tableView;

    @FXML public ImageView avatar;

    @FXML public Label userName;

    @FXML public Label login;

    @FXML public TextField searchField;

    @FXML public Button exitButton;

    @FXML public Button searchButton;

    @FXML public void search(){
        for (Repository r:Main.controller.tableView.getItems()) {
            if (searchField.getText().equals(r.getName())){
                System.out.println("find Repository");
            }
        }
    }

    @FXML
    public void initialize() {
        avatar.setImage(new Image(Main.user.getAvatar_url()));
        userName.setText(Main.user.getName());
        login.setText(Main.user.getLogin());
    }

}
