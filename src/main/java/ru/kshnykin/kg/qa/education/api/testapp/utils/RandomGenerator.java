package ru.kshnykin.kg.qa.education.api.testapp.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

@UtilityClass
@SuppressWarnings("unused")
public class RandomGenerator {

    public static final Double MAX_DOUBLE = 1_000_000.0;
    public static final Integer MAX_INTEGER = 1_000_000;
    public static final Long MAX_LONG = 1_000_000L;

    public static Double genPositiveDouble() {
        return genDoubleBetween(1, MAX_DOUBLE);
    }

    public static Double genNegativeDouble() {
        return -genDoubleBetween(MAX_DOUBLE, 1);
    }

    public static Double genPositiveDouble(double endExclusive) {
        return genDoubleBetween(1, endExclusive);
    }

    public static Double genDoubleBetween(double startInclusive, double endExclusive) {
        return RandomUtils.nextDouble(startInclusive, endExclusive);
    }

    public static Integer genPositiveInt() {
        return genIntBetween(1, MAX_INTEGER);
    }

    public static Integer genNegativeInt() {
        return -genIntBetween(1, MAX_INTEGER);
    }

    public static Integer genPositiveInt(int endExclusive) {
        return genIntBetween(1, endExclusive);
    }

    public static Integer genIntBetween(int startInclusive, int endExclusive) {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    }

    public static Long genPositiveLong() {
        return genLongBetween(1, MAX_LONG);
    }

    public static Long genNegativeLong() {
        return -genLongBetween(1, MAX_LONG);
    }

    public static Long genPositiveLong(long endExclusive) {
        return genLongBetween(1, endExclusive);
    }

    public static Long genLongBetween(long startInclusive, long endExclusive) {
        return RandomUtils.nextLong(startInclusive, endExclusive);
    }

    public static String genAlphabeticStr(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

}