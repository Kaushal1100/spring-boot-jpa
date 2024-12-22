package com.example.demo.response;

import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

	private long id;

	@JsonProperty("first_name")
	private String firstName;

	private String lastName;

	private String email;
	
	private String street;

	private String city;

	public StudentResponse(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
	
		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();
	}

}
