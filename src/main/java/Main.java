import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static MainWindowController controller;
    private static String credential;
    private static Stage authWindow;
    private static Stage mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("fxml/AuthWindow.fxml"));
        primaryStage.setTitle("Repository Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("Images/cloud.png"));
        primaryStage.show();
        authWindow = primaryStage;
        //f7RvWUwvH8NnKWB
    }

    public static void main(String[] args) {
        launch();
    }

    static MainWindowController getController() {
        return controller;
    }

    static void setController(MainWindowController controller) {
        Main.controller = controller;
    }

    static Stage getAuthWindow() {
        return authWindow;
    }

    static Stage getMainWindow() {
        return mainWindow;
    }

    static void setMainWindow(Stage mainWindow) {
        Main.mainWindow = mainWindow;
    }

    static void setCredential(String cred) {
        credential = cred;
    }

    static String getCredential() {
        return credential;
    }

}
