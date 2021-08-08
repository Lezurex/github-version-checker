# GitHub Version Checker

![GitHub release (latest by date)](https://img.shields.io/github/v/release/Lezurex/github-version-checker)
![GitHub all releases](https://img.shields.io/github/downloads/Lezurex/github-version-checker/total)
![GitHub](https://img.shields.io/github/license/Lezurex/github-version-checker)

A java library for checking for new releases on GitHub

## Download

Gradle:
```gradle
maven {
    url "https://repo.voxcrafter.dev/repository/maven-releases/"
}

dependencies {
    implementation 'com.lezurex.githubversionchecker:1.0.0'
}
```

Maven:
```xml
<dependency>
  <groupId>com.lezurex</groupId>
  <artifactId>github-version-checker</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>

<repository>
  <id>voxcrafter-repo</id>
  <url>https://repo.voxcrafter.dev/repository/maven-releases/</url>
</repository>
```

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