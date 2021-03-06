package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long petId;
    private Integer quantity;
    private String shipDate;
    private OrderStatus status;
    private Boolean complete;

    public static class OrderStatus {

        public static final String PLACED = "placed";
        public static final String APPROVED = "approved";
        public static final String DELIVERED = "delivered";

        private String status;

    }

}
