package com.lezurex.githubversionchecker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReleaseVersionTest {

    @Test
    @DisplayName("Major greater")
    void majorGreater() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(2);
        v2 = new ReleaseVersion(1);
        assertEquals(1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(10, 2);
        v2 = new ReleaseVersion(9, 99);
        assertEquals(1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(5, 3, 10);
        v2 = new ReleaseVersion(4, 89, 4);
        assertEquals(1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Major smaller")
    void majorSmaller() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(1);
        v2 = new ReleaseVersion(2);
        assertEquals(-1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(9, 99);
        v2 = new ReleaseVersion(10, 2);
        assertEquals(-1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(4, 89, 4);
        v2 = new ReleaseVersion(5, 3, 10);
        assertEquals(-1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Major equals")
    void majorEquals() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(1);
        v2 = new ReleaseVersion(1);
        assertEquals(0, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(9, 99);
        v2 = new ReleaseVersion(9, 99);
        assertEquals(0, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(4, 89, 4);
        v2 = new ReleaseVersion(4, 89, 4);
        assertEquals(0, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Minor greater")
    void minorGreater() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(1, 3);
        v2 = new ReleaseVersion(1, 1);
        assertEquals(1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(10, 99, 10);
        v2 = new ReleaseVersion(10, 2, 10);
        assertEquals(1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Minor smaller")
    void minorSmaller() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(1, 1);
        v2 = new ReleaseVersion(1, 3);
        assertEquals(-1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(10, 2, 10);
        v2 = new ReleaseVersion(10, 99, 10);
        assertEquals(-1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Minor equals")
    void minorEquals() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(1, 3);
        v2 = new ReleaseVersion(1, 3);
        assertEquals(0, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));

        v1 = new ReleaseVersion(9, 99, 10);
        v2 = new ReleaseVersion(9, 99, 10);
        assertEquals(0, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Patch greater")
    void patchGreater() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(10, 99, 12);
        v2 = new ReleaseVersion(10, 99, 10);
        assertEquals(1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Patch smaller")
    void patchSmaller() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(10, 99, 10);
        v2 = new ReleaseVersion(10, 99, 12);
        assertEquals(-1, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

    @Test
    @DisplayName("Patch equals")
    void patchEquals() {
        ReleaseVersion v1;
        ReleaseVersion v2;

        v1 = new ReleaseVersion(9, 99, 10);
        v2 = new ReleaseVersion(9, 99, 10);
        assertEquals(0, v1.compareTo(v2), String.format("%s vs %s failed", v1, v2));
    }

}