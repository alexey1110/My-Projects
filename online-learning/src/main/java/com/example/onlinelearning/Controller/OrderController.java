package com.example.onlinelearning.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.onlinelearning.POJO.PojoOrder;
import com.example.onlinelearning.Service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.hibernate.internal.CoreLogging.logger;

@CrossOrigin
@RestController
@RequestMapping("/users/{userId}/courses/{courseId}/orders") // courseId будет заполняться из URL
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @GetMapping("/busket")
    public ResponseEntity<List<PojoOrder>> getAllOrdersByUserId(@PathVariable long userId) {
        List<PojoOrder> orders = orderService.getAllOrdersByUserId(userId);

        return ResponseEntity.ok(orders);
    }
    @GetMapping
    public ResponseEntity<List<PojoOrder>> getAllOrders(@PathVariable long courseId) {
        List<PojoOrder> orders = orderService.getAllOrders(courseId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PojoOrder> getOrderById(@PathVariable long courseId, @PathVariable long orderId) {
        PojoOrder order = orderService.getOrderById(courseId, orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<PojoOrder> createOrder(@PathVariable("courseId") long courseId, @RequestBody PojoOrder pojoOrder,@PathVariable("userId") long userId) {
        logger.info("Received request to create order. UserId: {}, CourseId: {}", userId, courseId);

        // Ваш код для создания заказа

        PojoOrder createdOrder = orderService.createOrder(courseId, pojoOrder, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("userId") long userId, @PathVariable long courseId, @PathVariable long orderId) {
        orderService.deleteOrder(userId, courseId, orderId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<PojoOrder>> getOrdersByDate(@PathVariable long courseId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate) {
        List<PojoOrder> orders = orderService.findOrdersByDate(courseId, orderDate);
        return ResponseEntity.ok(orders);
    }
}