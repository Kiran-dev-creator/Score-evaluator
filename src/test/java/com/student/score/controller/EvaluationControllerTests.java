package com.student.score.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.student.score.model.ScoreSheetRequest;
import com.student.score.model.SubjectDetails;
import com.student.score.service.EvaluationService;
import org.junit.jupiter.api.Assertions;

@ComponentScan(basePackages = "com.student.score")
@SpringBootTest(classes = EvaluationController.class)
@ContextConfiguration(classes = {EvaluationController.class, EvaluationService.class})
public class EvaluationControllerTests {
	
	
	@InjectMocks
	private EvaluationController evaluationController;
	
	@Mock
	private EvaluationService evaluationService;
	
	
	@Test
	public void testCreateScoreSheet_ReturnCreated() {
		
		List<ScoreSheetRequest> scoreSheetRequestList = new ArrayList<>();
		ScoreSheetRequest scoreSheetRequest = new ScoreSheetRequest();
		scoreSheetRequest.setTesteeId("342");
		SubjectDetails subjectDetails = new SubjectDetails("maths",100,60,40);
		scoreSheetRequest.setSubjects(java.util.Arrays.asList(subjectDetails));
		scoreSheetRequestList.add(scoreSheetRequest);
		
		Mockito.doNothing().when(evaluationService).createScoreSheets(scoreSheetRequestList);
		ResponseEntity<Object> response = evaluationController.createScoreSheet(scoreSheetRequestList);
		Assertions.assertNotNull(response);
		Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
		
		
	}

}
