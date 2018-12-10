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

        @SerializedName("url")
        @Expose private String url;

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public String getUrl() {return url;}

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
