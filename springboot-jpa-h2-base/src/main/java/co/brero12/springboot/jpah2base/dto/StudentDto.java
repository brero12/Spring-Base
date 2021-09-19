package co.brero12.springboot.jpah2base.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentDto {

	private long id;
	private String firstName;
	private String lastName;
	private String emailId;
	
	public StudentDto() {
		
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}
	
}
