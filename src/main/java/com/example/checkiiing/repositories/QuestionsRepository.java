package com.example.checkiiing.repositories;

import com.example.checkiiing.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    boolean existsByText(String text);


}
