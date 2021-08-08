package com.lezurex.githubversionchecker;

import com.lezurex.githubversionchecker.exceptions.InvalidVersionString;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class ReleaseVersion implements Comparable<ReleaseVersion>, Serializable {

    private int major = 0;
    private int minor = 0;
    private int patch = 0;

    public ReleaseVersion(int major) {
        this.major = major;
    }

    public ReleaseVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public ReleaseVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public ReleaseVersion(String versionString) {

    }

    private void parseVersionString(String s) {
        final Pattern pattern = Pattern.compile("[vV]?(\\d+)\\.(\\d+)\\.(\\d+)");
        final Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            for (int i = 1; i < matcher.groupCount(); i++) {
                if (i == 1) this.major = Integer.parseInt(matcher.group());
                if (i == 2) this.minor = Integer.parseInt(matcher.group());
                if (i == 3) this.patch = Integer.parseInt(matcher.group());
            }
        } else throw new InvalidVersionString(s);
    }

    @Override
    public int compareTo(ReleaseVersion other) {
        if (this.major > other.major) return 1;
        if (this.major < other.major) return -1;

        if (this.minor > other.minor) return 1;
        if (this.minor < other.minor) return -1;

        if (this.patch > other.patch) return 1;
        if (this.patch < other.patch) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("v%d.%d.%d", major, minor, patch);
    }
}
