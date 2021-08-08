package com.lezurex.githubversionchecker.exceptions;

import lombok.Getter;

@Getter
public class RepoNotFoundException extends RuntimeException {

    private final String username;
    private final String repo;

    public RepoNotFoundException(String username, String repo) {
        this.username = username;
        this.repo = repo;
    }

    @Override
    public String getMessage() {
        return String.format("Repo \"%s/%s\" couldn't be found on GitHub! Does it exist and is it public?", username, repo);
    }
}
