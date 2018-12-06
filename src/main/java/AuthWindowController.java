import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import java.util.Objects;

public class AuthWindowController {
    private int a = 0;
    private final String API_URL = "https://api.github.com/";

    @FXML private PasswordField password;

    @FXML private TextField login;

    @FXML private Button submit;

    @FXML public void click(ActionEvent actionEvent) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    if (response.request().header("Authorization") != null) {
                        return null; // Give up, we've already failed to authenticate.
                    }
                    String credential = Credentials.basic(login.getText(), password.getText());
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                })
                .build();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient);

        Retrofit retrofit = builder.build();
        GitHubAPI github = retrofit.create(GitHubAPI.class);
//      GitHubAPI loginService = createService(AuthWindowController.getLogin(), AuthWindowController.getPassword());
        Call<User> call = github.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if (response.isSuccessful()) {
                    System.out.println(response);

                    if (Objects.requireNonNull(response.body()) != null){
                            System.out.println(response.body().getAvatar_url()+" "+response.body().getName());
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

}
