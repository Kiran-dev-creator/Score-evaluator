package com.student.score.model;

import java.util.Map;
import java.util.Objects;

import lombok.Data;

@Data
public class ScoreResponse {

    private String testeeId;

    private Map<String, Double> scores;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreResponse other = (ScoreResponse) obj;
		return Objects.equals(testeeId, other.testeeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(testeeId);
	}
    
    

}