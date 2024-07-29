package com.student.score.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "testee_score_sheets")
@Data
public class ScoreSheetEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "testee_id")
    private String testeeId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @Column(name = "correct")
    private Integer correct;

    @Column(name = "incorrect")
    private Integer incorrect;

}