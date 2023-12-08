package com.example.checkiiing.controllers;

import com.example.checkiiing.models.Students;
import com.example.checkiiing.models.Tests;
import com.example.checkiiing.services.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;

}
