package com.example.checkiiing.controllers;

import com.example.checkiiing.models.Questions;
import com.example.checkiiing.models.Tests;
import com.example.checkiiing.services.QuestionsService;
import com.example.checkiiing.services.TestsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class QuestionsController {

    private final QuestionsService questionsService;
    private final TestsService testsService;

    @GetMapping("/questions")
    public String startSurvey(
            Model model,
            HttpSession session
    ) {
        List<Questions> listQuestions = questionsService.filterQuestionsByTestId(
                questionsService.listQuestions(),
                Long.parseLong(
                        (String) session.getAttribute("test")
                )
        );
        model.addAttribute("questions", listQuestions);

        Optional<Tests> result = testsService.listTests().stream()
                .filter(obj -> obj.getId_test() == Integer.parseInt(
                        (String) session.getAttribute("test")
                ))
                .findFirst();

        model.addAttribute("testTitle", result.get());
        return "questions";
    }
}
