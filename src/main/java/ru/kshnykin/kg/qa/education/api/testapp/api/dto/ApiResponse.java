package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiResponse {

    private Integer code;
    private String type;
    private String message;

}
