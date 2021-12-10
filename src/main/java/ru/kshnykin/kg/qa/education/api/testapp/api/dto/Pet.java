package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Pet {

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @Data
    public static class PetStatus {

        public static final String AVAILABLE = "available";
        public static final String PENDING = "pending";
        public static final String SOLD = "sold";

        private String status;

    }

}
