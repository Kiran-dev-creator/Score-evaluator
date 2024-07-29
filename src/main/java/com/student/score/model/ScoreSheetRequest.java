package com.student.score.model;

import java.util.List;

import lombok.Data;

@Data
public class ScoreSheetRequest {

    private String testeeId;

    private List<SubjectDetails> subjects;
}