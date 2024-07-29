package com.student.score.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.score.entity.ScoreEntity;
import com.student.score.entity.ScoreSheetEntity;
import com.student.score.exception.InvalidRequestException;
import com.student.score.model.ScoreResponse;
import com.student.score.model.ScoreSheetRequest;
import com.student.score.model.SubjectDetails;
import com.student.score.repository.ScoreRepository;
import com.student.score.repository.ScoreSheetRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EvaluationService {

	
	
    @Autowired
    private ScoreSheetRepository scoreSheetRepository;

    @Autowired
    private ScoreRepository scoreRepository;
    
    private static final List<String> subjectsList = Collections.unmodifiableList(Arrays.asList("maths","science","general"));

    public void createScoreSheets(List<ScoreSheetRequest> scoreSheets) {
        for(ScoreSheetRequest scoreSheet : scoreSheets) {
            ScoreSheetEntity scoreSheetEntity = new ScoreSheetEntity();
            scoreSheetEntity.setTesteeId(scoreSheet.getTesteeId());
            for(SubjectDetails subjectDetails : scoreSheet.getSubjects()) {
            
            scoreSheetEntity.setSubject(subjectDetails.getSubject());
            scoreSheetEntity.setTotalQuestions(subjectDetails.getTotalQuestions());
            scoreSheetEntity.setCorrect(subjectDetails.getCorrect());
            scoreSheetEntity.setIncorrect(subjectDetails.getIncorrect());
            scoreSheetRepository.save(scoreSheetEntity);
            

            ScoreEntity scoreEntity = new ScoreEntity();
            double totalScore = 0.0;
            double avgScore = 0.0;
            double currentSubjectScore = calculateScore(subjectDetails, scoreSheet.getTesteeId());
            List<ScoreEntity> scoreEntitiesList = scoreRepository.findByTesteeId(scoreSheet.getTesteeId());
            totalScore = totalScore + currentSubjectScore;
            log.info("Total score is "+totalScore);
            if(scoreEntitiesList != null && !scoreEntitiesList.isEmpty()) {
            	for(ScoreEntity se : scoreEntitiesList) {
            		totalScore = totalScore + se.getScore();	
            	}
            	avgScore = ( totalScore) / (scoreEntitiesList.size()+1);
            	scoreEntity.setScore(currentSubjectScore);
            	scoreEntity.setTotalScore(totalScore);
            	scoreEntity.setAvgScore(avgScore);
            	
            }
            else {
            	log.info("Else block "+totalScore);
            	scoreEntity.setScore(currentSubjectScore);
            	scoreEntity.setTotalScore(totalScore);
            	scoreEntity.setAvgScore(currentSubjectScore);
            }
            
            scoreEntity.setTesteeId(scoreSheet.getTesteeId());
            scoreEntity.setSubject(subjectDetails.getSubject());
            
            scoreRepository.save(scoreEntity);
            }
        }
       
    }
    
    public Double calculateScore(SubjectDetails subject, String testeeId) {
        // Validate the request
        if (testeeId == null || subject == null || subject.getCorrect() == null || subject.getIncorrect() == null
        		|| subject.getTotalQuestions() == null) {
            throw new InvalidRequestException("Testee ID and subject details are required");
        }

        // Get the score sheet entity from the database
        Double score = ((double) (subject.getCorrect())/ subject.getTotalQuestions()) * 100;
        return score;
    }


    public List<ScoreResponse> getScores(List<String> testeeIds, List<String> subjects, String totalRange, String averageRange, String scoreRange) {
        List<ScoreResponse> scores = new ArrayList<>();

        List<ScoreEntity> scoreEntities = scoreRepository.findAll();
        Map<String,List<ScoreEntity>> filteredEntities = new HashMap<>();
        Map<String,ScoreResponse> tempMap = new HashMap<>();

        for(ScoreEntity scoreEntity: scoreEntities) {
            if (testeeIds != null && !testeeIds.contains(scoreEntity.getTesteeId())) {
                continue;
            }

            if (subjects != null && !subjects.contains(scoreEntity.getSubject())) {
            	continue;
            }

            if (totalRange != null) {
                String[] range = totalRange.split("-");
                if (scoreEntity.getTotalScore() < Double.parseDouble(range[0]) || scoreEntity.getTotalScore() > Double.parseDouble(range[1])) {
                	continue;
                }
            }

            if (averageRange != null) {
                String[] range = averageRange.split("-");
                if (scoreEntity.getAvgScore() < Double.parseDouble(range[0]) || scoreEntity.getAvgScore() > Double.parseDouble(range[1])) {
                	continue;
                }
            }

            if (scoreRange != null) {
                String[] range = scoreRange.split("-");
                if (scoreEntity.getScore() < Double.parseDouble(range[0]) || scoreEntity.getScore() > Double.parseDouble(range[1])) {
                	continue;
                }
            }
            
            if(filteredEntities.containsKey(scoreEntity.getTesteeId())) {
            	List<ScoreEntity> scoreList = filteredEntities.get(scoreEntity.getTesteeId());
            	scoreList.add(scoreEntity);
            	filteredEntities.put(scoreEntity.getTesteeId(), scoreList);
            }
            else {
            	List<ScoreEntity> list = new ArrayList<>();
            	list.add(scoreEntity);
            	filteredEntities.put(scoreEntity.getTesteeId(), list);
            }
                   
            
        }
        
        for(List<ScoreEntity> list : filteredEntities.values()) {
        	Collections.sort(list,(c1,c2) -> c2.getTotalScore().compareTo(c1.getTotalScore()));
        	ScoreResponse scr = new ScoreResponse();
        	Map<String,Double> scoreMap = new HashMap<>();
        	scoreMap.put("total", list.get(0).getTotalScore());
        	scoreMap.put("average", list.get(0).getAvgScore());
        	for(ScoreEntity sc : list) {
        		scoreMap.put(sc.getSubject(), sc.getScore());
        	}
        	scr.setScores(scoreMap);
        	scr.setTesteeId(list.get(0).getTesteeId());
        	scores.add(scr);
        }
        
        Comparator<ScoreResponse> c = (c1,c2) ->{
        	Map<String,Double> map1 = c1.getScores();
        	Map<String,Double> map2 = c2.getScores();
        	if(map2.get("total").compareTo(map1.get("total")) == 0) {
        		return c1.getTesteeId().compareTo(c2.getTesteeId());
        	}
        	else {
        		return map2.get("total").compareTo(map1.get("total"));
        	}
        };
        
        Collections.sort(scores,c);
        
        return scores;
        
        
    }

}
