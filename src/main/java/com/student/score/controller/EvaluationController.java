package com.student.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.score.model.ScoreRequest;
import com.student.score.model.ScoreResponse;
import com.student.score.model.ScoreSheetRequest;
import com.student.score.service.EvaluationService;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/sheets")
    public ResponseEntity<Object> createScoreSheet(@RequestBody List<ScoreSheetRequest> scoreSheets) {
    	if(scoreSheets == null || scoreSheets.isEmpty()) {
    		return new ResponseEntity<>("Invalid Request !! Scoresheets cannot be null or empty!!",HttpStatus.BAD_REQUEST);
    	}
        evaluationService.createScoreSheets(scoreSheets);
        return new ResponseEntity<>("Records inserted successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/scores")
    public ResponseEntity<List<ScoreResponse>> getScores(
            @RequestBody ScoreRequest scoreRequest) {
        List<ScoreResponse> scores = evaluationService.getScores(scoreRequest.getTesteeIds(), scoreRequest.getSubjects(),
        		scoreRequest.getTotalRange(), scoreRequest.getAverageRange(), scoreRequest.getScoreRange());
        return ResponseEntity.ok(scores);
    }
}