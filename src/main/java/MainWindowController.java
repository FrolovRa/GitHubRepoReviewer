import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class MainWindowController {

    @FXML public TableView<Repository> tableView;

    @FXML public ImageView avatar;

    @FXML public Label userName;

    @FXML public Label login;

    @FXML public TextField searchField;

    @FXML public Button exitButton;

    @FXML public Button searchButton;

    @FXML public TableColumn numbers;

    @FXML public void search() {
        for (Repository r:this.tableView.getItems()) {
            if (searchField.getText().toLowerCase().equals(r.getName().toLowerCase())){
                System.out.println("find Repository");
                //add later rxJava search
            }
        }
    }

    @FXML public void logout() {
        Main.setCredential("");
        Main.authWindow.show();
        Main.newWindow.close();
    }

    @FXML
    public void initialize() {
        numbers.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Repository, Repository>, ObservableValue<Repository>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

        numbers.setCellFactory(new Callback<TableColumn<Repository, Repository>, TableCell<Repository, Repository>>() {
            @Override public TableCell<Repository, Repository> call(TableColumn<Repository, Repository> param) {
                return new TableCell<Repository, Repository>() {
                    @Override protected void updateItem(Repository item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex()+ 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        avatar.setImage(new Image(Main.user.getAvatar_url()));
        userName.setText(Main.user.getName());
        login.setText(Main.user.getLogin());
    }

}
