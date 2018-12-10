import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

                                    Main.newWindow = new Stage();
                                    Scene scene =  new Scene(root);
                                    scene.setOnKeyPressed(event -> {
                                        if(event.getCode() == KeyCode.F5){

                                            Main.controller.tableView.getItems().clear();
                                            Main.getConnection().getRepos(Main.getCredential());
                                            System.out.println("F5 was pressed");
                                        }
                                    });
                                    Main.newWindow.setTitle("Repository Viewer");
                                    Main.newWindow.setScene(scene);
                                    Main.newWindow.getIcons().add(new Image("Images/cloud.png"));
//                                    ContextMenu cm = new ContextMenu();
//                                    MenuItem mi1 = new MenuItem("Copy the URL");
//
//                                    mi1.setOnAction((ActionEvent event) -> {
//                                        System.out.println("Menu item 1");
//                                        String item = Main.controller.tableView.getSelectionModel().getSelectedItem().getUrl();
//                                        if(Main.controller.tableView.getSelectionModel().getSelectedItem() != null) {
//                                            System.out.println("Selected item: " + item);
//                                        }
//
//                                    });
//
//                                    ContextMenu menu = new ContextMenu();
//                                    menu.getItems().add(mi1);
//                                    Main.controller.tableView.setContextMenu(menu);

//                                    Main.controller.tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//
//                                        @Override
//                                        public void handle(MouseEvent t) {
//                                            if(t.getButton() == MouseButton.SECONDARY) {
//                                                cm.show(Main.controller.tableView, t.getScreenX(), t.getScreenY());
//                                                System.out.println("ok");
//                                            }
//                                        }
//                                    });

                                    Main.newWindow.show();
                                    Main.authWindow.close();

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
