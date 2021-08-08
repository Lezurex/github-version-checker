package com.lezurex.githubversionchecker.exceptions;

import lombok.Getter;

@Getter
public class NoReleaseFoundException extends RuntimeException {

    private final String username;
    private final String repo;

    public NoReleaseFoundException(String username, String repo) {
        this.username = username;
        this.repo = repo;
    }

    @Override
    public String getMessage() {
        return String.format("Couldn't find an existing release in repo \"%s/%s\"! If you are the maintainer of this application, please create a release!", username, repo);
    }
}
