package com.example.MyFavoriteTeam.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
