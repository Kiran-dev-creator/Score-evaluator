package com.student.score.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.score.entity.ScoreEntity;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {

    List<ScoreEntity> findByTesteeId(String testeeId);

    List<ScoreEntity> findBySubject(String subject);
    
    List<ScoreEntity> findByTesteeIdIn(List<String> testeeIds);
    
    List<ScoreEntity> findBySubjectIn(List<String> subjects);
}