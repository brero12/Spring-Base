package co.brero12.springboot.jpah2base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.brero12.springboot.jpah2base.exception.ResourceNotFoundException;
import co.brero12.springboot.jpah2base.iservice.IStudentService;
import co.brero12.springboot.jpah2base.model.Student;
import co.brero12.springboot.jpah2base.repository.StudentRepository;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	private static final String NOTFOUNDMESSAGE = "Student not found for this id :: ";

	public List<Student> getAllStudents() {

		return studentRepository.findAll();

	}

	@Override
	public Student getStudentById(Long studentId) throws ResourceNotFoundException {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException(NOTFOUNDMESSAGE + studentId));

		return student;
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Long studentId, Student studentDetails) throws ResourceNotFoundException {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException(NOTFOUNDMESSAGE + studentId));

		student.setEmailId(studentDetails.getEmailId());
		student.setLastName(studentDetails.getLastName());
		student.setFirstName(studentDetails.getFirstName());
		final Student updatedStudent = studentRepository.save(student);
		return updatedStudent;
	}

	@Override
	public Map<String, Boolean> deleteStudent(Long studentId) throws ResourceNotFoundException {
		
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException(NOTFOUNDMESSAGE + studentId));

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
