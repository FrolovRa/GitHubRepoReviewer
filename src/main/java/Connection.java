import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    Connection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

         gitHub = retrofit.create(GitHubAPI.class);
    }

    void getUser(String credential) {

        Call<User> call = gitHub.getUser(credential);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    System.out.println(response);
                    if (response.body() != null){
                        Main.user = response.body();
                        System.out.println(response.body().getAvatar_url()+" "+response.body().getName());
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                try{
                                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml/MainWindow.fxml"));
                                    Parent root = fxml.load();
                                    Main.controller = fxml.getController();

                                    getRepos(Main.getCredential());

                                    Stage newWindow = new Stage();
                                    newWindow.setTitle("Repository Viewer");
                                    newWindow.setScene(new Scene(root));
                                    newWindow.show();

                                } catch (Exception e) {System.out.println(e);}
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

    void getRepos(String credential) {

        Call<List<Repository>> call = gitHub.getUserRepositories(credential);

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    System.out.println(response);
                    if (response.body() != null){
//                        System.out.println(Main.controller.tableView.getItems());
                        if(Main.controller != null) Main.controller.tableView.getItems().addAll(response.body());
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
