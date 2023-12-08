package com.example.checkiiing.services;


import com.example.checkiiing.models.Questions;
import com.example.checkiiing.models.Students;
import com.example.checkiiing.repositories.StudentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public List<Students> listStudents() { return studentsRepository.findAll();}

//    public List<Students> filterStudentsByStudentId(List<Students> studentsList, Long studentId) {
//        return studentsList.stream()
//                .filter(student -> student.getId_student().equals(studentId))
//                .collect(Collectors.toList());
//    }

    public void saveStudents(Students student){
        if (!studentsRepository.existsByFullName((student.getFullName()))) {
            log.info("Saving new {}", student);
            studentsRepository.save(student);
        }
    }

}
