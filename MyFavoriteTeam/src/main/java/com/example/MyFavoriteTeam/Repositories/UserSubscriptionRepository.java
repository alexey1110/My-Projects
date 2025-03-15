package com.example.MyFavoriteTeam.Repositories;

import com.example.MyFavoriteTeam.Models.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUserId(Long userId);
    boolean existsByUserIdAndTeamId(Long userId, Long teamId);
    void deleteByUserIdAndTeamId(Long userId, Long teamId);
}
