package com.student.score.model;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ScoreRequest {
	
	List<String> testeeIds;
	
	List<String> subjects;
    String totalRange;
    String averageRange;
    @JsonProperty("scoreRange")
    String scoreRange;

}
