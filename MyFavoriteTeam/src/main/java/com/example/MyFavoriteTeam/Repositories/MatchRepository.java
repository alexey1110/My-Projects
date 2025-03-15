package com.example.MyFavoriteTeam.Repositories;

import com.example.MyFavoriteTeam.Models.Match;
import org.checkerframework.common.util.count.report.qual.ReportCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByTeamId(Long teamId);
}
