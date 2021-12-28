package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    private Object id;
    private Category category;
    private Object name;
    private List<Object> photoUrls;
    private List<Tag> tags;
    private Object status;

    @Data
    public static class PetStatus {

        public static final String AVAILABLE = "available";
        public static final String PENDING = "pending";
        public static final String SOLD = "sold";

        private String status;

    }

}
