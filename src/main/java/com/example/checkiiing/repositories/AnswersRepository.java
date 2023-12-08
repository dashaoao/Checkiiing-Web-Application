package com.example.checkiiing.repositories;

import com.example.checkiiing.models.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
    boolean existsByResponse(String text);
}
