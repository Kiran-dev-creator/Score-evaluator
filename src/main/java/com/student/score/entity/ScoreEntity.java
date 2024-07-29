package com.student.score.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "testee_scores")
@Data
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "testee_id")
    private String testeeId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "score")
    private Double score;
    
    @Column(name = "total")
    private Double totalScore;
    
    @Column(name = "average")
    private Double avgScore;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreEntity other = (ScoreEntity) obj;
		return Objects.equals(testeeId, other.testeeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(testeeId);
	}
    
    
    
    

}