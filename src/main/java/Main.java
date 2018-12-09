import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Connection connection;
    static MainWindowController controller;
    static User user;
    private static String credential;
    static Stage authWindow;
    static Stage newWindow;

    static void setCredential(String cred) {
        credential = cred;
    }

    static String getCredential() {
        return credential;
    }

    static Connection getConnection() {
        return Main.connection;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("fxml/AuthWindow.fxml"));
        primaryStage.setTitle("Repository Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        authWindow = primaryStage;

//        String credential = Credentials.basic("FrolovRa", "f7RvWUwvH8NnKWB");
//        Main.getConnection().getRepos(credential);
    }


    public static void main(String[] args) {
        connection = new Connection();
        launch(args);
    }
}
