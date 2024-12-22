package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.CreateStudentRequest;
import com.example.demo.request.InQueryRequest;
import com.example.demo.request.UpdateStudentRequest;
import com.example.demo.repository.SubjectRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		student.setAddress(address);
		student = studentRepository.save(student);
		return student;

	}

	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {

		Student student = studentRepository.findById(updateStudentRequest.getId()).get();
		if (updateStudentRequest.getFirstName() != null && !updateStudentRequest.getLastName().isEmpty()) {

			student.setFirstName(updateStudentRequest.getFirstName());
			student.setLastName(updateStudentRequest.getLastName());
			student.setEmail(updateStudentRequest.getEmail());

			studentRepository.save(student);
		}
		return student;

	}

//	public void  deleteStudent(DeleteStudentRequest deleteStudentRequest) {
//		
//		studentRepository.deleteById(deleteStudentRequest.getId());
//		
//	}

	public String deleteStudent(long id) {

		studentRepository.deleteById(id);
		return "Student deleted successfully";

	}

	public List<Student> getByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}

	public List<Student> getByLastName(String lastName) {
		return studentRepository.findByLastName(lastName);
	}

	public Student getByFirstNameAndLastName(String firstName, String lastName) {
		return studentRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}

	public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
		// Spring data provides pageable interface, select it from springframework
		// not from awt.
		// PageRequest.of is zero based page index, so you need to pass 0 for page 1.
		// So, you need to do -1
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return studentRepository.findAll(pageable).getContent();
	}

	public List<Student> getAllStudentsWithSorting() {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName", "email");
		return studentRepository.findAll(sort);
	}

	public List<Student> like(String firstName) {
		return studentRepository.findByFirstNameContains(firstName);
	}

	public List<Student> startsWith(String firstName) {
		return studentRepository.findByFirstNameStartsWith(firstName);
	}

	public Integer updateStudentWithJpql(Long id, String firstName) {
		return studentRepository.updateFirstName(id, firstName);
	}

	public Integer deleteStudent(String firstName) {
		return studentRepository.deleteByFirstName(firstName);
	}

	public List<Student> getByCity(String city) {
		return studentRepository.findByAddressCity(city);
	}
}