package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;

@Data
public class Order {

    private Integer id;
    private Integer petId;
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
