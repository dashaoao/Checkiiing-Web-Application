package com.example.checkiiing.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_student")
    private Long id_student;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "birth_date")
    private String birth_date;
    @Column(name = "university")
    private String university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test")
    private Tests test;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Statistic> statistic;

    public Students(String fullName, String birth_date, String university, Tests test) {
        this.fullName = fullName;
        this.birth_date = birth_date;
        this.university = university;
        this.test = test;
    }
}
