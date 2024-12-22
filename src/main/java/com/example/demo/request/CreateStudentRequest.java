package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateStudentRequest {
	@JsonProperty("first_name")
	@NotBlank(message="First name is required")
	private String firstName;

	private String lastName;

	private String email;

	private String street;

	private String city;

}
