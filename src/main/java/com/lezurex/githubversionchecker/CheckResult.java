package com.lezurex.githubversionchecker;

import lombok.Getter;

@Getter
public class CheckResult {

    private ReleaseVersion version;
    private String pageLink;
    private VersionState versionState;

    public CheckResult(ReleaseVersion version, String pageLink, VersionState versionState) {
        this.version = version;
        this.pageLink = pageLink;
        this.versionState = versionState;
    }
}
