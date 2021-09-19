package co.brero12.springboot.jpah2base.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.brero12.springboot.jpah2base.dto.StudentDto;
import co.brero12.springboot.jpah2base.exception.ResourceNotFoundException;
import co.brero12.springboot.jpah2base.iservice.IStudentService;
import co.brero12.springboot.jpah2base.model.Student;
import co.brero12.springboot.jpah2base.service.StudentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/students")
	public List<StudentDto> getAllStudents() {

		List<Student> students = studentService.getAllStudents();

		List<StudentDto> studentDto = students.stream().map(this::studentConvertToDto).collect(Collectors.toList());

		return studentDto;
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {

		return ResponseEntity.ok().body(studentConvertToDto(studentService.getStudentById(studentId)));
	}

	@PostMapping("/students")
	public StudentDto createStudent(@Valid @RequestBody StudentDto studentDto) {

		Student student = studentConvertToEntity(studentDto);
		return studentConvertToDto(studentService.createStudent(student));
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable(value = "id") Long studentId,
			@Valid @RequestBody StudentDto studentDetailsDto) throws ResourceNotFoundException {

		Student studentDetails = studentConvertToEntity(studentDetailsDto);
		return ResponseEntity
				.ok(studentConvertToDto(studentService.updateStudent(studentId, studentDetails)));
	}

	@DeleteMapping("/students/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {
		return studentService.deleteStudent(studentId);
	}
	
	private StudentDto studentConvertToDto(Student student) {
		StudentDto studentDto = modelMapper.map(student, StudentDto.class);
		return studentDto;
	}
	
	private Student studentConvertToEntity(StudentDto studentDto) {
		Student student = modelMapper.map(studentDto, Student.class);
		return student;
	}
	
}
