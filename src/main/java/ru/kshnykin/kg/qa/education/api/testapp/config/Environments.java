package ru.kshnykin.kg.qa.education.api.testapp.config;

public enum Environments {

    DEV,
    TEST,
    PROD;

    public static Environments parse(String env) {
        if (env == null || env.isBlank()) {
            throw new IllegalArgumentException("Параметр env не может быть пустой или null");
        }
        env = env.trim();
        if (env.equalsIgnoreCase("d")
                || env.equalsIgnoreCase("development")
                || env.equalsIgnoreCase("develop")) {
            return DEV;
        }
        if (env.equalsIgnoreCase("t") || env.equalsIgnoreCase("test")) {
            return TEST;
        }
        if (env.equalsIgnoreCase("p")
                || env.equalsIgnoreCase("prod")
                || env.equalsIgnoreCase("production")) {
            return PROD;
        }
        return DEV;
    }

}