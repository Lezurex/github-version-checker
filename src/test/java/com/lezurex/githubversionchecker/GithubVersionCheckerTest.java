package com.lezurex.githubversionchecker;

import com.lezurex.githubversionchecker.exceptions.RepoNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GithubVersionCheckerTest {

    @Test
    @DisplayName("Setup with valid repo")
    void setupValidRepo() {
        ReleaseVersion releaseVersion = new ReleaseVersion("0.1.1");
        assertDoesNotThrow(() -> {
            GithubVersionChecker versionChecker = new GithubVersionChecker("Lezurex", "github-version-checker", releaseVersion);
        }, "Repo validation failed!");
    }

    @Test
    @DisplayName("Setup with invalid repo")
    void setupInvalidRepo() {
        ReleaseVersion releaseVersion = new ReleaseVersion("0.1.1");
        assertThrows(RepoNotFoundException.class, () -> new GithubVersionChecker("Lezurex", "doesnotexist", releaseVersion), "Repo validation di not fail!");
    }

}