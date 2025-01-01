package com.example.demo.entity;


import java.util.List;

import com.example.demo.request.CreateStudentRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student",indexes= {
		@Index(name="idx_first_Name",columnList="first_name"),
		@Index(name="idx_last_Name",columnList="last_name")
})
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Transient
	private String fullName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(mappedBy="student")
	private List<Subject> learningSubjects;
	public Student(CreateStudentRequest createStudentRequest) {
		this.firstName = createStudentRequest.getFirstName();
		this.lastName = createStudentRequest.getLastName();
		this.email = createStudentRequest.getEmail();
		this.fullName= createStudentRequest.getFirstName()+" "
		+createStudentRequest.getLastName();
		
	}
}
