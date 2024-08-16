package com.sohaib.app.response;


import com.sohaib.app.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {


    private long id;

	private String firstName;

	private String lastName;

	private String email;

	private AddressResponse addressResponse;

	public StudentResponse(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
	}

}
