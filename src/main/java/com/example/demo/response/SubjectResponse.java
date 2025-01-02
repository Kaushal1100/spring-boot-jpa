package com.example.demo.response;

import com.example.demo.entity.Subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class SubjectResponse { //Check console.
	private Long id;
	private String subjectName;
	private Double marksObtained;
	public SubjectResponse(Subject subject) {
		this.id = subject.getId();
		this.subjectName = subject.getSubjectName();
		this.marksObtained = subject.getMarksObtained();
	}
}