package com.example.checkiiing.controllers;

import com.example.checkiiing.models.Answers;
import com.example.checkiiing.models.Questions;
import com.example.checkiiing.models.Students;
import com.example.checkiiing.models.Tests;
import com.example.checkiiing.services.AnswersService;
import com.example.checkiiing.services.QuestionsService;
import com.example.checkiiing.services.StudentsService;
import com.example.checkiiing.services.TestsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;


@Controller
@RequiredArgsConstructor
public class TestsController {
    private final TestsService testsService;
    private final QuestionsService questionsService;
    private final AnswersService answersService;
    private final StudentsService studentsService;

    @GetMapping("/tests")
    public String startTest(Model model) {
        model.addAttribute("tests", testsService.listTests());
        return "tests";
    }

    @PostMapping("/questions")
    public String startTest(
            Model model,
            HttpSession session,
            @RequestParam("test") String selectedTest,
            @RequestParam("username") String username,
            @RequestParam("birth_date") String birth_date,
            @RequestParam("university") String university
            )
    {
        Optional<Tests> result = testsService.listTests().stream()
                .filter(obj -> obj.getId_test() == Integer.parseInt(selectedTest))
                .findFirst();

        Students student = new Students(username,
                birth_date,
                university,
                result.get());
        model.addAttribute("student", student);
        session.setAttribute("test", selectedTest);
        session.setAttribute("student", student.getFullName());
        studentsService.saveStudents(student);
        return "redirect:/questions";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/tests/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        testsService.deleteTest(id);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/tests/add_test")
    public String addTest(HttpSession session,
                             @RequestParam String testName,
                             @RequestParam int numberOfQuestions,
                             @RequestParam int completionTime) {
        Long maxScore=5L;
        Random random = new Random();
        int randomWithNextInt = random.nextInt();
        Tests test = new Tests(testName, Long.valueOf(numberOfQuestions), Long.valueOf(completionTime), maxScore);
        session.setAttribute("numberOfQuestions", numberOfQuestions);
        testsService.saveTests(test);
        return "redirect:/tests";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add_questions/{id}")
    public String startAddQuestions(Model model, @PathVariable int id, HttpSession session) {
        Optional<Tests> result = testsService.listTests().stream()
                .filter(obj -> obj.getId_test() == id)
                .findFirst();

        Tests test = result.get();
        model.addAttribute("test", test);
        session.setAttribute("testTitle", test.getTitle());
        return "/add_questions";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/tests/add_questions")
    public String addQuestions(
            Model model,
            HttpSession session,
            @RequestParam String question,
            @RequestParam String answer1,
            @RequestParam String answer2,
            @RequestParam String answer3,
            @RequestParam int correctAnswer) {

        Optional<Tests> result = testsService.listTests().stream()
                .filter(obj -> obj.getTitle() == session.getAttribute("testTitle"))
                .findFirst();
        System.out.println(session.getAttribute("testTitle"));
        System.out.println(testsService.listTests().get(0));

        Tests test = result.get();

        System.out.println(session.getAttribute("testTitle"));
        Questions quest = new Questions(question, test);
        questionsService.saveQuestions(quest);

        if(correctAnswer==1){
            Answers ans1 = new Answers("1", answer1, quest);
            Answers ans2 = new Answers("0", answer2, quest);
            Answers ans3 = new Answers("0", answer3, quest);
            answersService.saveAnswers(ans1);
            answersService.saveAnswers(ans2);
            answersService.saveAnswers(ans3);
        } else if (correctAnswer==2) {
            Answers ans1 = new Answers("0", answer1, quest);
            Answers ans2 = new Answers("1", answer2, quest);
            Answers ans3 = new Answers("0", answer3, quest);
            answersService.saveAnswers(ans1);
            answersService.saveAnswers(ans2);
            answersService.saveAnswers(ans3);
        } else {
            Answers ans1 = new Answers("0", answer1, quest);
            Answers ans2 = new Answers("0", answer2, quest);
            Answers ans3 = new Answers("1", answer3, quest);
            answersService.saveAnswers(ans1);
            answersService.saveAnswers(ans2);
            answersService.saveAnswers(ans3);
        }

        return "redirect:/tests";
    }

}
