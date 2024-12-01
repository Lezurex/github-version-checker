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
            new GithubVersionChecker("Lezurex", "github-version-checker", releaseVersion);
        }, "Repo validation failed!");
    }

    @Test
    @DisplayName("Setup with invalid repo")
    void setupInvalidRepo() {
        ReleaseVersion releaseVersion = new ReleaseVersion("0.1.1");
        assertThrows(RepoNotFoundException.class,
                () -> new GithubVersionChecker("Lezurex", "doesnotexist", releaseVersion),
                "Repo validation di not fail!");
    }

    @Test
    @DisplayName("Check with outdated version")
    void checkOutdated() {
        ReleaseVersion releaseVersion = new ReleaseVersion("0.0.1");
        GithubVersionChecker githubVersionChecker =
                new GithubVersionChecker("VoxCrafterLP", "JumpRace", releaseVersion);
        CheckResult checkResult = githubVersionChecker.check();
        assertEquals(VersionState.OUTDATED, checkResult.getVersionState(),
                "Version state is not outdated!");
        assertTrue(checkResult.getPageLink().endsWith(checkResult.getVersion().toString()),
                "End of release URL does not match version!");
    }

}
