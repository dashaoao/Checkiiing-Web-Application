package com.example.checkiiing.services;


import com.example.checkiiing.models.Questions;
import com.example.checkiiing.repositories.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    public List<Questions> listQuestions() { return questionsRepository.findAll();}

    public List<Questions> filterQuestionsByTestId(List<Questions> questionsList, Long testId) {
        return questionsList.stream()
                .filter(question -> question.getTest().getId_test().equals(testId))
                .collect(Collectors.toList());
    }

    public void saveQuestions(Questions question){
        if (!questionsRepository.existsByText((question.getText()))) {
            log.info("Saving new {}", question);
            questionsRepository.save(question);
        }
    }
}
