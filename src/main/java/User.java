import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.beans.property.SimpleStringProperty;

public class User {
    @SerializedName("name")
    @Expose private String name;

    @SerializedName("avatar_url")
    @Expose private String avatar_url;

    @SerializedName("login")
    @Expose private String login;

    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }
}
