package ru.kshnykin.kg.qa.education.api.testapp.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kshnykin.kg.qa.education.api.testapp.utils.IOHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Configuration {

    private static String envId;
    private static Environments env;
    private static final Map<Environments, EnvironmentConfig> envs = new HashMap<>();
    @JsonIgnore
    private static final Map<String, Object> additionalProperties = new HashMap<>();

    private static final AtomicBoolean IS_CONFIGURED = new AtomicBoolean(false);

    private Configuration() {
    }

    public static void init() {
        if (!IS_CONFIGURED.getAndSet(true)) {
            IOHelper.getYmlFileAsObject("configuration.yml", Configuration.class);
        }
    }

    public static String getEnvId() {
        return envId;
    }

    public static void setEnvId(String envId) {
        Configuration.env = Environments.parse(envId);
        Configuration.envId = envId;
    }

    public static Environments getEnvironment() {
        return env;
    }

    public static void setEnvironment(String envId) {
        Configuration.env = Environments.parse(envId);
        Configuration.envId = envId;
    }

    public static void setEnvironment(Environments env) {
        Configuration.env = env;
        Configuration.envId = env.name();
    }

    public static EnvironmentConfig getEnvironmentConfig(String envId) {
        return envs.get(Environments.parse(envId));
    }

    public static EnvironmentConfig getEnvironmentConfig() {
        return envs.get(getEnvironment());
    }

    public static Map<String, Object> getAdditionalProperties() {
        return Configuration.additionalProperties;
    }

    public static void setAdditionalProperty(String name, Object value) {
        Configuration.additionalProperties.put(name, value);
    }

    @JsonProperty("envId")
    private String _getEnvId() {
        return envId;
    }

    @JsonProperty("envId")
    private void _setEnvId(String envId) {
        Configuration.envId = envId;
        Configuration.env = Environments.parse(envId);
    }

    @JsonProperty("envs")
    private Map<Environments, EnvironmentConfig> _getEnvs() {
        return envs;
    }

    @JsonProperty("envs")
    private void _setEnvs(String envId, EnvironmentConfig env) {
        Configuration.envs.put(Environments.parse(envId), env);
    }

    @JsonAnyGetter
    private Map<String, Object> _getAdditionalProperties() {
        return Configuration.additionalProperties;
    }

    @JsonAnySetter
    private void _setAdditionalProperty(String name, Object value) {
        Configuration.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "additionalProperties=" + additionalProperties +
                ", envId='" + envId + '\'' +
                ", envs=" + envs +
                '}';
    }

}