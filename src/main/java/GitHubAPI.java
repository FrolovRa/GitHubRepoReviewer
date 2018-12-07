import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

public interface GitHubAPI {
    @GET("user/repos")
    Call<List<Repository>> getUserRepositories(@Header("Authorization") String credentials);

    @GET("user")
    Call<User> getUser(@Header("Authorization") String credentials);
}

