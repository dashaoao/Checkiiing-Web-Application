package com.example.checkiiing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_answer")
    private Long id_answer;
    @Column(name = "correctness")
    private String correctness;
    @Column(name = "response", columnDefinition = "text")
    private String response;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questions")
    private Questions question;

    public Answers(String correctness, String response, Questions question) {
        this.correctness = correctness;
        this.response = response;
        this.question = question;
    }
}