package com.student.score.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDetails{
	
	private String subject;
	private Integer totalQuestions;
	private Integer correct;
	private Integer incorrect;

}
