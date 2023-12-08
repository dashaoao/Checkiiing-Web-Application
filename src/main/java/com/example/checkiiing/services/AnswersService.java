package com.example.checkiiing.services;

import com.example.checkiiing.models.Answers;
import com.example.checkiiing.models.Questions;
import com.example.checkiiing.repositories.AnswersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswersService {
    private final AnswersRepository answersRepository;

    public List<Answers> listAnswers() { return answersRepository.findAll();}

    public List<Answers> filterAnswersByIdQuestion(List<Answers> listAnswers, Long questionId) {
        return listAnswers.stream()
                .filter(answer -> answer.getQuestion().getId_question().equals(questionId))
                .collect(Collectors.toList());
    }

    public void saveAnswers(Answers answer){
        if (!answersRepository.existsByResponse((answer.getResponse()))) {
            log.info("Saving new {}", answer);
            answersRepository.save(answer);
        }
    }
}
