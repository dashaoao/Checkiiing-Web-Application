package com.example.checkiiing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stat {
    private String testTitle;

    private float averageScore;

    private int numberOfPasses;
}
