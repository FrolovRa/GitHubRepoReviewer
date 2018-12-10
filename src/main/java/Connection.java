import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class Connection {

    private GitHubAPI gitHub;
    private static Connection connection = new Connection();
    private User user;

    public User getUser() {
        return user;
    }

    public static Connection getConnection() {
        return connection;
    }

    private Connection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

         gitHub = retrofit.create(GitHubAPI.class);
    }

    void requestUser(String credential) {

        Call<User> call = gitHub.getUser(credential);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    System.out.println(response);
                    if (response.body() != null){
                        user = response.body();
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                try{
                                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml/MainWindow.fxml"));
                                    Parent root = fxml.load();
                                    Main.setController(fxml.getController());

                                    requestRepos(Main.getCredential());

                                    Main.setMainWindow(new Stage());
                                    Scene scene =  new Scene(root);
                                    scene.setOnKeyPressed(event -> {
                                        if(event.getCode() == KeyCode.F5){

                                            Main.getController().tableView.getItems().clear();
                                            Connection.getConnection().requestRepos(Main.getCredential());
                                            System.out.println("F5 was pressed");
                                        }
                                    });
                                    Main.getMainWindow().setTitle("Repository Viewer");
                                    Main.getMainWindow().setScene(scene);
                                    Main.getMainWindow().getIcons().add(new Image("Images/cloud.png"));
//
                                    Main.getMainWindow().show();
                                    Main.getAuthWindow().close();

                                } catch (Exception e) {
                                    System.out.println(e);}
                            }
                        });
                    }
                } else {
                    System.out.println("error");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("connection error");
            }
        });
    }

    void requestRepos(String credential) {

        Call<List<Repository>> call = gitHub.getUserRepositories(credential);

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    System.out.println(response);
                    if (response.body() != null){
                        if(Main.getController() != null) Main.getController().tableView.getItems().addAll(response.body());
                    }
                } else {
                    System.out.println("error");
                }
            }
            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                System.out.println("connection error" + t);
            }
        });
    }
}
