import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

//    static void inits(){
//        String API_URL = "https://api.github.com/";
//
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .authenticator((route, response) -> {
//                    if (response.request().header("Authorization") != null) {
//                        return null; // Give up, we've already failed to authenticate.
//                    }
//                    String credential = Credentials.basic(AuthWindowController.getLogin(), AuthWindowController.getPassword());
//                    return response.request().newBuilder()
//                            .header("Authorization", credential)
//                            .build();
//                })
//                .build();
//
//        Retrofit.Builder builder =
//                new Retrofit.Builder()
//                        .baseUrl(API_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .client(httpClient);
//
//        Retrofit retrofit = builder.build();
//        GitHubAPI github = retrofit.create(GitHubAPI.class);
////      GitHubAPI loginService = createService(AuthWindowController.getLogin(), AuthWindowController.getPassword());
//        Call<List<Repository>> call = github.getUserRepositories();
////        call.enqueue(new Callback<List<Repository>>() {
////
////        @Override
////        public void onResponse(Call<List<Repository>> call, retrofit2.Response<List<Repository>> response) {
////            if (response.isSuccessful()) {
////                System.out.println(response);
////                System.out.println(String.valueOf(response.body().get(1).getName()));
////                System.out.println(String.valueOf(response.body().get(2).getName()));
////                System.out.println(String.valueOf(response.body().get(0).getName()));
////            } else {
////                System.out.println("error");
////            }
////        }
////            @Override
////        public void onFailure(Call<List<Repository>> call, Throwable t) {
////                System.out.println("connection error");
////        }
////    });
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent rootMain = FXMLLoader.load(getClass().getResource("fxml/MainWindow.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(new Scene(rootMain));
        newWindow.show();

        Parent root = FXMLLoader.load(getClass().getResource("fxml/AuthWindow.fxml"));
        primaryStage.setTitle("Repository Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
