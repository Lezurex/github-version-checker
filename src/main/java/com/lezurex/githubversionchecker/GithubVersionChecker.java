package com.lezurex.githubversionchecker;

public class GithubVersionChecker {

    /**
     * Username where the targeted repo is located
     */
    private final String username;
    /**
     * Name of the repository on GitHub
     */
    private final String repo;

    /**
     * @param username Username where the targeted repo is located
     * @param repo Name of the repository on GitHub
     */
    public GithubVersionChecker(String username, String repo) {
        this.username = username;
        this.repo = repo;
    }
}
