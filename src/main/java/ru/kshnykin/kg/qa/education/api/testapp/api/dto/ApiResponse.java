package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private Integer code;
    private String type;
    private String message;

}
