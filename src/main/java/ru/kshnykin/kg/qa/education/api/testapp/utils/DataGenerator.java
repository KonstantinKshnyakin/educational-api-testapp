package ru.kshnykin.kg.qa.education.api.testapp.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomUtils;

@UtilityClass
public class DataGenerator {

    public static final Double MAX_DOUBLE = 100_000.0;
    public static final Integer MAX_INTEGER = 100_000;

    public static Double genPositiveDouble() {
        return RandomUtils.nextDouble(1, MAX_DOUBLE);
    }

    public static Double genNegativeDouble() {
        return -RandomUtils.nextDouble(MAX_DOUBLE, 1);
    }

    public static Double genPositiveDouble(double endExclusive) {
        return RandomUtils.nextDouble(1, endExclusive);
    }

    public static Double genDoubleBetween(double startInclusive, double endExclusive) {
        return RandomUtils.nextDouble(startInclusive, endExclusive);
    }

    public static Integer genPositiveInt() {
        return RandomUtils.nextInt(1, MAX_INTEGER);
    }

    public static Integer genNegativeInt() {
        return -RandomUtils.nextInt(1, MAX_INTEGER);
    }

    public static Integer genPositiveInt(int endExclusive) {
        return RandomUtils.nextInt(1, endExclusive);
    }

    public static Integer genIntBetween(int startInclusive, int endExclusive) {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    }

}