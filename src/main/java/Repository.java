        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

    public class Repository {

        @SerializedName("name")
        @Expose private String name;

        @SerializedName("description")
        @Expose private String description;

        @SerializedName("fork_count")
        @Expose private Integer fork_count;

        @SerializedName("stargazers_count")
        @Expose private Integer stargazers_count;

        @SerializedName("watchers_count")
        @Expose private Integer watchers_count;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Integer getFork_count() {
            return fork_count;
        }

        public Integer getStargazers_count() {
            return stargazers_count;
        }

        public Integer getWatchers_count() {
            return watchers_count;
        }
    }
