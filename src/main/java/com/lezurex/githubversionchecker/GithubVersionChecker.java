package com.lezurex.githubversionchecker;

import com.lezurex.githubversionchecker.exceptions.RepoNotFoundException;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GithubVersionChecker {

    /**
     * Username where the targeted repo is located
     */
    private final String username;
    /**
     * Name of the repository on GitHub
     */
    private final String repo;
    private final ReleaseVersion currentVersion;

    /**
     * @param username Username where the targeted repo is located
     * @param repo Name of the repository on GitHub
     * @param currentVersion The current version running
     */
    public GithubVersionChecker(String username, String repo, ReleaseVersion currentVersion) {
        this.username = username;
        this.repo = repo;
        this.currentVersion = currentVersion;

        try {
            URL url = new URL(String.format("https://api.github.com/repos/%s/%s", this.username, this.repo));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RepoNotFoundException(this.username, this.repo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
