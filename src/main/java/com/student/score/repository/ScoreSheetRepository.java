package com.student.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.score.entity.ScoreSheetEntity;

public interface ScoreSheetRepository extends JpaRepository<ScoreSheetEntity, Long> {

}