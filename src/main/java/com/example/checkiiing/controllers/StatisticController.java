package com.example.checkiiing.controllers;

import com.example.checkiiing.models.*;
import com.example.checkiiing.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StatisticController {

    private final TestsService testsService;
    private final StudentsService studentsService;
    private final QuestionsService questionsService;
    private final AnswersService answersService;
    private final StatisticService statisticService;

    @GetMapping("/statistics")
    public String showStatisticsPage(Model model) {

        model.addAttribute("tests", testsService.listTests());
        List<Long> ids = new ArrayList<>();

        for (Statistic element : statisticService.listStatistic()) {
            Long id = element.getTest().getId_test();
            ids.add(id);
        }

        List<Stat> stat = new ArrayList<>();

        for (Long id : ids){
            List<Statistic> elementsWithSameId = new ArrayList<>();
            float averageScore = 0.0F;
            int sum = 0;
            int count = 0;
            for (Statistic element : statisticService.listStatistic()){
                if (element.getTest().getId_test().equals(id)) {
                    elementsWithSameId.add(element);
                    sum += element.getScore();
                    count++;
                }
                averageScore = (float)sum / count;
            }
            int numberOfPasses = elementsWithSameId.size();
            String testTitle = elementsWithSameId.get(0).getTest().getTitle();
            Stat el = new Stat(testTitle, averageScore, numberOfPasses);
            stat.add(el);
        }
        model.addAttribute("stats", stat);
        return "statistics";
    }

    @GetMapping("/statistics/saveAnswers")
    public String showStatisticsForm(Model model) {
        model.addAttribute("statisticForm", new Statistic());
        return "statistics";
    }

    @PostMapping("/statistics/saveAnswers")
    public String addStatistic(
            HttpSession session,
            HttpServletRequest request,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        Optional<Tests> result = testsService.listTests().stream()
                .filter(obj -> obj.getId_test() == Integer.parseInt(
                        (String) session.getAttribute("test")
                ))
                .findFirst();

        Tests test = result.get();

        Optional<Students> res = studentsService.listStudents().stream()
                .filter(obj -> Objects.equals(obj.getFullName(), (String) session.getAttribute("student")))
                .findFirst();
        Students student = res.get();


        List<Questions> questions = questionsService.filterQuestionsByTestId(
                questionsService.listQuestions(),
                Long.parseLong(
                        (String) session.getAttribute("test")
                )
        );

        Long score = 0L;

        for (int i=0; i<questions.size(); i++) {
            String name = "answer"+(i+1);
            String response = request.getParameter(name);

            Optional<Answers> ans = answersService.listAnswers().stream()
                    .filter(obj -> Objects.equals(obj.getResponse(), response))
                    .findFirst();
            Answers answer = ans.get();
            score += Integer.parseInt(answer.getCorrectness());
        }

        Statistic statistic = new Statistic(test, student, score);

        statisticService.saveStatistic(statistic);

        redirectAttributes.addFlashAttribute("max_score", test.getMax_score());
        redirectAttributes.addFlashAttribute("score", score);
        return "redirect:/result";
    }
}
