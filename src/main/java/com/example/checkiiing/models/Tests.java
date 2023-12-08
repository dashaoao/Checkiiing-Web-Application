package com.example.checkiiing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Tests", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_test")
    private Long id_test;
    @Column(name = "title")
    private String title;
    @Column(name = "number_of_questions")
    private Long number_of_questions;
    @Column(name = "completion_time")
    private Long completion_time;
    @Column(name = "max_score")
    private Long max_score;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<Students> students;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<Questions> questions;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<Statistic> statistic;

    public Tests(String title, Long number_of_questions, Long completion_time, Long max_score) {
        this.title = title;
        this.number_of_questions = number_of_questions;
        this.completion_time = completion_time;
        this.max_score = max_score;
    }
}


