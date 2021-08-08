package com.lezurex.githubversionchecker.exceptions;

import lombok.Getter;

@Getter
public class InvalidVersionString extends RuntimeException {

    private final String versionString;

    public InvalidVersionString(String versionString) {
        this.versionString = versionString;
    }

    @Override
    public String getMessage() {
        return String.format("Unable to parse version string: \"%s\"%nPlease report this error if you believe this string should be valid!", versionString);
    }
}
