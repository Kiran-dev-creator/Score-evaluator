package com.student.score;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;


@SpringBootApplication
@SpringBootTest(useMainMethod = UseMainMethod.ALWAYS)
class ScoreEvaluatorApplicationTests {

	@Test
	void contextLoads() {
	}

}
