package com.lezurex.githubversionchecker.examples;

import com.lezurex.githubversionchecker.CheckResult;
import com.lezurex.githubversionchecker.GithubVersionChecker;
import com.lezurex.githubversionchecker.ReleaseVersion;

public class Example {

    public static void main(String[] args) {
        ReleaseVersion currentVersion = new ReleaseVersion("v1.0.0");
        GithubVersionChecker versionChecker = new GithubVersionChecker("Lezurex", "github-version-checker", currentVersion);

        CheckResult result = versionChecker.check();
        switch (result.getVersionState()) {
            case NEWER:
                System.out.println("You are on a development branch, aren't you?");
                break;
            case OUTDATED:
                System.out.printf("Your current version is outdated! %s is available here: %s", result.getVersion(), result.getPageLink());
                break;
            case UP_TO_DATE:
                System.out.println("Your current version is up to date!");
                break;
        }

    }

}
