package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.response.StudentResponse;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	Logger logger=LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;

	@GetMapping("/getAll")
	public List<StudentResponse> getAllStudent() {
		
		logger.error("Inside error");
						
		List<Student> studentList = studentService.getAllStudents();

		List<StudentResponse> studentResponseList = new ArrayList<>(); // its a DTO

		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});

		return studentResponseList;

	}

	@PostMapping("/create")
	public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		Student student = studentService.createStudent(createStudentRequest);
		logger.info("Created New Student");
		return new StudentResponse(student);
	}

	@PutMapping("/update")
	public StudentResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {

		Student student = studentService.updateStudent(updateStudentRequest);
		return new StudentResponse(student);

	}

//	@DeleteMapping("/delete")
//	public ResponseEntity<Map<String,String>> deleteStudent(@RequestBody DeleteStudentRequest deleteStudentRequest) {
//		Map<String,String> response=new HashMap<>();
//		response.put("msg", "Student Deleted");
//		studentService.deleteStudent(deleteStudentRequest);
//		return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id) {
		return studentService.deleteStudent(id);
	}

	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
		// 1. Get list of Students
		List<Student> studentList = studentService.getByFirstName(firstName);

		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<>();
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});

		return studentResponseList;
	}

	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
		// 1. Get student
		Student student = studentService.getByFirstNameAndLastName(firstName, lastName);

		// 2. Convert Student object to StudentResponse
		return new StudentResponse(student);
	}
	
	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName,
			@PathVariable String lastName) {
		// 1. Get list of Students
		List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<>();
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	@GetMapping("getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
		
		
		logger.info("inQueryRequest="+inQueryRequest);
		// 1. Get list of Students
		List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
		
		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<>();
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		logger.info("Student ResponseList= "+studentResponseList);
		return studentResponseList;
	}
	@GetMapping("getAllWithPagination")
	public List<StudentResponse> getAllStudentsWithPagination(@RequestParam int pageNo,  @RequestParam int pageSize) {
		// 1. Get list of Students
		List<Student> studentList = studentService.getAllStudentsWithPagination(pageNo, pageSize);
		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<>();
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	@GetMapping("getAllWithSorting")
	public List<StudentResponse> getAllStudentsWithSorting () {
		
		List<Student> studentList = studentService.getAllStudentsWithSorting();
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	@GetMapping("like/{firstName}")
	public List<StudentResponse> like(@PathVariable String firstName) {
		
		List<Student> studentList = studentService.like(firstName);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	@GetMapping("startsWith/{firstName}")
	public List<StudentResponse> startsWith(@PathVariable String firstName) {
		
		List<Student> studentList = studentService.startsWith(firstName);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	//UpdateQuery
	@PutMapping("updateFirstName/{id}/{firstName}")
	public String updateStudentWithJpql (@PathVariable Long id, @PathVariable String firstName) {
		return studentService.updateStudentWithJpql(id, firstName)+ " Student(s) updated";
	}
	
	@DeleteMapping("deleteByFirstName/{firstName}")
	public String deleteStudent (@PathVariable String firstName) {
		return studentService.deleteStudent(firstName) + " Student(s) deleted";
	}
	@GetMapping("/getByCity/{city}")
	public List<StudentResponse> getByCity(@PathVariable String city) {
		
		// 1. Get list of all students who live in the same city
		List<Student> studentList = studentService.getByCity(city);
		
		// 2. Convert list of Students to list of StudentResponse
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}

}
