package com.example.checkiiing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Statistic", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_passing")
    private Long id_passing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test")
    private Tests test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private Students student;

    @Column(name = "score")
    private Long score;

    public Statistic(Tests test, Students student, Long score) {
        this.test = test;
        this.student = student;
        this.score = score;
    }
}
