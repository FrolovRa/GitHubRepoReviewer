import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface GitHubAPI {
    @GET("user/repos")
    Call<List<Repository>> getUserRepositories();
}

