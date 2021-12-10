package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

}
