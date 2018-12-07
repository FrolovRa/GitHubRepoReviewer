import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Connection connection;
    private static Stage newWindow;

    static Connection getConnection() {
        return Main.connection;
    }

    static Stage getStage() {
        return Main.newWindow;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent rootMain = FXMLLoader.load(getClass().getResource("fxml/MainWindow.fxml"));
        newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(new Scene(rootMain));


        Parent root = FXMLLoader.load(getClass().getResource("fxml/AuthWindow.fxml"));
        primaryStage.setTitle("Repository Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public static void main(String[] args) {
        connection = new Connection();
        launch(args);
    }
}
