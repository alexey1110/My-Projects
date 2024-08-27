package com.example.onlinelearning.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity
@Table(name = "orders", schema = "public")
@Getter
@Setter
@Data

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date orderDate;

    public Long getUserId() {
        return this.user.getUserId();
    }
    public Long getCourseId() {
        return this.course.getCourseId();
    }

}
