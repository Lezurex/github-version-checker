# GitHub Version Checker

![GitHub release (latest by date)](https://img.shields.io/github/v/release/Lezurex/github-version-checker)
![GitHub all releases](https://img.shields.io/github/downloads/Lezurex/github-version-checker/total)
![GitHub](https://img.shields.io/github/license/Lezurex/github-version-checker)

A java library for checking for new releases on GitHub

## Download

This library is published as `com.lezurex.github-version-checker` on GitHub
Packages. Take a look at [this](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package)
to see how to configure the GitHub Package Registry for your project.

## Usage

```java
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
```
