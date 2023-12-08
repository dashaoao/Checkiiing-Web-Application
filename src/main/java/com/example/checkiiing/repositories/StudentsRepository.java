package com.example.checkiiing.repositories;

import com.example.checkiiing.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    boolean existsByFullName(String fullName);
}
