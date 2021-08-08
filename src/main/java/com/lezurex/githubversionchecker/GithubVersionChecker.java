package com.lezurex.githubversionchecker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lezurex.githubversionchecker.exceptions.NoReleaseFoundException;
import com.lezurex.githubversionchecker.exceptions.RepoNotFoundException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

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
    private final boolean includePreReleases;

    /**
     * @param username Username where the targeted repo is located
     * @param repo Name of the repository on GitHub
     * @param currentVersion The current version running
     * @param includePreReleases Whether pre releases should be tested (default: false)
     */
    public GithubVersionChecker(String username, String repo, ReleaseVersion currentVersion, boolean includePreReleases) {
        this.username = username;
        this.repo = repo;
        this.currentVersion = currentVersion;
        this.includePreReleases = includePreReleases;

        try {
            URL url = new URL(String.format("https://api.github.com/repos/%s/%s", this.username, this.repo));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() != HTTP_OK) {
                throw new RepoNotFoundException(this.username, this.repo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param username Username where the targeted repo is located
     * @param repo Name of the repository on GitHub
     * @param currentVersion The current version running
     */
    public GithubVersionChecker(String username, String repo, ReleaseVersion currentVersion) {
        this(username, repo, currentVersion, false);
    }

    public CheckResult check() {
        String queryURL = "https://api.github.com/repos/%s/%s/releases/latest";
        if (this.includePreReleases)
            queryURL = "https://api.github.com/repos/%s/%s/releases?per_page=1";
        try {
            URL url = new URL(String.format(queryURL, this.username, this.repo));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() != HTTP_OK) throw new NoReleaseFoundException(this.username, this.repo);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            JsonObject releaseData;
            if (this.includePreReleases) {
                JsonArray jsonArray = JsonParser.parseReader(in).getAsJsonArray();
                if (jsonArray.size() == 0) throw new NoReleaseFoundException(this.username, this.repo);
                releaseData = jsonArray.get(0).getAsJsonObject();
            } else
                releaseData = JsonParser.parseReader(in).getAsJsonObject();
            in.close();
            con.disconnect();

            ReleaseVersion githubVersion = new ReleaseVersion(releaseData.get("tag_name").getAsString());
            String pageLink = releaseData.get("html_url").getAsString();
            switch (this.currentVersion.compareTo(githubVersion)) {
                case -1:
                    return new CheckResult(githubVersion, pageLink, VersionState.OUTDATED);
                case 0:
                    return new CheckResult(githubVersion, pageLink, VersionState.UP_TO_DATE);
                case 1:
                    return new CheckResult(githubVersion, pageLink, VersionState.NEWER);
                default:
                    return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
