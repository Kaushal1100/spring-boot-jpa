package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateStudentRequest {

	@NotNull(message = "Student id is required")
	private Long id;
	@JsonProperty("first_name")
	private String firstName;
	private String lastName;
	private String email;

}
