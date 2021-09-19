package co.brero12.springboot.jpah2base.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import co.brero12.springboot.jpah2base.Application;
import co.brero12.springboot.jpah2base.dto.StudentDto;
import co.brero12.springboot.jpah2base.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
		assertFalse(false);
	}

	@Test
	public void testGetAllStudents() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/students",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetStudentById() {
		StudentDto student = restTemplate.getForObject(getRootUrl() + "/students/1", StudentDto.class);
		System.out.println(student.getFirstName());
		assertNotNull(student);
	}

	@Test
	public void testCreateStudent() {
		StudentDto student = new StudentDto();
		student.setEmailId("test@correo.com");
		student.setFirstName("test");
		student.setLastName("test");

		ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/students", student, Student.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateStudent() {
		int id = 1;
		StudentDto student = restTemplate.getForObject(getRootUrl() + "/students/" + id, StudentDto.class);
		student.setFirstName("admin1");
		student.setLastName("admin2");

		restTemplate.put(getRootUrl() + "/students/" + id, student);

		StudentDto updatedStudent = restTemplate.getForObject(getRootUrl() + "/students/" + id, StudentDto.class);
		assertNotNull(updatedStudent);
	}

	@Test
	public void testDeleteStudent() {
		int id = 2;
		StudentDto student = restTemplate.getForObject(getRootUrl() + "/students/" + id, StudentDto.class);
		assertNotNull(student);

		restTemplate.delete(getRootUrl() + "/students/" + id);

		try {
			student = restTemplate.getForObject(getRootUrl() + "/students/" + id, StudentDto.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND,e.getStatusCode());
		}
	}
}
