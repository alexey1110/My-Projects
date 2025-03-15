package com.example.MyFavoriteTeam.Service;

import com.example.MyFavoriteTeam.Models.Team;
import com.example.MyFavoriteTeam.Models.UserSubscription;
import com.example.MyFavoriteTeam.Repositories.TeamRepository;
import com.example.MyFavoriteTeam.Repositories.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriprionService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    public boolean subscribe(Long userId, String teamName) {
        Team team = teamRepository.findByName(teamName);
        if (team == null) {
            return false;
        }

        if (userSubscriptionRepository.existsByUserIdAndTeamId(userId, team.getId())) {
            return false;
        }

        UserSubscription subscription = new UserSubscription();
        subscription.setUserId(userId);
        subscription.setTeam(team);

        userSubscriptionRepository.save(subscription);
        return true;
    }

    public boolean unsubscribe(Long userId, String teamName) {
        Team team = teamRepository.findByName(teamName);
        if (team == null) {
            return false;
        }

        if (!userSubscriptionRepository.existsByUserIdAndTeamId(userId, team.getId())) {
            return false;
        }

        userSubscriptionRepository.deleteByUserIdAndTeamId(userId, team.getId());
        return true;
    }

    public List<String> getUserSubscriptions(Long userId) {
        List<UserSubscription> subscriptions = userSubscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .map(subscription -> subscription.getTeam().getName())
                .toList();
    }
}

