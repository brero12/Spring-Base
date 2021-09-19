package co.brero12.springboot.jpah2base.iservice;

import java.util.List;
import java.util.Map;

import co.brero12.springboot.jpah2base.exception.ResourceNotFoundException;
import co.brero12.springboot.jpah2base.model.Student;

public interface IStudentService {

	public List<Student> getAllStudents();
	
	public Student getStudentById(Long studentId) throws ResourceNotFoundException;
	
	public Student createStudent(Student student);
	
	public Student updateStudent(Long studentId,Student studentDetails) throws ResourceNotFoundException;
	
	public Map<String, Boolean> deleteStudent(Long studentId) throws ResourceNotFoundException;

}
