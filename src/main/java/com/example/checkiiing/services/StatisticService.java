package com.example.checkiiing.services;

import com.example.checkiiing.models.Questions;
import com.example.checkiiing.models.Statistic;
import com.example.checkiiing.repositories.QuestionsRepository;
import com.example.checkiiing.repositories.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public List<Statistic> listStatistic() { return statisticRepository.findAll();}

    public void saveStatistic(Statistic statistic){
        if (!statisticRepository.existsByScore((statistic.getScore()))) {
            log.info("Saving new {}", statistic);
            statisticRepository.save(statistic);
        }
    }
}