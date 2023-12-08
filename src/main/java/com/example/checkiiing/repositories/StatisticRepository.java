package com.example.checkiiing.repositories;

import com.example.checkiiing.models.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository  extends JpaRepository<Statistic, Long> {
    boolean existsByScore(Long score);
}