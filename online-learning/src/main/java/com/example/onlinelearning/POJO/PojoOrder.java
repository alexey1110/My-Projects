package com.example.onlinelearning.POJO;

import com.example.onlinelearning.Entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PojoOrder {
    private Long orderId;
    private Date orderDate;
    private Long userId;
    private Long courseId;

    public static PojoOrder fromEntity(Order order) {
        var pojo = new PojoOrder();
        pojo.setOrderId(order.getOrderId());
        pojo.setOrderDate(order.getOrderDate());
        pojo.setUserId(order.getUserId()); // Устанавливаем userId из сущности Order
        pojo.setCourseId(order.getCourseId());
        return pojo;
    }

    public static Order toEntity(PojoOrder pojoOrder) {
        var order = new Order();
        order.setOrderId(pojoOrder.getOrderId());
        order.setOrderDate(pojoOrder.getOrderDate());

        return order;
    }
}