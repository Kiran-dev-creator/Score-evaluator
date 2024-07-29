package com.student.score.model;

import lombok.Data;

@Data
public class ScoreSheetResponse {

    private String testeeId;

    private String subject;

    private Double score;
}