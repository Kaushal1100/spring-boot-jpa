package com.example.demo.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeleteStudentRequest {
	@NotNull(message = "Student id is required")
	private Long id;

}
