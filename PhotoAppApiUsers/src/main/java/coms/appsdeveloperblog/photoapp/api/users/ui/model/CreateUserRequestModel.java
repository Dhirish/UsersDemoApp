package coms.appsdeveloperblog.photoapp.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
	
	@NotNull(message="Firstname cannot be null")
	@Size(min = 2,message= "First name cannot be less than 2 characters")
	private String firstname;
	
	@NotNull(message="lastname cannot be null")
	@Size(min = 2,message= "last name cannot be less than 2 characters")
	private String lastname;
	
	@NotNull(message="Please provide the password")
	@Size(min=8,max=16,message="password must be between 8 and 16 characters")
	private String password;
	
	@NotNull(message="email cannot be null")
	@Email(regexp = "^(.+)@(.+)$")
	private String email;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
