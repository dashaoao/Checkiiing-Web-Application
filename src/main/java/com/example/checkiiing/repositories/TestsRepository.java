package com.example.checkiiing.repositories;

import com.example.checkiiing.models.Tests;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestsRepository extends JpaRepository<Tests, Long> {
    boolean existsByTitle(String title);

}
