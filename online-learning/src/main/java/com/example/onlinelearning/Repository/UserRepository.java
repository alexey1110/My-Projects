package com.example.onlinelearning.Repository;

import com.example.onlinelearning.Entity.Order;
import com.example.onlinelearning.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}