package com.example.onlinelearning.Repository;

import com.example.onlinelearning.Entity.Order;
import com.example.onlinelearning.POJO.PojoOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCourseCourseId(long courseId);

    List<Order> findByOrderDate(Date orderDate);
    List<Order> findByUserUserId(long userId);

    List<Order> findByCourseCourseIdAndOrderDate(long courseId, Date orderDate);
    public void deleteAllByCourseCourseId(long courseId);
    Order findByOrderIdAndCourseCourseId(long orderId,long courseId);
    @Transactional
    void deleteByOrderIdAndUserUserIdAndCourseCourseId(long ordderId, long userId, long courseId);
}