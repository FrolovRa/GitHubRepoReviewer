        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        public class Repository {

        @SerializedName("name")
        @Expose private String name;

        @SerializedName("description")
        @Expose private String description;

        @SerializedName("forks_count")
        @Expose private Integer forkCount;

        @SerializedName("stargazers_count")
        @Expose private Integer stargazersCount;

        @SerializedName("watchers_count")
        @Expose private Integer watchersCount;

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public Integer getForkCount() {
                return forkCount;
            }

            public Integer getStargazersCount() {
                return stargazersCount;
            }

            public Integer getWatchersCount() {
                return watchersCount;
            }
        }
