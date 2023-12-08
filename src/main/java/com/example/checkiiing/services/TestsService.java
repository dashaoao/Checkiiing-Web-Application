package com.example.checkiiing.services;

import com.example.checkiiing.models.Tests;
import com.example.checkiiing.repositories.TestsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestsService {

    private final TestsRepository testsRepository;

    public List<Tests> listTests() { return testsRepository.findAll();}

    public void saveTests(Tests test){
        if (!testsRepository.existsByTitle(test.getTitle())) {
            log.info("Saving new {}", test);
            testsRepository.save(test);
        }
    }

    public void deleteTest(Long id) {
        testsRepository.deleteById(id);
    }

}