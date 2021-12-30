package ru.kshnykin.kg.qa.education.api.testapp.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EnvironmentConfig {

    @JsonProperty
    private String host;
    @JsonProperty
    private Integer port;
    @JsonProperty
    private String basePath;
    @JsonProperty
    private Map<String, String> users = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getPassword(String username) {
        return this.users.get(username);
    }

    @JsonAnyGetter
    private Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    private void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}