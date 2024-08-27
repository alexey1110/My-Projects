package com.example.onlinelearning.Service;

import com.example.onlinelearning.Entity.Course;
import com.example.onlinelearning.Entity.Order;
import com.example.onlinelearning.Entity.User;
import com.example.onlinelearning.POJO.PojoOrder;
import com.example.onlinelearning.Repository.CourseRepository;
import com.example.onlinelearning.Repository.OrderRepository;
import com.example.onlinelearning.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    public List<PojoOrder> getAllOrders(long courseId) {
        List<Order> orders = orderRepository.findByCourseCourseId(courseId);
        List<PojoOrder> pojoOrders = new ArrayList<>();
        for (Order order : orders) {
            pojoOrders.add(PojoOrder.fromEntity(order));
        }
        return pojoOrders;
    }

    public PojoOrder getOrderById(long courseId, long orderId) {
        try {
            Order order = orderRepository.findByOrderIdAndCourseCourseId(orderId, courseId);
            return PojoOrder.fromEntity(order);
        } catch (NullPointerException ex) {
            return null;
        }
    }

//    public PojoOrder createOrder(long courseId, PojoOrder pojoOrder, long userId) {
//        Order order = PojoOrder.toEntity(pojoOrder);
//        User user = userRepository.findById(userId).get();
//        // Установка courseId для заказа
//        Course course = courseRepository.findById(courseId).get();
//        order.setCourse(course);
//        order.setUser(user);
//
//        return PojoOrder.fromEntity(orderRepository.save(order));
//    }
public PojoOrder createOrder(long courseId, PojoOrder pojoOrder, long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

    Order order = PojoOrder.toEntity(pojoOrder);
    order.setCourse(course);
    order.setUser(user);

    return PojoOrder.fromEntity(orderRepository.save(order));
}


    public void deleteOrder(long userId, long courseId, long orderId) {
        orderRepository.deleteByOrderIdAndUserUserIdAndCourseCourseId(orderId, userId, courseId);
    }

    public List<PojoOrder> findOrdersByDate(long courseId, Date orderDate) {
        List<Order> orders = orderRepository.findByCourseCourseIdAndOrderDate(courseId, orderDate);
        List<PojoOrder> pojoOrders = new ArrayList<>();
        for (Order order : orders) {
            pojoOrders.add(PojoOrder.fromEntity(order));
        }
        return pojoOrders;
    }
    public List<PojoOrder> getAllOrdersByUserId(long userId) {
        List<Order> orders = orderRepository.findByUserUserId(userId);
        List<PojoOrder> pojoOrders = new ArrayList<>();
        for (Order order : orders) {
            pojoOrders.add(PojoOrder.fromEntity(order));
        }

        return pojoOrders;
    }
}