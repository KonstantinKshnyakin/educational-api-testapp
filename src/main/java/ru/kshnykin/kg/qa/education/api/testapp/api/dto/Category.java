package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Category {

    private Integer id;
    private String name;

}